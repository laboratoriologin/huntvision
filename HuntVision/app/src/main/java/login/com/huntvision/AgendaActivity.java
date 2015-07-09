package login.com.huntvision;

import android.app.ProgressDialog;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
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

import login.com.huntvision.models.Agenda;
import login.com.huntvision.models.Cliente;
import login.com.huntvision.models.Imagem;
import login.com.huntvision.models.ServerResponse;
import login.com.huntvision.models.Vistoria;
import login.com.huntvision.models.VistoriaResposta;
import login.com.huntvision.network.VistoriaRequest;
import login.com.huntvision.network.http.InputStreamWrapper;
import login.com.huntvision.network.http.ResponseListener;
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

    private int positionToSend;

    private ProgressDialog progressDialog;

    @Click
    public void btnVoltar(View view) {

        this.finish();
    }

    @AfterViews
    void init() {
        txtUsuario.setText(getUsuario().getNome());
    }

    @Override
    protected void onResume() {

        super.onResume();

        agendas = getHelper().getAgendaRuntimeDAO().queryForAll();

        QueryBuilder<Agenda, String> queryBuilder = getHelper().getAgendaRuntimeDAO().queryBuilder();

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


    public List<Agenda> getAgendas() {
        return agendas;
    }

    public void setAgendas(List<Agenda> agendas) {
        this.agendas = agendas;
    }
}
