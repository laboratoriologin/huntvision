package login.com.huntvision.view.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import login.com.huntvision.R;
import login.com.huntvision.models.Agenda;
import login.com.huntvision.models.Agenda;

/**
 * Created by Ricardo on 19/01/2015.
 */

public class AgendaAdapter extends BaseAdapter {

    private List<Agenda> agendas;
    private List<Agenda> agendasFiltered;
    private LayoutInflater mInflater;
    private AgendaFilter mFilter = new AgendaFilter();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public AgendaAdapter(List<Agenda> agendas, Context context) {
        this.agendas = agendas;
        this.agendasFiltered = agendas;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return agendasFiltered.size();
    }

    @Override
    public Object getItem(int position) {
        return agendasFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = mInflater.inflate(R.layout.adapter_agenda, null);

        TextView titulo = (TextView) convertView.findViewById(R.id.adapter_agenda_descricao);

        TextView data = (TextView) convertView.findViewById(R.id.adapter_agenda_data);

        TextView status = (TextView) convertView.findViewById(R.id.status_textview);

        Agenda agenda = (Agenda) getItem(position);

        titulo.setText(agenda.getCliente().getNome() + "\n" + agenda.getCliente().getEndereco());

        data.setText("Agendado para " + dateFormat.format(agenda.getDataHora()));


        return convertView;
    }

    private class AgendaFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final List<Agenda> list = agendas;

            if (TextUtils.isEmpty(filterString)) {
                results.values = list;
                results.count = list.size();
                return results;
            }

            int count = list.size();

            final ArrayList<Agenda> nlist = new ArrayList<Agenda>(count);

            Agenda filterableAgenda;

            for (int i = 0; i < count; i++) {
                filterableAgenda = list.get(i);
                if (filterableAgenda.getCliente().getNome().toLowerCase().contains(filterString)) {
                    nlist.add(filterableAgenda);
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            agendasFiltered = (ArrayList<Agenda>) results.values;
            notifyDataSetChanged();
        }

    }

    public List<Agenda> getAgendasFiltered() {
        return agendasFiltered;
    }

    public Filter getFilter() {
        return mFilter;
    }
}