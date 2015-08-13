package com.login.huntvision.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import com.login.huntvision.AgendaDetalheActivity;
import com.login.huntvision.R;
import com.login.huntvision.models.Agenda;

/**
 * Created by Ricardo on 19/01/2015.
 */

public class AgendaDetalheAdapter extends BaseAdapter {

    private List<Agenda> agendas;
    private LayoutInflater mInflater;
    private String imgPath;
    private AgendaDetalheActivity activity;

    public AgendaDetalheAdapter(List<Agenda> agendas, AgendaDetalheActivity activity, String imgPath) {

        this.agendas = agendas;

        mInflater = LayoutInflater.from(activity);

        this.activity = activity;

        this.imgPath = imgPath;

    }

    @Override
    public int getCount() {
        return agendas.size();
    }

    @Override
    public Object getItem(int position) {
        return agendas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = mInflater.inflate(R.layout.activity_agenda_detalhe,null);

        TextView data = (TextView) convertView.findViewById(R.id.activity_agenda_detalhe_data);

        TextView local  = (TextView) convertView.findViewById(R.id.activity_agenda_detalhe_local);


        final Agenda agenda = (Agenda) getItem(position);

        data.setText("Agendado para " + agenda.getDataHora());
        local.setText(agenda.getCliente().getNome() + "\n" + agenda.getCliente().getEndereco());



        return convertView;

    }
}