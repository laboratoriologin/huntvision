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
import java.util.List;

import login.com.huntvision.R;
import login.com.huntvision.models.Vistoria;
import login.com.huntvision.models.VistoriaResposta;

/**
 * Created by Ricardo on 19/01/2015.
 */

public class VistoriaDetalheAdapter extends BaseAdapter {

    private List<VistoriaResposta> respostas;
    private LayoutInflater mInflater;
    private String imgPath;

    public VistoriaDetalheAdapter(List<VistoriaResposta> respostas, Context context,String imgPath) {

        this.respostas = respostas;

        mInflater = LayoutInflater.from(context);

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

        ImageView imagem = (ImageView) convertView.findViewById(R.id.adapter_vistoria_detalhe_imagem);

        VistoriaResposta vistoriaResposta = (VistoriaResposta) getItem(position);

        pergunta.setText(vistoriaResposta.getResposta().getQuestionario().getPergunta());

        resposta.setText(vistoriaResposta.getResposta().getDescricao());

        if(!TextUtils.isEmpty(vistoriaResposta.getImagem())) {

            imagem.setVisibility(View.VISIBLE);

            File imgFile = new  File(imgPath + "/" + vistoriaResposta.getImagem());

            if(imgFile.exists()){

                BitmapFactory.Options options =  new BitmapFactory.Options();
                options.inJustDecodeBounds = false;
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                options.inDither = true;
                options.inSampleSize = 4;
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath(),options);

                imagem.setImageBitmap(myBitmap);

            }

        }

        return convertView;

    }
}