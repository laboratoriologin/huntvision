package login.com.huntvision.view.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import login.com.huntvision.R;
import login.com.huntvision.VistoriaPendenteDetalheActivity;
import login.com.huntvision.models.Imagem;
import login.com.huntvision.models.Questionario;
import login.com.huntvision.models.Vistoria;
import login.com.huntvision.models.VistoriaResposta;

/**
 * Created by Ricardo on 19/01/2015.
 */

public class VistoriaDetalheAdapter extends BaseAdapter {

    private List<VistoriaResposta> respostas;
    private LayoutInflater mInflater;
    private String imgPath;
    private VistoriaPendenteDetalheActivity activity;

    public VistoriaDetalheAdapter(List<VistoriaResposta> respostas, VistoriaPendenteDetalheActivity activity,String imgPath) {

        this.respostas = respostas;

        mInflater = LayoutInflater.from(activity);

        this.activity = activity;

        this.imgPath = imgPath;

    }

    @Override
    public int getCount() {
        return respostas.size();
    }

    @Override
    public Object getItem(int position) {
        return respostas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = mInflater.inflate(R.layout.adapter_vistoria_detalhe,null);

        TextView pergunta = (TextView) convertView.findViewById(R.id.adapter_vistoria_detalhe_pergunta);

        TextView resposta  = (TextView) convertView.findViewById(R.id.adapter_vistoria_detalhe_resposta);

        TextView qtdFoto  = (TextView) convertView.findViewById(R.id.lbImagens);

        final VistoriaResposta vistoriaResposta = (VistoriaResposta) getItem(position);

        pergunta.setText(vistoriaResposta.getResposta().getQuestionario().getPergunta());

        resposta.setText(vistoriaResposta.getResposta().getDescricao());

        qtdFoto.setText(vistoriaResposta.getImagens().size() + " foto(s)");

        qtdFoto.setClickable(vistoriaResposta.getImagens().size() > 0);

        qtdFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Questionario questionario = new Questionario();

                questionario.setCaminhosImagens(new ArrayList<String>());

                questionario.setPergunta(vistoriaResposta.getResposta().getQuestionario().getPergunta());

                for (Imagem imagem : vistoriaResposta.getImagens()) {

                    questionario.getCaminhosImagens().add(imagem.getCaminho());

                }

                activity.showImages(questionario);

            }

        });

        return convertView;

    }
}