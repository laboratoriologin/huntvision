package login.com.huntvision;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.stmt.QueryBuilder;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import login.com.huntvision.models.Cliente;
import login.com.huntvision.models.Imagem;
import login.com.huntvision.models.ServerResponse;
import login.com.huntvision.models.Vistoria;
import login.com.huntvision.models.VistoriaResposta;
import login.com.huntvision.network.VistoriaRequest;
import login.com.huntvision.network.http.InputStreamWrapper;
import login.com.huntvision.network.http.ResponseListener;
import login.com.huntvision.view.adapters.VistoriaAdapter;

@EActivity(R.layout.activity_vistoria_pendente)
public class VistoriaPendenteActivity extends DefaultActivity {

    @ViewById(R.id.vistoria_pendente_listview)
    ListView pendentes;

    @ViewById(R.id.btnVoltar)
    Button btnVoltar;
    @ViewById(R.id.txtUsuario)
    TextView txtUsuario;

    @ViewById(R.id.edtTextPesquisa)
    EditText filter;

    private List<Vistoria> vistorias;

    private List<Vistoria> vistoriasToSend;

    private VistoriaAdapter vistoriaAdapter;

    private int positionToSend;

    private ProgressDialog progressDialog;

    @AfterViews
    void init() {

        setTitle("Concluir visita");

        txtUsuario.setText(getUsuario().getNome());

        vistorias = getHelper().getVistoriaRuntimeDAO().queryForAll();

        QueryBuilder<VistoriaResposta, String> queryBuilder = getHelper().getVistoriaRespostaRuntimeDAO().queryBuilder();

        QueryBuilder<Cliente, String> queryBuilderCliente = getHelper().getClienteRuntimeDAO().queryBuilder();

        QueryBuilder<Imagem, String> builderImagem = getHelper().getImagemRuntimeDAO().queryBuilder();

        for (Vistoria vistoria : vistorias) {

            try {

                vistoria.setRespostas(getHelper().getVistoriaRespostaRuntimeDAO().query(queryBuilder.where().eq("vistoriaId", vistoria.getId()).prepare()));

                for (VistoriaResposta resposta : vistoria.getRespostas()) {
                    builderImagem.where().eq("vistoriaRespostaId", resposta.getId());
                    resposta.setImagens(builderImagem.query());
                }

                vistoria.setCliente(getHelper().getClienteRuntimeDAO().queryForFirst(queryBuilderCliente.where().eq("id", vistoria.getClienteId()).prepare()));

            } catch (SQLException e) {

                vistoria.setRespostas(new ArrayList<VistoriaResposta>());

            }

        }

        this.vistoriaAdapter = new VistoriaAdapter(vistorias, this);

        pendentes.setAdapter(this.vistoriaAdapter);

        pendentes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(VistoriaPendenteActivity.this, VistoriaPendenteDetalheActivity_.class);

                intent.putExtra("vistoria", (Vistoria) vistoriaAdapter.getItem(position));

                startActivity(intent);

            }
        });

        filter.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                vistoriaAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        this.vistoriaAdapter.notifyDataSetChanged();
    }

    private void enviarVistoriasPendentes() {

        vistoriasToSend = new ArrayList<>();

        positionToSend = 0;

        for (Vistoria vistoria : vistoriaAdapter.getVistoriasFiltered()) {

            if (vistoria.getFlagSincronizado() == 0 || vistoria.getPendenteImagem() == 1) {

                vistoriasToSend.add(vistoria);

            }

        }

        if (vistoriasToSend.size() > 0) {

            progressDialog = ProgressDialog.show(this, "Aguarde", "Concluindo visitas...");

            enviarVistoria();

        } else {

            Toast.makeText(this, "Nenhuma visita pendente de envio", Toast.LENGTH_SHORT).show();

        }

    }

    private void enviarVistoria() {

        if (positionToSend < vistoriasToSend.size()) {

            final Vistoria vistoria = vistoriasToSend.get(positionToSend);

            if (vistoria.getFlagSincronizado() == 1 && vistoria.getPendenteImagem() == 1) {

                postImagens(vistoria, getImagens(vistoria));

                return;

            }

            new VistoriaRequest(getUrlWS(), new ResponseListener() {

                @Override
                public void onResult(ServerResponse serverResponse) {

                    if (serverResponse.isOK()) {

                        vistoria.setFlagSincronizado(1);

                        getHelper().getVistoriaRuntimeDAO().update(vistoria);

                        List<InputStreamWrapper> imagens = getImagens(vistoria);

                        if (imagens.size() > 0) {

                            postImagens(vistoria, imagens);

                            return;

                        }

                    }

                    positionToSend++;

                    enviarVistoria();

                }

            }).post(vistoria);

        } else {

            progressDialog.dismiss();

            Toast.makeText(this, "Pronto!", Toast.LENGTH_SHORT).show();

            this.init();

        }

    }

    private void postImagens(final Vistoria vistoria, List<InputStreamWrapper> imagens) {

        new VistoriaRequest(getUrlWS(), new ResponseListener() {

            @Override
            public void onResult(ServerResponse serverResponse) {

                positionToSend++;

                if (serverResponse.isOK()) {

                    vistoria.setPendenteImagem(0);

                } else {

                    vistoria.setPendenteImagem(1);

                }

                getHelper().getVistoriaRuntimeDAO().update(vistoria);


                enviarVistoria();

            }

        }).post(new Vistoria(), imagens);

    }

    private List<InputStreamWrapper> getImagens(Vistoria vistoria) {

        List<InputStreamWrapper> imagens = new ArrayList<>();

        InputStreamWrapper imagem = null;

        for (VistoriaResposta vistoriaResposta : vistoria.getRespostas()) {

            for (Imagem caminhoImagem : vistoriaResposta.getImagens()) {

                File imgFile = new File(getTmpDataFolder() + "/" + caminhoImagem.getCaminho());

                if (imgFile.exists()) {

                    imagem = new InputStreamWrapper();

                    imagem.setFilename(caminhoImagem.getCaminho());

                    try {

                        imagem.setStream(new FileInputStream(imgFile));

                        imagens.add(imagem);

                    } catch (FileNotFoundException e) {

                        e.printStackTrace();

                    }

                }

            }

        }

        return imagens;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_vistoria_pendente, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.concluir) {

            enviarVistoriasPendentes();

        }

        if (item.getItemId() == android.R.id.home) {

            NavUtils.navigateUpFromSameTask(this);

        }

        return true;

    }

}
