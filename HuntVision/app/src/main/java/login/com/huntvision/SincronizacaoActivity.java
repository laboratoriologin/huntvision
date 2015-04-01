package login.com.huntvision;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import login.com.huntvision.models.Cliente;
import login.com.huntvision.models.Imagem;
import login.com.huntvision.models.Item;
import login.com.huntvision.models.ItemLocal;
import login.com.huntvision.models.Questionario;
import login.com.huntvision.models.Resposta;
import login.com.huntvision.models.ServerResponse;
import login.com.huntvision.models.TipoQuestionario;
import login.com.huntvision.models.Usuario;
import login.com.huntvision.models.Local;
import login.com.huntvision.network.ClienteRequest;
import login.com.huntvision.network.ImagemRequest;
import login.com.huntvision.network.ItemLocalRequest;
import login.com.huntvision.network.ItemRequest;
import login.com.huntvision.network.LocalRequest;
import login.com.huntvision.network.QuestionarioRequest;
import login.com.huntvision.network.RespostaRequest;
import login.com.huntvision.network.TipoQuestionarioRequest;
import login.com.huntvision.network.UsuarioRequest;
import login.com.huntvision.network.http.ResponseListener;
import login.com.huntvision.utils.Constantes;
import login.com.huntvision.utils.JsonUtil;
import login.com.huntvision.utils.Utilitarios;


@EActivity(R.layout.activity_sincronizacao)
public class SincronizacaoActivity extends DefaultActivity {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle icicle) {

        super.onCreate(icicle);

        setContentView(R.layout.activity_sincronizacao);

        this.sincronizarUsuario(null);

    }

    @Click
    void sincronizarUsuario(View view) {

        progressDialog = ProgressDialog.show(this, "Aguarde", "Sincronizando usuários...");

        new UsuarioRequest(new ResponseListener() {

            @Override
            public void onResult(ServerResponse serverResponse) {

                try {

                    getHelper().getUsuarioRuntimeDAO().deleteBuilder().delete();

                } catch (SQLException e) {

                    e.printStackTrace();

                }

                if (serverResponse.isOK()) {

                    for (Usuario usuario : JsonUtil.usuariosFromJsonObject((JSONObject) serverResponse.getReturnObject())) {

                        getHelper().getUsuarioRuntimeDAO().create(usuario);

                    }

                    sincronizarCliente();

                } else {

                    progressDialog.dismiss();

                    Toast.makeText(SincronizacaoActivity.this, "Ocorreu um erro, tente novamente", Toast.LENGTH_LONG).show();

                }

            }

        }).getAll(new Usuario());


    }


    private void sincronizarCliente() {

        progressDialog.setMessage("Sincronizando clientes...");

        new ClienteRequest(new ResponseListener() {

            @Override
            public void onResult(ServerResponse serverResponse) {

                if (serverResponse.isOK()) {

                    try {

                        getHelper().getClienteRuntimeDAO().deleteBuilder().delete();

                    } catch (SQLException e) {

                        e.printStackTrace();

                    }

                    for (Cliente cliente : JsonUtil.clientesFromJsonObject((JSONObject) serverResponse.getReturnObject())) {

                        getHelper().getClienteRuntimeDAO().create(cliente);

                    }

                    sincronizarLocal();

                } else {

                    progressDialog.dismiss();

                    Toast.makeText(SincronizacaoActivity.this, "Ocorreu um erro", Toast.LENGTH_SHORT).show();

                }

            }

        }).getAll(new Cliente());


    }


    private void sincronizarLocal() {

        progressDialog.setMessage("Sincronizando locais...");

        new LocalRequest(new ResponseListener() {

            @Override
            public void onResult(ServerResponse serverResponse) {

                if (serverResponse.isOK()) {

                    try {

                        getHelper().getLocalRuntimeDAO().deleteBuilder().delete();

                    } catch (SQLException e) {

                        e.printStackTrace();

                    }

                    for (Local local : JsonUtil.locaisFromJsonObject((JSONObject) serverResponse.getReturnObject())) {

                        getHelper().getLocalRuntimeDAO().create(local);

                    }

                    sincronizarItem();

                } else {

                    progressDialog.dismiss();

                    Toast.makeText(SincronizacaoActivity.this, "Ocorreu um erro, tente novamente", Toast.LENGTH_LONG).show();


                }

            }

        }).getAll(new Local());


    }

    private void sincronizarItem() {

        progressDialog.setMessage("Sincronizando itens...");

        new ItemRequest(new ResponseListener() {

            @Override
            public void onResult(ServerResponse serverResponse) {

                if (serverResponse.isOK()) {

                    try {

                        getHelper().getItemRuntimeDAO().deleteBuilder().delete();

                    } catch (SQLException e) {

                        e.printStackTrace();

                    }

                    for (Item item : JsonUtil.itemsFromJsonObject((JSONObject) serverResponse.getReturnObject())) {

                        getHelper().getItemRuntimeDAO().create(item);

                    }

                    sincronizarItemLocal();

                } else {

                    progressDialog.dismiss();

                    Toast.makeText(SincronizacaoActivity.this, "Ocorreu um erro, tente novamente", Toast.LENGTH_LONG).show();

                }

            }

        }).getAll(new Item());


    }

    private void sincronizarItemLocal() {

        new ItemLocalRequest(new ResponseListener() {

            @Override
            public void onResult(ServerResponse serverResponse) {

                if (serverResponse.isOK()) {

                    try {

                        getHelper().getItemLocalRuntimeDAO().deleteBuilder().delete();

                    } catch (SQLException e) {

                        e.printStackTrace();

                    }

                    JSONObject retorno = (JSONObject) serverResponse.getReturnObject();

                    for (ItemLocal itemLocal : JsonUtil.itensLocaisFromJsonObject(retorno)) {

                        getHelper().getItemLocalRuntimeDAO().create(itemLocal);

                    }

                    sincronizarQuestionario();

                } else {

                    progressDialog.dismiss();

                    Toast.makeText(SincronizacaoActivity.this, "Ocorreu um erro, tente novamente", Toast.LENGTH_LONG).show();

                }

            }

        }).getAll(new ItemLocal());


    }



    private void sincronizarQuestionario() {

        progressDialog.setMessage("Sincronizando dados dos questionários...");

        new QuestionarioRequest(new ResponseListener() {

            @Override
            public void onResult(ServerResponse serverResponse) {

                if (serverResponse.isOK()) {

                    try {

                        getHelper().getQuestionarioRuntimeDAO().deleteBuilder().delete();

                    } catch (SQLException e) {

                        e.printStackTrace();

                    }

                    for (Questionario questionario : JsonUtil.questionariosFromJsonObject((JSONObject) serverResponse.getReturnObject())) {

                        getHelper().getQuestionarioRuntimeDAO().create(questionario);

                    }

                    sincronizarTipoQuestionario() ;

                } else {

                    progressDialog.dismiss();

                    Toast.makeText(SincronizacaoActivity.this, "Ocorreu um erro, tente novamente", Toast.LENGTH_LONG).show();


                }

            }

        }).getAll(new Questionario());


    }


    private void sincronizarTipoQuestionario() {

        new TipoQuestionarioRequest(new ResponseListener() {

            @Override
            public void onResult(ServerResponse serverResponse) {

                if (serverResponse.isOK()) {

                    try {

                        getHelper().getTipoQuestionarioRuntimeDAO().deleteBuilder().delete();

                    } catch (SQLException e) {

                        e.printStackTrace();

                    }

                    for (TipoQuestionario tipoQuestionario : JsonUtil.tipoQuestionariosFromJsonObject((JSONObject) serverResponse.getReturnObject())) {

                        getHelper().getTipoQuestionarioRuntimeDAO().create(tipoQuestionario);

                    }

                    sincronizarRespostas();

                } else {

                    progressDialog.dismiss();

                    Toast.makeText(SincronizacaoActivity.this, "Ocorreu um erro, tente novamente", Toast.LENGTH_LONG).show();

                }

            }

        }).getAll(new TipoQuestionario());


    }


    private void sincronizarImagens() {

        new ImagemRequest(new ResponseListener() {

            @Override
            public void onResult(ServerResponse serverResponse) {

                if (serverResponse.isOK()) {

                    try {

                        getHelper().getImagemRuntimeDAO().deleteBuilder().delete();

                    } catch (SQLException e) {

                        e.printStackTrace();

                    }

                    for (Imagem imagem : JsonUtil.imagemsFromJsonObject((JSONObject) serverResponse.getReturnObject())) {

                        getHelper().getImagemRuntimeDAO().create(imagem);

                    }



                } else {

                    progressDialog.dismiss();

                    Toast.makeText(SincronizacaoActivity.this, "Ocorreu um erro, tente novamente", Toast.LENGTH_LONG).show();

                }

            }

        }).getAll(new Imagem());


    }


    private void sincronizarRespostas() {

        progressDialog.setMessage("Finalizando sincronização...");

        new RespostaRequest(new ResponseListener() {

            @Override
            public void onResult(ServerResponse serverResponse) {

                progressDialog.dismiss();

                if (serverResponse.isOK()) {

                    try {

                        getHelper().getRespostaRuntimeDAO().deleteBuilder().delete();

                    } catch (SQLException e) {

                        e.printStackTrace();

                    }

                    for (Resposta resposta : JsonUtil.respostasFromJsonObject((JSONObject) serverResponse.getReturnObject())) {

                        getHelper().getRespostaRuntimeDAO().create(resposta);

                    }

                    startLoginActivity();

                } else {

                    progressDialog.dismiss();

                    Toast.makeText(SincronizacaoActivity.this, "Ocorreu um erro, tente novamente", Toast.LENGTH_LONG).show();

                }

            }

        }).getAll(new Resposta());


    }

    private void startLoginActivity() {

        Toast.makeText(SincronizacaoActivity.this, "Sincronização feita com sucesso!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(SincronizacaoActivity.this,LoginActivity_.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(intent);

        finish();

    }

}
