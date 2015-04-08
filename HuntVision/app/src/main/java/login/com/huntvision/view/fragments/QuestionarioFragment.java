package login.com.huntvision.view.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import login.com.huntvision.QuestionarioActivity;
import login.com.huntvision.R;
import login.com.huntvision.models.Questionario;
import login.com.huntvision.models.Resposta;

/**
 * Created by login on 20/03/15.
 */
public class QuestionarioFragment extends Fragment {

    public static final String ARG_OBJECT = "object";
    private View view;


    @Override
    public void onResume() {

        super.onResume();

        final Questionario questionario = (Questionario) getArguments().getSerializable(ARG_OBJECT);

        Bitmap bitmap = ((QuestionarioActivity) getActivity()).getImage(questionario);

        ImageView image = (ImageView) getView().findViewById(R.id.imageView);

        TextView textView = (TextView) getView().findViewById(R.id.lbImagemCapturada);

        image.setVisibility(View.GONE);

        textView.setVisibility(View.GONE);

        if(bitmap!=null) {

            image.setVisibility(View.VISIBLE);

            textView.setVisibility(View.VISIBLE);

            image.setImageBitmap(bitmap);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_questionario, container, false);

        final Questionario questionario = (Questionario) getArguments().getSerializable(ARG_OBJECT);

        final RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.rgpResposta);

        RadioButton button = null;

        View.OnClickListener onclickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int position = radioGroup.indexOfChild(view);

                Questionario questao = ((QuestionarioActivity) getActivity()).getQuestionario(questionario);

                for (int i = 0; i < radioGroup.getChildCount(); i++) {

                    ((RadioButton) radioGroup.getChildAt(i)).setChecked(position == i);

                    questao.getRespostas().get(i).setFlagRespostaCerta(position == i);

                }

            }

        };

        ((TextView) rootView.findViewById(R.id.lblTituloPergunta)).setText(questionario.getPergunta());

        for (Resposta resposta : questionario.getRespostas()) {

            button = new RadioButton(rootView.getContext());

            button.setChecked(resposta.getFlagRespostaCerta() != null && resposta.getFlagRespostaCerta());

            button.setText(resposta.getDescricao());

            button.setTextSize(16f);

            LinearLayout.LayoutParams layoutParams = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);

            button.setOnClickListener(onclickListener);

            radioGroup.addView(button, layoutParams);

        }

        EditText editText = (EditText) rootView.findViewById(R.id.edtObservacoes);

        editText.setText(questionario.getObservacao());

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Questionario questao = ((QuestionarioActivity) getActivity()).getQuestionario(questionario);
                questao.setObservacao(s.toString());
            }
        });

        rootView.findViewById(R.id.btnCapturarImagem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((QuestionarioActivity) getActivity()).openCamera(questionario);

            }
        });

        rootView.findViewById(R.id.btnCapturarImagemAcervo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((QuestionarioActivity) getActivity()).openAcervo(questionario);

            }
        });


        return rootView;

    }

}