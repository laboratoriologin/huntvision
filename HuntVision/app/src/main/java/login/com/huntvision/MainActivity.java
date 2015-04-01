package login.com.huntvision;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.androidannotations.annotations.EActivity;


@EActivity(R.layout.activity_main)
public class MainActivity extends DefaultActivity {


    public void goPendentes(View view) {

        startActivity(new Intent(this, VistoriaPendenteActivity_.class));

    }

    public void loadCliente(View view) {
        Intent mainIntent = new Intent(MainActivity.this, ClienteActivity_.class);
        this.startActivity(mainIntent);
    }

    public void goSincronizacao(View view) {
        Intent mainIntent = new Intent(MainActivity.this, SincronizacaoActivity_.class);
        this.startActivity(mainIntent);
    }


    public void goSobre(View view) {

        Intent mainIntent = new Intent(MainActivity.this, SobreActivity_.class);
        this.startActivity(mainIntent);

    }

    public void goQRcode(View view) {

        Intent mainIntent = new Intent(MainActivity.this, QrcodeActivity_.class);
        this.startActivity(mainIntent);

    }



}
