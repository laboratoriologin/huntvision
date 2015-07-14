package login.com.huntvision;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.stmt.DeleteBuilder;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.apache.james.mime4j.field.datetime.DateTime;

import java.sql.SQLException;
import java.util.Date;

import login.com.huntvision.models.Agenda;
import login.com.huntvision.models.ServerResponse;
import login.com.huntvision.network.AgendaRequest;
import login.com.huntvision.network.http.ResponseListener;

@EActivity(R.layout.activity_agenda_detalhe)
public class AgendaDetalheActivity extends DefaultActivity {

    @ViewById(R.id.activity_agenda_detalhe_data)
    TextView txtDetalheData;

    @ViewById(R.id.txtUsuario)
    TextView txtUsuario;

    @ViewById(R.id.activity_agenda_detalhe_local)
    TextView txtDetalheLocal;

    @ViewById(R.id.activity_agenda_detalhe_descricao)
    TextView txtDetalheDescricao;

    @ViewById(R.id.btnRegistrarChegada)
    Button btnRegistrarChegada;

    @ViewById(R.id.btnRegistrarSaida)
    Button btnRegistrarSaida;

    @ViewById(R.id.txtChegada)
    TextView txtChegada;

    @ViewById(R.id.txtSaida)
    TextView txtSaida;

    private Agenda agenda;

    @AfterViews
    void init() {

        txtUsuario.setText(getUsuario().getNome());

        agenda = (Agenda) getIntent().getSerializableExtra("agenda");

        txtDetalheData.setText("Agendado: " + agenda.getDataHora().toString());

        txtDetalheLocal.setText("Local: " + agenda.getCliente().getNome() + "\n" + agenda.getCliente().getEndereco());

        txtDetalheDescricao.setText("Descrição: " + agenda.getDescricao().toString());

        validaHora();
    }

    @Click
    void excluir(View view) {

            new AgendaRequest(getUrlWS(), new ResponseListener() {

                @Override
                public void onResult(ServerResponse serverResponse) {

                    if(serverResponse.isOK()) {

                        try {

                            DeleteBuilder<Agenda, String> builder = getHelper().getAgendaRuntimeDAO().deleteBuilder();

                            builder.where().eq("id", agenda.getId());

                            builder.delete();



                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                    } else {

                        Toast.makeText(AgendaDetalheActivity.this, "Sem conexão no momento", Toast.LENGTH_SHORT).show();

                    }

                }

            }).delete(agenda);

            Toast.makeText(this, "Agenda excluída com sucesso", Toast.LENGTH_SHORT).show();

            finish();


    }



    @Click
    void btnRegistrarChegada() {

        agenda.setDataHoraChegada(new Date());

        new AgendaRequest(getUrlWS(), new ResponseListener() {

            @Override
            public void onResult(ServerResponse serverResponse) {

                if (serverResponse.isOK()) {

                    getHelper().getAgendaRuntimeDAO().update(agenda);

                    Toast.makeText(AgendaDetalheActivity.this, "Data de chegada registrada com sucesso!", Toast.LENGTH_LONG).show();
                    validaHora();
                }
                else
                {
                    Toast.makeText(AgendaDetalheActivity.this, "É necessária a conexão com a internet! ", Toast.LENGTH_LONG).show();
                }
            }


    }).updateHoras(agenda);
    }


    @Click
    void btnRegistrarSaida()
    {
        agenda.setDataHoraSaida(new Date());

        new AgendaRequest(getUrlWS(), new ResponseListener() {

            @Override
            public void onResult(ServerResponse serverResponse) {

                if (serverResponse.isOK()) {


                    if (agenda.getDataHoraChegada() != null) {
                        getHelper().getAgendaRuntimeDAO().update(agenda);
                        Toast.makeText(AgendaDetalheActivity.this, "Data de saída registrada com sucesso!", Toast.LENGTH_LONG).show();
                        validaHora();
                    }
                    else
                    {
                        Toast.makeText(AgendaDetalheActivity.this, "Atenção, é necessario registrar hora de chegada antes!", Toast.LENGTH_LONG).show();

                    }
                }else
                {
                    Toast.makeText(AgendaDetalheActivity.this, "É necessária a conexão com a internet! ", Toast.LENGTH_LONG).show();
                }
            }


        }).updateHoras(agenda);
    }


    private void validaHora()
    {
        if(agenda.getDataHoraChegada() == null) {
            txtChegada.setText("");
        }
        else
        {
            txtChegada.setText(agenda.getDataHoraChegada().toString());
        }

        if(agenda.getDataHoraSaida() == null) {
            txtSaida.setText("");
        }
        else
        {
            txtSaida.setText( agenda.getDataHoraSaida().toString());
        }
    }
    public void backPressed(View view) {
        super.onBackPressed();
    }



}