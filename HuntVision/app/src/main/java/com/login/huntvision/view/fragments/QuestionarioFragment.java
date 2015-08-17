package com.login.huntvision.view.fragments;

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

import com.login.huntvision.QuestionarioActivity;
import com.login.huntvision.R;
import com.login.huntvision.models.Questionario;
import com.login.huntvision.models.Resposta;
import com.login.huntvision.models.Protocolo;

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

        TextView qtdImagens = (TextView) getView().findViewById(R.id.lbImagens);

        qtdImagens.setText(questionario.getCaminhosImagens().size() + " foto(s)");

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_questionario, container, false);

        final Questionario questionario = (Questionario) getArguments().getSerializable(ARG_OBJECT);

        final RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.rgpResposta);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (questionario.getRespostas().get(radioGroup.getCheckedRadioButtonId()).getFlagNaoConformidade()) {

                    rootView.findViewById(R.id.btnNaoConformidade).setVisibility(View.VISIBLE);

                } else {

                    rootView.findViewById(R.id.btnNaoConformidade).setVisibility(View.INVISIBLE);

                }

            }
        });

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

        Resposta resposta = null;

        for (int i = 0; i < questionario.getRespostas().size(); i++) {

            button = new RadioButton(rootView.getContext());

            resposta = questionario.getRespostas().get(i);

            button.setId(i);

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

        rootView.findViewById(R.id.btnNaoConformidade).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((QuestionarioActivity) getActivity()).linkNaoConformidade(questionario);

            }
        });

        rootView.findViewById(R.id.btnCapturarImagemAcervo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((QuestionarioActivity) getActivity()).openAcervo(questionario);

            }
        });

        rootView.findViewById(R.id.lbImagens).setClickable(true);

        rootView.findViewById(R.id.lbImagens).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ((QuestionarioActivity) getActivity()).showImages(questionario);

            }

        });


        return rootView;

    }

}