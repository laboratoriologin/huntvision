package login.com.huntvision;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.stmt.QueryBuilder;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.sql.SQLException;

import login.com.huntvision.models.ServerResponse;
import login.com.huntvision.models.Usuario;
import login.com.huntvision.network.UsuarioRequest;
import login.com.huntvision.network.http.ResponseListener;
import login.com.huntvision.utils.Constantes;
import login.com.huntvision.utils.Utilitarios;

@EActivity(R.layout.activity_config)
public class ConfigActivity extends DefaultActivity {

    private Usuario usuarioPesquisado;
    private ProgressDialog progressDialog;


    @ViewById(R.id.edtEmail)
    EditText edtEmail;

    @ViewById(R.id.edtUser)
    EditText edtUser;

    @ViewById(R.id.btnMudarSenha)
    Button btnMudarSenha;



    @ViewById(R.id.btnSair)
    Button btnSair;

    @ViewById(R.id.btnVoltar)
    Button btnVoltar ;

    @AfterViews
    void loadPage()
    {
        edtUser.setText(getUsuario().getNome());
    }

    @Click
    public void btnVoltar(View view) {

        this.finish();
    }

    @Click
    public void btnMudarSenha(View view)
    {

        if (TextUtils.isEmpty(edtEmail.getText())) {
            Toast.makeText(this, "Preencha o campo!", Toast.LENGTH_SHORT).show();
        } else {

            usuarioPesquisado = new Usuario();
            usuarioPesquisado.setEmail(edtEmail.getText().toString());

            QueryBuilder<Usuario,String> builder =  getHelper().getUsuarioRuntimeDAO().queryBuilder();

            try {
                builder.where().eq("email",usuarioPesquisado.getEmail());
                usuarioPesquisado = getHelper().getUsuarioRuntimeDAO().queryForFirst(builder.prepare());
            } catch (SQLException e) {
                e.printStackTrace();
            }

          if (Utilitarios.hasConnection(this)) {
                new UsuarioRequest(new ResponseListener() {


                    @Override
                    public void onResult(ServerResponse serverResponse) {
                        progressDialog.dismiss();
                    }
                }).getLembrarSenha(usuarioPesquisado);


                progressDialog = ProgressDialog.show(this,"Aguarde","Enviando nova senha para o seu e-mail...");

               } else {
                Toast.makeText(this, Constantes.MSG_ERRO_NET, Toast.LENGTH_SHORT).show();
            }

        }
    }

}
