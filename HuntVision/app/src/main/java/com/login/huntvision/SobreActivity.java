package com.login.huntvision;

import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import com.login.huntvision.R;

@EActivity(R.layout.activity_sobre)
public class SobreActivity extends DefaultActivity {

    ;

    @ViewById(R.id.txtConteudoSobre)
    TextView txtConteudoSobre;

    @ViewById(R.id.btnVoltar)
    Button btnVoltar;


    @AfterViews
    void loadContent() {
        txtConteudoSobre.setText(loadConteudo());
    }


    private String loadConteudo() {
        StringBuilder strConteudo = new StringBuilder();

        strConteudo.append("Aplicação para vistorias de condomínios ou qualquer ambiente que necessita uma rotina de auditorias automatizado e dinâmico.");

        return strConteudo.toString();
    }


}
