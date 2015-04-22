package login.com.huntvision;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.stmt.QueryBuilder;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import login.com.huntvision.managers.sqlite.DatabaseHelper;
import login.com.huntvision.models.Cliente;
import login.com.huntvision.models.Item;
import login.com.huntvision.models.Local;
import login.com.huntvision.models.Questionario;
import login.com.huntvision.models.Resposta;
import login.com.huntvision.models.Usuario;
import login.com.huntvision.models.Vistoria;
import login.com.huntvision.models.VistoriaResposta;
import login.com.huntvision.view.adapters.QuestionarioFragmentPageAdapter;

@EActivity(R.layout.activity_questionario)
public class QuestionarioActivity extends FragmentActivity {

    private Uri selectedImage;
    List<Questionario> lstQuestionario;
    private Cliente objCliente;
    private Local objLocal;
    private Item objItem;
    private boolean acervo = false;
    private String[] caminhosImagens;

    private DatabaseHelper databaseHelper;

    @ViewById(R.id.pager)
    ViewPager viewPager;

    private QuestionarioFragmentPageAdapter questionarioAdapter;

    @ViewById(R.id.btnVoltar)
    Button btnVoltar;

    @ViewById(R.id.txtUsuario)
    TextView txtUsuario;

    @Click
    public void btnVoltar(View view) {

        this.finish();
    }

    @AfterViews
    void init() {

        txtUsuario.setText(getUsuario().getNome());

        objCliente = (Cliente) getIntent().getSerializableExtra("cliente");

        objLocal = (Local) getIntent().getSerializableExtra("local");

        objItem = (Item) getIntent().getSerializableExtra("item");

        TextView titulo = (TextView) findViewById(R.id.lblTituloClienteLocal);

        titulo.setText(objCliente.getNome() + " - " + objItem.getDescricao());

        QueryBuilder<Questionario, String> builder = getHelper().getQuestionarioRuntimeDAO().queryBuilder();

        try {

            builder.where().eq("itemId", objItem.getId().toString());

            lstQuestionario = getHelper().getQuestionarioRuntimeDAO().query(builder.prepare());

            QueryBuilder<Resposta, String> respostaBuilder = null;


            for (Questionario questionario : lstQuestionario) {

                respostaBuilder = getHelper().getRespostaRuntimeDAO().queryBuilder();

                respostaBuilder.where().eq("questionarioId", questionario.getId());

                questionario.setRespostas(getHelper().getRespostaRuntimeDAO().query(respostaBuilder.prepare()));

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        questionarioAdapter = new QuestionarioFragmentPageAdapter(getSupportFragmentManager(), lstQuestionario);

        caminhosImagens = new String[lstQuestionario.size()];

        viewPager.setAdapter(questionarioAdapter);


    }

    public Usuario getUsuario() {
        Usuario usuario = ((HuntVisionApp) getApplication()).getUsuario();
        return getHelper().getUsuarioRuntimeDAO().queryForId(usuario.getId());
    }


    public void openCamera(Questionario questionario) {
        acervo = false;

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "Nova foto");
        values.put(MediaStore.Images.Media.DESCRIPTION, "de sua câmera");
        selectedImage = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, selectedImage);
        startActivityForResult(intent, lstQuestionario.indexOf(questionario));

    }

    public void openAcervo(Questionario questionario) {
        acervo = true;
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Galeria"), lstQuestionario.indexOf(questionario));

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {

        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        this.getURI(requestCode, resultCode, imageReturnedIntent);

        if (selectedImage != null) {

            try {

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);

                String imageName = "huntvision_resposta_" + System.currentTimeMillis() + ".jpg";

                createImageTmp(imageName, bitmap);

                caminhosImagens[requestCode] = imageName;

            } catch (Exception e) {

                e.printStackTrace();

            }

        }
    }

    private void getURI(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {

            if (acervo) {

                selectedImage = data.getData();

            } else if (data != null) {

                selectedImage = null;

                Bundle extras = data.getExtras();

                Bitmap bitmap = (Bitmap) extras.get("data");

                String imageName = "huntvision_resposta_" + System.currentTimeMillis() + ".jpg";

                createImageTmp(imageName, bitmap);

                caminhosImagens[requestCode] = imageName;

            }

        }

    }

    @Click
    void salvarQuestionario(View view) {

        if (!validarPerguntas()) {

            return;

        }

        if (!getApp().getChanged()) {

            Toast.makeText(this, "Não foi possível obter sua localização atual, procure um local aberto!", Toast.LENGTH_LONG).show();

            return;

        }

        Vistoria vistoria = new Vistoria();

        vistoria.setData(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));

        vistoria.setFlagSincronizado(0);

        vistoria.setClienteId(objCliente.getId());

        vistoria.setItemId(objItem.getId());

        vistoria.setUsuarioId(getApp().getUsuario().getId());

        vistoria.setRespostas(new ArrayList<VistoriaResposta>());

        vistoria.setLatitude(getApp().getLatitude());

        vistoria.setLongitude(getApp().getLongitude());

        getHelper().getVistoriaRuntimeDAO().create(vistoria);

        VistoriaResposta vistoriaResposta = null;

        for (Questionario questionario : lstQuestionario) {

            for (Resposta resposta : questionario.getRespostas()) {

                if (resposta.getFlagRespostaCerta() != null && resposta.getFlagRespostaCerta()) {

                    vistoriaResposta = new VistoriaResposta();

                    vistoriaResposta.setRespostaId(resposta.getId());

                    vistoriaResposta.setVistoriaId(vistoria.getId().toString());

                    vistoriaResposta.setObservacao(questionario.getObservacao());

                    vistoriaResposta.setImagem(caminhosImagens[lstQuestionario.indexOf(questionario)]);

                    getHelper().getVistoriaRespostaRuntimeDAO().create(vistoriaResposta);

                    break;

                }

            }

        }

        Toast.makeText(this, "Pronto! Questionário respondido e salvo com sucesso", Toast.LENGTH_LONG).show();

        finish();

    }

    private void cleanTmpDirectory() {

        for (int i = 0; i < caminhosImagens.length; i++) {

            if (!TextUtils.isEmpty(caminhosImagens[i])) {

                File file = new File(getApp().getTmpDataFolder() + "/" + caminhosImagens[i]);

                if (file.exists()) {

                    file.renameTo(new File(getApp().getDataFolder() + "/" + caminhosImagens[i]));

                }

            }

        }

        for (File file : getApp().getTmpDataFolder().listFiles()) {

            file.delete();

        }

    }

    private boolean validarPerguntas() {

        boolean questionarioRespondido = false;

        for (Questionario questionario : lstQuestionario) {

            for (Resposta resposta : questionario.getRespostas()) {

                if (resposta.getFlagRespostaCerta() != null && resposta.getFlagRespostaCerta()) {

                    questionarioRespondido = true;

                    break;

                }

            }

            if (!questionarioRespondido) {

                Toast.makeText(this, "A pergunta " + (lstQuestionario.indexOf(questionario) + 1) + " não foi respondida", Toast.LENGTH_SHORT).show();

                return false;

            }

            questionarioRespondido = false;

        }

        return true;

    }

    private void createImageTmp(String imageName, Bitmap bitmap) {

        try {

            File folder = getApp().getTmpDataFolder();

            File file = new File(folder, imageName);

            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));

            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bos);

            bos.flush();

            bos.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);

    }

    public void backPressed(View view) {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OpenHelperManager.releaseHelper();
        cleanTmpDirectory();
    }

    DatabaseHelper getHelper() {
        return databaseHelper;
    }

    public Questionario getQuestionario(Questionario questionario) {
        return lstQuestionario.get(lstQuestionario.indexOf(questionario));
    }

    public Bitmap getImage(Questionario questionario) {

        int position = lstQuestionario.indexOf(questionario);

        String caminhoImagem = caminhosImagens[position];

        HuntVisionApp app = (HuntVisionApp) getApplication();

        File imgFile = new File(app.getTmpDataFolder() + "/" + caminhoImagem);

        if (imgFile.exists()) {

            return BitmapFactory.decodeFile(imgFile.getAbsolutePath());

        }

        return null;

    }

    private HuntVisionApp getApp() {
        return (HuntVisionApp) getApplication();
    }

}
