package com.login.huntvision;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.location.LocationManager;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.activity_main)
public class MainActivity extends DefaultActivity {

    @ViewById(R.id.txtLerCodigo)
    TextView txtLerCodigo;


    @ViewById(R.id.txtCliente)
    TextView txtCliente;


    @ViewById(R.id.txtVisita)
    TextView txtVisita;

    @ViewById(R.id.txtSobre)
    TextView txtSobre;


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

      /*  final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER) && !manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            buildAlertMessageNoGps();
            return;
        }*/

        Typeface helveticaBold;
        Typeface helveticaRegular;


        helveticaBold = Typeface.createFromAsset(getAssets(), "Agencyb.ttf");
        helveticaRegular = Typeface.createFromAsset(getAssets(), "Agencyr.ttf");

        txtCliente.setTypeface(helveticaRegular);
        txtVisita.setTypeface(helveticaRegular);
        txtLerCodigo.setTypeface(helveticaRegular);
        txtSobre.setTypeface(helveticaRegular);


    }

    public void goPendentes(View view) {

        startActivity(new Intent(this, VistoriaPendenteActivity_.class));

    }

    public void loadCliente(View view) {
        Intent mainIntent = new Intent(MainActivity.this, ClienteActivity_.class);
        this.startActivity(mainIntent);
    }

    public void goSincronizacao() {
        Intent mainIntent = new Intent(MainActivity.this, SincronizacaoActivity_.class);
        this.startActivity(mainIntent);
    }


    public void goSobre(View view) {

        Intent mainIntent = new Intent(MainActivity.this, SobreActivity_.class);
        this.startActivity(mainIntent);

    }

    public void goAgenda(View view) {

        Intent mainIntent = new Intent(MainActivity.this, AgendaActivity_.class);
        this.startActivity(mainIntent);

    }

    public void goQRcode(View view) {

        startActivity(new Intent(this, QrcodeActivity.class));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.settings) {

            goSincronizacao();

        }


        return true;

    }


}
