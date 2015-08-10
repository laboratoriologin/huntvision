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
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import login.com.huntvision.models.Agenda;
import login.com.huntvision.models.Cliente;
import login.com.huntvision.models.Imagem;
import login.com.huntvision.models.ServerResponse;
import login.com.huntvision.models.Vistoria;
import login.com.huntvision.models.VistoriaResposta;
import login.com.huntvision.network.AgendaRequest;
import login.com.huntvision.network.VistoriaRequest;
import login.com.huntvision.network.http.InputStreamWrapper;
import login.com.huntvision.network.http.ResponseListener;
import login.com.huntvision.utils.JsonUtil;
import login.com.huntvision.view.adapters.AgendaAdapter;
import login.com.huntvision.view.adapters.VistoriaAdapter;

@EActivity(R.layout.activity_agenda)
public class AgendaActivity extends DefaultActivity {

    @ViewById(R.id.agenda_listview)
    ListView agenda_listview;

    @ViewById(R.id.btnVoltar)
    Button btnVoltar;
    @ViewById(R.id.txtUsuario)
    TextView txtUsuario;

    @ViewById(R.id.edtTextPesquisa)
    EditText filter;

    private List<Agenda> agendas;
    private AgendaAdapter agendaAdapter;
    private ProgressDialog progressDialog;

    @Click
    public void btnVoltar(View view) {

        this.finish();
    }

    @AfterViews
    void init() {

        setTitle("Agenda");

        this.filter.setText("");

        txtUsuario.setText(getUsuario().getNome());

        agendas = getHelper().getAgendaRuntimeDAO().queryForAll();

        QueryBuilder<Cliente, String> queryBuilderCliente = getHelper().getClienteRuntimeDAO().queryBuilder();

        for (Agenda agenda : agendas) {

            try {

                agenda.setCliente(getHelper().getClienteRuntimeDAO().queryForFirst(queryBuilderCliente.where().eq("id", agenda.getClienteId()).prepare()));

            } catch (SQLException e) {


            }

        }

        this.agendaAdapter = new AgendaAdapter(agendas, this);

        agenda_listview.setAdapter(this.agendaAdapter);

        agenda_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(AgendaActivity.this, AgendaDetalheActivity_.class);
                intent.putExtra("agenda", (Agenda) agendaAdapter.getItem(position));

                startActivity(intent);

            }
        });

        filter.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                agendaAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        this.agendaAdapter.notifyDataSetChanged();
    }

    private void sincronizarAgendas() {

        progressDialog = ProgressDialog.show(this, "Aguarde", "Sincronizando agendas...");

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

                    }

                    init();

                } else {

                    progressDialog.dismiss();

                    Toast.makeText(AgendaActivity.this, "Ocorreu um erro, tente novamente", Toast.LENGTH_LONG).show();

                }

            }

        }).getAll(new Agenda());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_agenda, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {

            NavUtils.navigateUpFromSameTask(this);

        }

        if (item.getItemId() == R.id.refresh) {

            sincronizarAgendas();

        }

        return true;

    }

}
