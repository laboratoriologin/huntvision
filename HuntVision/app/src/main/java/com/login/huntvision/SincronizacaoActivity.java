package com.login.huntvision;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.login.huntvision.managers.alarms.AlarmScheduler;
import com.login.huntvision.models.Agenda;
import com.login.huntvision.models.Cliente;
import com.login.huntvision.models.Conexao;
import com.login.huntvision.models.Item;
import com.login.huntvision.models.ItemLocal;
import com.login.huntvision.models.Local;
import com.login.huntvision.models.Questionario;
import com.login.huntvision.models.Resposta;
import com.login.huntvision.models.ServerResponse;
import com.login.huntvision.models.TipoQuestionario;
import com.login.huntvision.models.Usuario;
import com.login.huntvision.models.Vistoria;
import com.login.huntvision.network.AgendaRequest;
import com.login.huntvision.network.ClienteRequest;
import com.login.huntvision.network.ItemLocalRequest;
import com.login.huntvision.network.ItemRequest;
import com.login.huntvision.network.LocalRequest;
import com.login.huntvision.network.QuestionarioRequest;
import com.login.huntvision.network.RespostaRequest;
import com.login.huntvision.network.TipoQuestionarioRequest;
import com.login.huntvision.network.UsuarioRequest;
import com.login.huntvision.network.http.ResponseListener;
import com.login.huntvision.utils.Constantes;
import com.login.huntvision.utils.JsonUtil;


@EActivity(R.layout.activity_sincronizacao)
public class SincronizacaoActivity extends DefaultActivity {
    private Conexao objConexao;
    private ProgressDialog progressDialog;
    private List<Conexao> conexaos;

    @ViewById(R.id.rlConexao)
    RelativeLayout rlConexao;

    @ViewById
    RelativeLayout rlGeraChave;

    @ViewById
    Button btnGeraChave;

    @ViewById(R.id.spConexao)
    Spinner spinner;

    @ViewById(R.id.txtChave)
    EditText txtChave;

    @Click
    void sincronizarUsuario(View view) {

        if (TextUtils.isEmpty(getUrlWS())) {

            Toast.makeText(this, "Nenhuma chave no sistema. É necessário incluir uma chava primeiro", Toast.LENGTH_SHORT).show();

            return;

        }

        for (Vistoria vistoria : getHelper().getVistoriaRuntimeDAO().queryForAll()) {

            if (vistoria.getFlagSincronizado() == 0) {

                Toast.makeText(this, "É necessário concluir todas as visitas antes de sincronizar", Toast.LENGTH_SHORT).show();

                return;

            }

        }
        progressDialog = ProgressDialog.show(this, "Aguarde", "Sincronizando usuários...");

        new UsuarioRequest(getUrlWS(), new ResponseListener() {

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

        new ClienteRequest(getUrlWS(), new ResponseListener() {

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

        new LocalRequest(getUrlWS(), new ResponseListener() {

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

        new ItemRequest(getUrlWS(), new ResponseListener() {

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

        new ItemLocalRequest(getUrlWS(), new ResponseListener() {

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

        new QuestionarioRequest(getUrlWS(), new ResponseListener() {

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

                    sincronizarTipoQuestionario();

                } else {

                    progressDialog.dismiss();

                    Toast.makeText(SincronizacaoActivity.this, "Ocorreu um erro, tente novamente", Toast.LENGTH_LONG).show();


                }

            }

        }).getAll(new Questionario());


    }


    private void sincronizarTipoQuestionario() {

        progressDialog.setMessage("Sincronizando dados dos tipo questionários...");

        new TipoQuestionarioRequest(getUrlWS(), new ResponseListener() {

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

    private void sincronizarRespostas() {

        progressDialog.setMessage("Sincronizando dados dos respostas...");

        new RespostaRequest(getUrlWS(), new ResponseListener() {

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

                    sincronizarAgendas();


                } else {

                    progressDialog.dismiss();

                    Toast.makeText(SincronizacaoActivity.this, "Ocorreu um erro, tente novamente", Toast.LENGTH_LONG).show();

                }

            }

        }).getAll(new Resposta());

    }

    private void sincronizarAgendas() {

        progressDialog.setMessage("Finalizando sincronização...");

        new AgendaRequest(getUrlWS(), new ResponseListener() {

            @Override
            public void onResult(ServerResponse serverResponse) {

                progressDialog.dismiss();

                if (serverResponse.isOK()) {

                    try {

                        getHelper().getAgendaRuntimeDAO().deleteBuilder().delete();

                    } catch (SQLException e) {

                        e.printStackTrace();

                    }

                    for (Agenda agenda : JsonUtil.agendasFromJsonObject((JSONObject) serverResponse.getReturnObject())) {

                        getHelper().getAgendaRuntimeDAO().create(agenda);

                        agenda.setCliente(getHelper().getClienteRuntimeDAO().queryForId(agenda.getClienteId().toString()));

                        if (agenda.getCliente() != null && agenda.getDataHoraChegada() == null) {

                            AlarmScheduler.schedule(agenda, SincronizacaoActivity.this);

                        }

                    }

                    startLoginActivity();

                } else {

                    progressDialog.dismiss();

                    Toast.makeText(SincronizacaoActivity.this, "Ocorreu um erro, tente novamente", Toast.LENGTH_LONG).show();

                }

            }

        }).getAll(new Agenda());


    }


    @AfterViews
    public void populateSpinner() {
        Typeface helveticaBold;
        Typeface helveticaRegular;


        helveticaBold = Typeface.createFromAsset(getAssets(), "Agencyb.ttf");
        helveticaRegular = Typeface.createFromAsset(getAssets(), "Agencyr.ttf");

        txtChave.setTypeface(helveticaRegular);

        objConexao = new Conexao();
        conexaos = new ArrayList<Conexao>();

        objConexao.setId("1");
        objConexao.setUrl(Constantes.URL_WS_GOLD);

        conexaos.add(objConexao);


        objConexao = new Conexao();
        objConexao.setId("2");
        objConexao.setUrl(Constantes.URL_WS_OTIMIZE);

        conexaos.add(objConexao);

        objConexao = new Conexao();
        objConexao.setId("3");
        objConexao.setUrl(Constantes.URL_WS_LOCAL);

        conexaos.add(objConexao);
        final ArrayAdapter<Conexao> adapter = new ArrayAdapter<Conexao>(this, android.R.layout.simple_spinner_item, conexaos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Click
    void selecionarUrl(View view) {

        setUrlWS(((Conexao) spinner.getSelectedItem()).getUrl());

        rlGeraChave.setVisibility(View.INVISIBLE);
        btnGeraChave.setVisibility(View.VISIBLE);
        rlConexao.setVisibility(View.INVISIBLE);

    }

    private void startLoginActivity() {

        Toast.makeText(SincronizacaoActivity.this, "Sincronização feita com sucesso!", Toast.LENGTH_SHORT).show();


        try {
            getHelper().getVistoriaRuntimeDAO().deleteBuilder().delete();
            getHelper().getVistoriaRespostaRuntimeDAO().deleteBuilder().delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(SincronizacaoActivity.this, LoginActivity_.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(intent);

        finish();

    }

    @Click
    void btnGeraChave() {
        rlGeraChave.setVisibility(View.VISIBLE);
        btnGeraChave.setVisibility(View.INVISIBLE);
    }

    @Click
    void btnAcessar() {
        if (txtChave.getText().toString().equals(Constantes.SECURITY_KEY)) {
            rlGeraChave.setVisibility(View.INVISIBLE);
            rlConexao.setVisibility(View.VISIBLE);
        } else

        {
            Toast.makeText(this, "Chave de segurança incorreta!", Toast.LENGTH_LONG).show();
        }
    }


}
