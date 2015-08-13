package com.login.huntvision;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.j256.ormlite.stmt.QueryBuilder;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.sql.SQLException;

import com.login.huntvision.models.Usuario;
import com.login.huntvision.utils.Utilitarios;


@EActivity(R.layout.activity_login)
public class LoginActivity extends DefaultActivity {

    @ViewById(R.id.edtUser)
    EditText dialogLogin;

    @ViewById(R.id.edtSenha)
    EditText dialogSenha;

    @ViewById(R.id.btnEsqueciSenha)
    Button btnEsqueciSenha;

    private Usuario usuarioPesquisado;

    @ViewById(R.id.btnVoltar)
    Button btnVoltar ;

    @Click
    public void btnVoltar(View view) {

        this.finish();
    }

    @Click
    public void autenticar(View view) {

        if (TextUtils.isEmpty( dialogLogin.getText()) || TextUtils.isEmpty(dialogSenha.getText())) {

            Toast.makeText(LoginActivity.this, "Preencha os dois campos!", Toast.LENGTH_SHORT).show();

        } else {

            usuarioPesquisado = new Usuario();

            usuarioPesquisado.setLogin(dialogLogin.getText().toString());

            usuarioPesquisado.setSenha(dialogSenha.getText().toString());

            usuarioPesquisado.setSenha(Utilitarios.gerarHash(usuarioPesquisado.getSenha(),"MD5"));

            QueryBuilder<Usuario,String> builder =  getHelper().getUsuarioRuntimeDAO().queryBuilder();

            try {

                builder.where().eq("login",usuarioPesquisado.getLogin()).and().eq("senha",usuarioPesquisado.getSenha());

                usuarioPesquisado = getHelper().getUsuarioRuntimeDAO().queryForFirst(builder.prepare());

            } catch (SQLException e) {

                e.printStackTrace();

            }

            if(usuarioPesquisado !=null) {

                setUsuario(usuarioPesquisado);

                startActivity(new Intent(this,MainActivity_.class));

                finish();

            } else {

                Toast.makeText(this,"Usuário inválido!",Toast.LENGTH_SHORT).show();

            }

        }

    }

    @Click
    public void btnEsqueciSenha(View view)
    {

         Intent mainIntent = new Intent(LoginActivity.this, EsqueciSenhaActivity_.class);
        this.startActivity(mainIntent);
    }

}
