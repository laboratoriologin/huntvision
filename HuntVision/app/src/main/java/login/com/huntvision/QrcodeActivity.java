package login.com.huntvision;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.j256.ormlite.stmt.QueryBuilder;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import login.com.huntvision.models.Cliente;
import login.com.huntvision.models.Item;
import login.com.huntvision.models.ItemLocal;
import login.com.huntvision.models.Local;
import login.com.huntvision.models.Questionario;

@EActivity(R.layout.activity_qrcode)
public class QrcodeActivity extends DefaultActivity {

    private List<Item> lstItem = new ArrayList<>();
    private List<ItemLocal> lstItemLocal = new ArrayList<>();
    private List<Local> lstLocal = new ArrayList<>();
    private List<Cliente> lstCliente = new ArrayList<>();

    private Item item;
    private Local local;
    private Cliente cliente;
    private ItemLocal itemLocal;

    @AfterViews
    public void createQrcode() {



        IntentIntegrator integrator = new IntentIntegrator(this);

        integrator.initiateScan();

    }

    @ViewById(R.id.btnVoltar)
    Button btnVoltar;

    @Click
    public void btnVoltar(View view) {

        this.finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        if (result != null) {

            String contents = result.getContents();

            if (contents != null) {

                QueryBuilder<Item, String> builder = getHelper().getItemRuntimeDAO().queryBuilder();

                try {

                    builder.where().eq("chave", contents.toString());

                    item = getHelper().getItemRuntimeDAO().queryForFirst(builder.prepare());

                    if (item != null) {

                        QueryBuilder<ItemLocal, String> builderItemLocal = getHelper().getItemLocalRuntimeDAO().queryBuilder();

                        builderItemLocal.where().eq("item_id", item.getId().toString());

                        itemLocal = getHelper().getItemLocalRuntimeDAO().queryForFirst(builderItemLocal.prepare());

                        QueryBuilder<Local, String> builderLocal = getHelper().getLocalRuntimeDAO().queryBuilder();

                        builderLocal.where().eq("id", itemLocal.getLocal_id().toString());

                        local = getHelper().getLocalRuntimeDAO().queryForFirst(builderLocal.prepare());

                        QueryBuilder<Cliente, String> builderCliente = getHelper().getClienteRuntimeDAO().queryBuilder();

                        builderCliente.where().eq("id", local.getClienteId().toString());

                        cliente = getHelper().getClienteRuntimeDAO().queryForFirst(builderCliente.prepare());

                        Intent mainIntent = new Intent(this, QuestionarioActivity_.class);

                        mainIntent.putExtra("item", item);

                        mainIntent.putExtra("cliente", cliente);

                        mainIntent.putExtra("local", local);

                        this.startActivity(mainIntent);

                        finish();



                    } else {

                        Toast.makeText(this, "Não existe vistoria para esse QRCode, obrigado.", Toast.LENGTH_SHORT).show();

                        finish();

                    }

                } catch (SQLException e) {

                    Toast.makeText(this, "Ocorreu um erro ao ler código, tente novamente.", Toast.LENGTH_SHORT).show();

                    e.printStackTrace();

                    finish();

                }


            }
        }
    }
}
