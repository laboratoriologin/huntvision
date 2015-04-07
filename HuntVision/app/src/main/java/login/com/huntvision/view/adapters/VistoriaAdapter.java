package login.com.huntvision.view.adapters;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import login.com.huntvision.R;
import login.com.huntvision.models.Cliente;
import login.com.huntvision.models.Vistoria;

/**
 * Created by Ricardo on 19/01/2015.
 */

public class VistoriaAdapter extends BaseAdapter {

    private List<Vistoria> vistorias;
    private List<Vistoria> vistoriasFiltered;
    private LayoutInflater mInflater;
    private VistoriaFilter mFilter = new VistoriaFilter();

    public VistoriaAdapter(List<Vistoria> vistorias, Context context) {
        this.vistorias = vistorias;
        this.vistoriasFiltered = vistorias;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return vistoriasFiltered.size();
    }

    @Override
    public Object getItem(int position) {
        return vistoriasFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = mInflater.inflate(R.layout.adapter_vistoria,null);

        TextView titulo = (TextView) convertView.findViewById(R.id.adapter_vistoria_resumo);

        TextView data  = (TextView) convertView.findViewById(R.id.adapter_vistoria_data);

        TextView status = (TextView) convertView.findViewById(R.id.status_textview);

        Vistoria vistoria = (Vistoria) getItem(position);

        titulo.setText(vistoria.getCliente().getNome() + "\n" + vistoria.getCliente().getEndereco());

        data.setText("Feita em " + vistoria.getData());

        if (vistoria.getFlagSincronizado() == 1) {

            if (vistoria.getPendenteImagem() == 0) {

                status.setText("Status: Enviado");

            } else {

                status.setText("Status: Imagens pendentes de envio");

            }

        } else {

            status.setText("Status: Pendente de envio");

        }

        return convertView;
    }

    private class VistoriaFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final List<Vistoria> list = vistorias;

            if(TextUtils.isEmpty(filterString)) {
                results.values = list;
                results.count = list.size();
                return results;
            }

            int count = list.size();

            final ArrayList<Vistoria> nlist = new ArrayList<Vistoria>(count);

            Vistoria filterableVistoria;

            for (int i = 0; i < count; i++) {
                filterableVistoria = list.get(i);
                if (filterableVistoria.getCliente().getNome().toLowerCase().contains(filterString)) {
                    nlist.add(filterableVistoria);
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            vistoriasFiltered = (ArrayList<Vistoria>) results.values;
            notifyDataSetChanged();
        }

    }

    public List<Vistoria> getVistoriasFiltered() {
        return vistoriasFiltered;
    }

    public Filter getFilter() {
        return mFilter;
    }
}