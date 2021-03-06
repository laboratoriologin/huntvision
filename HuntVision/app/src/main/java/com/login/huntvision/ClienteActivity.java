package com.login.huntvision;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.stmt.QueryBuilder;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.login.huntvision.models.Cliente;
import com.login.huntvision.models.Item;
import com.login.huntvision.models.ItemLocal;
import com.login.huntvision.models.Local;
import com.login.huntvision.models.Questionario;
import com.login.huntvision.utils.Constantes;
import com.login.huntvision.view.adapters.ClienteAdapter;
import com.login.huntvision.view.adapters.ItemAdapter;
import com.login.huntvision.view.adapters.LocalAdapter;

@EActivity(R.layout.activity_cliente)
public class ClienteActivity extends DefaultActivity {


    private ClienteAdapter clienteAdapter;
    private List<Cliente> lstCliente = new ArrayList<Cliente>();
    private LocalAdapter localAdapter;
    private List<Local> lstLocal = new ArrayList<Local>();


    @ViewById(R.id.spnSecao)
    Spinner spnItem;

    private ItemAdapter itemAdapter;
    private List<Item> lstItem = new ArrayList<Item>();
    private List<ItemLocal> lstItemLocal = new ArrayList<ItemLocal>();
    @ViewById(R.id.spnCliente)
    Spinner comboCliente;

    @ViewById(R.id.spnLocal)
    Spinner comboLocal;

    @ViewById(R.id.spnSecao)
    Spinner comboSecao;

    @ViewById(R.id.lblCliente)
    TextView txtCliente;

    @ViewById(R.id.txtUsuario)
    TextView txtUsuario;

    @ViewById(R.id.lblLocal)
    TextView txtLocal;

    @ViewById(R.id.lblSecao)
    TextView txtSecao;

    @ViewById(R.id.btnVoltar)
    Button btnVoltar;

    @AfterViews
    void loadCliente() {

        setTitle("Cliente");

        txtUsuario.setText(getUsuario().getNome());

        lstCliente = new ArrayList<Cliente>();

        lstCliente = getHelper().getClienteRuntimeDAO().queryForAll();


        clienteAdapter = new ClienteAdapter(this, comboCliente.getId(), lstCliente);


        comboCliente.setAdapter(clienteAdapter);


        comboCliente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                loadLocal(Integer.valueOf(lstCliente.get(position).getId()));
                comboLocal.setVisibility(View.VISIBLE);
                txtLocal.setVisibility(View.VISIBLE);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                txtLocal.setVisibility(View.INVISIBLE);
                comboLocal.setVisibility(View.GONE);
                txtLocal.setVisibility(View.GONE);

            }
        });

        if (lstCliente.size() == 0) {
            txtCliente.setVisibility(View.INVISIBLE);
            comboCliente.setVisibility(View.INVISIBLE);
        }
    }


    @Click
    public void gerarQuestionario(View view) {
        Cliente cliente = (Cliente) comboCliente.getSelectedItem();
        Local local = (Local) comboLocal.getSelectedItem();
        Item item = (Item) comboSecao.getSelectedItem();

        if (cliente == null || local == null || item == null) {
            Toast.makeText(this, "Selecione todos os campos!", Toast.LENGTH_LONG).show();
            return;
        }
        cliente = getHelper().getClienteRuntimeDAO().queryForId(cliente.getId());

        local = getHelper().getLocalRuntimeDAO().queryForId(local.getId());
        item = getHelper().getItemRuntimeDAO().queryForId(item.getId());

        Intent mainIntent = new Intent(this, QuestionarioActivity_.class);

        mainIntent.putExtra("cliente", cliente);
        mainIntent.putExtra("local", local);
        mainIntent.putExtra("item", item);

        this.startActivity(mainIntent);

    }

    protected void loadLocal(Integer idCliente) {

        lstLocal = new ArrayList<Local>();

        try {

            QueryBuilder<Local, String> builder = getHelper().getLocalRuntimeDAO().queryBuilder();

            builder.where().eq("clienteId", idCliente.toString());

            lstLocal = getHelper().getLocalRuntimeDAO().queryForAll();

            lstLocal = getHelper().getLocalRuntimeDAO().query(builder.prepare());

            if (lstLocal.size() == 0) {
                txtLocal.setVisibility(View.INVISIBLE);
                comboLocal.setVisibility(View.INVISIBLE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        localAdapter = new LocalAdapter(this, comboLocal.getId(), lstLocal);

        comboLocal.setAdapter(localAdapter);

        comboLocal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                loadItem(Integer.valueOf(lstLocal.get(position).getId()));
                comboSecao.setVisibility(View.VISIBLE);
                txtSecao.setVisibility(View.VISIBLE);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                comboSecao.setVisibility(View.GONE);
                txtSecao.setVisibility(View.GONE);

            }
        });
        localAdapter.notifyDataSetChanged();


        comboSecao.setVisibility(View.VISIBLE);
        txtSecao.setVisibility(View.VISIBLE);

    }

    protected void loadItem(Integer idLocal) {

        int[] resIds;
        lstItem = new ArrayList<Item>();
        lstItemLocal = new ArrayList<ItemLocal>();

        try {

            QueryBuilder<ItemLocal, String> builder = getHelper().getItemLocalRuntimeDAO().queryBuilder();

            builder.where().eq("local_id", idLocal.toString());

            lstItemLocal = getHelper().getItemLocalRuntimeDAO().query(builder.prepare());

            lstItem = new ArrayList<Item>();

            for (ItemLocal itemLocal : lstItemLocal) {

                lstItem.add(getHelper().getItemRuntimeDAO().queryForId(itemLocal.getItem_id().toString()));

            }

            if (lstItem.size() == 0) {
                txtSecao.setVisibility(View.INVISIBLE);
                comboSecao.setVisibility(View.INVISIBLE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        itemAdapter = new ItemAdapter(this, comboSecao.getId(), lstItem);

        comboSecao.setAdapter(itemAdapter);

        itemAdapter.notifyDataSetChanged();


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
