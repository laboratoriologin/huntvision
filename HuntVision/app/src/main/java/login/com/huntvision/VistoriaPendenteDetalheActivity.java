package login.com.huntvision;

import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.sql.SQLException;

import login.com.huntvision.models.Item;
import login.com.huntvision.models.Questionario;
import login.com.huntvision.models.Resposta;
import login.com.huntvision.models.Vistoria;
import login.com.huntvision.models.VistoriaResposta;
import login.com.huntvision.view.adapters.VistoriaDetalheAdapter;

@EActivity(R.layout.activity_vistoria_pendente_detalhe)
public class VistoriaPendenteDetalheActivity extends DefaultActivity {

    @ViewById(R.id.listview)
    ListView listView;

    @ViewById(R.id.txtUsuario)
    TextView txtUsuario;

    private Vistoria vistoria;

    @AfterViews
    void init() {

        txtUsuario.setText(getUsuario().getNome());


        vistoria = (Vistoria) getIntent().getSerializableExtra("vistoria");

        try {

            QueryBuilder<VistoriaResposta, String> builder = getHelper().getVistoriaRespostaRuntimeDAO().queryBuilder();

            builder.where().eq("vistoriaId", vistoria.getId());

            vistoria.setRespostas(getHelper().getVistoriaRespostaRuntimeDAO().query(builder.prepare()));

            QueryBuilder<Resposta, String> respostaQueryBuilder = null;

            QueryBuilder<Questionario, String> questionarioQueryBuilder = null;

            for (VistoriaResposta resposta : vistoria.getRespostas()) {

                respostaQueryBuilder = getHelper().getRespostaRuntimeDAO().queryBuilder();

                questionarioQueryBuilder = getHelper().getQuestionarioRuntimeDAO().queryBuilder();

                respostaQueryBuilder.where().eq("id", resposta.getRespostaId());

                resposta.setResposta(getHelper().getRespostaRuntimeDAO().queryForFirst(respostaQueryBuilder.prepare()));

                questionarioQueryBuilder.where().eq("id", resposta.getResposta().getQuestionarioId());

                resposta.getResposta().setQuestionario(getHelper().getQuestionarioRuntimeDAO().queryForFirst(questionarioQueryBuilder.prepare()));

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        HuntVisionApp app = (HuntVisionApp) getApplication();

        listView.setAdapter(new VistoriaDetalheAdapter(vistoria.getRespostas(), this, app.getDataFolder().getPath()));

        QueryBuilder<Item, String> itemQueryBuilder = getHelper().getItemRuntimeDAO().queryBuilder();

        TextView textViewData = (TextView) findViewById(R.id.lblData);

        TextView textView = (TextView) findViewById(R.id.lblTituloClienteLocal);

        textView.setText(vistoria.getCliente().getNome());

        textViewData.setText("Feita em " + vistoria.getData());

        String textoCliente = vistoria.getCliente().getNome();

        try {

            itemQueryBuilder.where().eq("id", vistoria.getRespostas().get(0).getResposta().getQuestionario().getItemId());

            Item item = getHelper().getItemRuntimeDAO().queryForFirst(itemQueryBuilder.prepare());

            if (item != null) {

                textoCliente = textoCliente + " - " + item.getDescricao();

            }

        } catch (SQLException e) {

        }

        textView.setText(textoCliente);

    }

    @Click
    void excluir(View view) {

        try {

            DeleteBuilder<VistoriaResposta, String> builder = getHelper().getVistoriaRespostaRuntimeDAO().deleteBuilder();

            builder.where().eq("vistoriaId", vistoria.getId());

            builder.delete();

            DeleteBuilder<Vistoria,Long> deleteBuilderVistoria = getHelper().getVistoriaRuntimeDAO().deleteBuilder();

            deleteBuilderVistoria.where().eq("id",vistoria.getId());

            deleteBuilderVistoria.delete();

            Toast.makeText(this,"Vistoria excluída com sucesso",Toast.LENGTH_SHORT).show();

            finish();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void backPressed(View view) {
        super.onBackPressed();
    }

}