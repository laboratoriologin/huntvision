package com.login.huntvision;

import android.app.ProgressDialog;
import android.support.v4.app.NavUtils;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.j256.ormlite.stmt.QueryBuilder;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.sql.SQLException;

import com.login.huntvision.models.ServerResponse;
import com.login.huntvision.models.Usuario;
import com.login.huntvision.network.UsuarioRequest;
import com.login.huntvision.network.http.ResponseListener;
import com.login.huntvision.utils.Constantes;
import com.login.huntvision.utils.Utilitarios;

@EActivity(R.layout.activity_esqueci_senha)
public class EsqueciSenhaActivity extends DefaultActivity {

    private Usuario usuarioPesquisado;
    private ProgressDialog progressDialog;


    @ViewById(R.id.edtEmail)
    EditText edtEmail;

    @ViewById(R.id.btnVoltar)
    Button btnVoltar;

    @Click
    public void mudarSenha(View view) {

        if (TextUtils.isEmpty(edtEmail.getText())) {
            Toast.makeText(this, "Preencha o campo!", Toast.LENGTH_SHORT).show();
        } else {

            usuarioPesquisado = new Usuario();
            usuarioPesquisado.setEmail(edtEmail.getText().toString());

            QueryBuilder<Usuario, String> builder = getHelper().getUsuarioRuntimeDAO().queryBuilder();

            try {
                builder.where().eq("email", usuarioPesquisado.getEmail());
                usuarioPesquisado = getHelper().getUsuarioRuntimeDAO().queryForFirst(builder.prepare());
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (Utilitarios.hasConnection(this)) {

                progressDialog = ProgressDialog.show(this, "Aguarde", "Enviando nova senha para o seu e-mail...");

                new UsuarioRequest(getUrlWS(), new ResponseListener() {

                    @Override
                    public void onResult(ServerResponse serverResponse) {
                        progressDialog.dismiss();
                    }

                }).getLembrarSenha(usuarioPesquisado);

            } else {

                Toast.makeText(this, Constantes.MSG_ERRO_NET, Toast.LENGTH_SHORT).show();

            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {

            NavUtils.navigateUpFromSameTask(this);

        }

        return true;

    }

}
