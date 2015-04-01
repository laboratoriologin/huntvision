package login.com.huntvision;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.stmt.QueryBuilder;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
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
import login.com.huntvision.view.fragments.QuestionarioFragment;

@EActivity(R.layout.activity_questionario)
public class QuestionarioActivity extends FragmentActivity {

    private Uri selectedImage;
    List<Questionario> lstQuestionario;
    private Cliente objCliente;
    private Local objLocal;
    private Item objItem;
    private boolean acervo = false;
    private Bitmap[] bitmaps;
    private InputStream[] streams;

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

        titulo.setText(objCliente.getNome() + " - " +objItem.getDescricao());

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

        bitmaps = new Bitmap[lstQuestionario.size()];

        streams = new InputStream[lstQuestionario.size()];

        viewPager.setAdapter(questionarioAdapter);


    }
    public Usuario getUsuario() {
        Usuario usuario =  ((HuntVisionApp) getApplication()).getUsuario();
        return getHelper().getUsuarioRuntimeDAO().queryForId(usuario.getId());
    }
    public InputStream rotateImage(Bitmap fotoUpload, ImageView imagemUpload) {

        Matrix matrix = new Matrix();

        matrix.postRotate(90);

        fotoUpload = Bitmap.createBitmap(fotoUpload, 0, 0, fotoUpload.getWidth(), fotoUpload.getHeight(), matrix, true);

        imagemUpload.setImageBitmap(fotoUpload);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        fotoUpload.compress(Bitmap.CompressFormat.PNG, 0 /* ignored for PNG */, bos);

        return new ByteArrayInputStream(bos.toByteArray());

    }

    public void openCamera(Questionario questionario) {
        acervo = false;

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
        selectedImage = getContentResolver().insert( MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
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

            InputStream inputStream = null;

            try {

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);

                inputStream = getContentResolver().openInputStream(selectedImage);

                ByteArrayOutputStream bos = new ByteArrayOutputStream();

                bitmap.compress(Bitmap.CompressFormat.JPEG, 100 , bos);

                bitmaps[requestCode] = bitmap;

                byte[] bitmapdata = bos.toByteArray();

                streams[requestCode] = new ByteArrayInputStream(bitmapdata);


            } catch (Exception e) {

                e.printStackTrace();

            }

        }
    }

    private void getURI(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {

            if (acervo) {

                selectedImage = data.getData();

            } else if(data!=null) {

                selectedImage = null;

                Bundle extras = data.getExtras();

                Bitmap bitmap = (Bitmap) extras.get("data");

                ByteArrayOutputStream bos = new ByteArrayOutputStream();

                bitmap.compress(Bitmap.CompressFormat.JPEG, 100 , bos);

                bitmaps[requestCode] = bitmap;

                byte[] bitmapdata = bos.toByteArray();

                streams[requestCode] = new ByteArrayInputStream(bitmapdata);


            }

        }

    }

    @Click
    void salvarQuestionario(View view) {

        if (!validarPerguntas()) {

            return;

        }

        Vistoria vistoria = new Vistoria();

        vistoria.setData(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));

        vistoria.setFlagSincronizado(0);

        vistoria.setClienteId(objCliente.getId());

        vistoria.setItemId(objItem.getId());

        HuntVisionApp app = (HuntVisionApp) getApplication();

        vistoria.setUsuarioId(app.getUsuario().getId());

        vistoria.setRespostas(new ArrayList<VistoriaResposta>());

        getHelper().getVistoriaRuntimeDAO().create(vistoria);

        VistoriaResposta vistoriaResposta = null;

        for (Questionario questionario : lstQuestionario) {

            for (Resposta resposta : questionario.getRespostas()) {

                if (resposta.getFlagRespostaCerta() != null && resposta.getFlagRespostaCerta()) {

                    vistoriaResposta = new VistoriaResposta();

                    vistoriaResposta.setRespostaId(resposta.getId());

                    vistoriaResposta.setVistoriaId(vistoria.getId().toString());

                    vistoriaResposta.setObservacao(questionario.getObservacao());

                    vistoriaResposta.setImagem(createImage(questionario));

                    getHelper().getVistoriaRespostaRuntimeDAO().create(vistoriaResposta);

                    break;

                }

            }

        }

        Toast.makeText(this,"Pronto! Questionário respondido e salvo com sucesso",Toast.LENGTH_LONG).show();

        finish();

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

    private String createImage(Questionario questionario) {

        int index = lstQuestionario.indexOf(questionario);

        if(getImage(questionario) == null) {
            return null;
        }

        String imageName = "huntvision_resposta_" + System.currentTimeMillis() + ".jpg";

        try {

            InputStream stream = getStream(questionario);

            HuntVisionApp app = (HuntVisionApp) getApplication();

            File folder = app.getDataFolder();

            File file = new File(folder, imageName);

            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));

            getImage(questionario).compress(Bitmap.CompressFormat.JPEG, 50, bos);

            bos.flush();

            bos.close();

        } catch (Exception e) {

            e.printStackTrace();

            return null;

        }

        return imageName;

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
    }

    DatabaseHelper getHelper() {
        return databaseHelper;
    }

    public Questionario getQuestionario(Questionario questionario) {
        return lstQuestionario.get(lstQuestionario.indexOf(questionario));
    }

    public Bitmap getImage(Questionario questionario) {

        int position = lstQuestionario.indexOf(questionario);

        return bitmaps[position];

    }


    public InputStream getStream(Questionario questionario) {

        int position = lstQuestionario.indexOf(questionario);

        return streams[position];

    }


}
