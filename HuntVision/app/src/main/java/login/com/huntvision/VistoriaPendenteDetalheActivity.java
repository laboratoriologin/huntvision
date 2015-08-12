package login.com.huntvision;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

import login.com.huntvision.models.Imagem;
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

        setTitle("Detalhe de vistoria");

        txtUsuario.setText(getUsuario().getNome());

        vistoria = (Vistoria) getIntent().getSerializableExtra("vistoria");

        try {

            QueryBuilder<VistoriaResposta, String> builder = getHelper().getVistoriaRespostaRuntimeDAO().queryBuilder();

            builder.where().eq("vistoriaId", vistoria.getId());

            vistoria.setRespostas(getHelper().getVistoriaRespostaRuntimeDAO().query(builder.prepare()));

            QueryBuilder<Resposta, String> respostaQueryBuilder = getHelper().getRespostaRuntimeDAO().queryBuilder();

            QueryBuilder<Questionario, String> questionarioQueryBuilder = getHelper().getQuestionarioRuntimeDAO().queryBuilder();

            QueryBuilder<Imagem, String> builderImagem = getHelper().getImagemRuntimeDAO().queryBuilder();

            for (VistoriaResposta resposta : vistoria.getRespostas()) {

                builderImagem.where().eq("vistoriaRespostaId", resposta.getId());

                resposta.setImagens(builderImagem.query());

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

    private void excluir() {

        try {

            DeleteBuilder<VistoriaResposta, String> builder = getHelper().getVistoriaRespostaRuntimeDAO().deleteBuilder();

            builder.where().eq("vistoriaId", vistoria.getId());

            builder.delete();

            DeleteBuilder<Vistoria, Long> deleteBuilderVistoria = getHelper().getVistoriaRuntimeDAO().deleteBuilder();

            deleteBuilderVistoria.where().eq("id", vistoria.getId());

            deleteBuilderVistoria.delete();

            Toast.makeText(this, "Vistoria excluída com sucesso", Toast.LENGTH_SHORT).show();

            finish();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void showImages(Questionario questionario) {

        Intent intent = new Intent(this, GaleriaActivity_.class);

        intent.putExtra("questionario", questionario);

        intent.putExtra("path", getDataFolder().getAbsolutePath());

        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_vistoria_pendente_detalhe, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.excluir) {

            excluir();

        }

        if (item.getItemId() == android.R.id.home) {

            NavUtils.navigateUpFromSameTask(this);

        }

        return true;

    }

}