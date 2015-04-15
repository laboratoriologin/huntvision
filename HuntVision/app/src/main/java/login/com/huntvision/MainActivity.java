package login.com.huntvision;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;


@EActivity(R.layout.activity_main)
public class MainActivity extends DefaultActivity {


    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Precisamos do seu GPS habilitado para enviar os dados. Deseja habilitar?").setCancelable(false).setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                dialog.cancel();
            }
        });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    @AfterViews
    void afterView() {

        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
            return;
        }

    }

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
