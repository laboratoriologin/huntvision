package com.login.huntvision;

import android.os.Bundle;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.File;

import com.login.huntvision.models.Questionario;
import com.login.huntvision.utils.Constantes;

@EActivity(R.layout.activity_galeria)
public class GaleriaActivity extends DefaultActivity {

    private Questionario questionario;
    private String dataFolder;

    @ViewById(R.id.slider)
    SliderLayout mDemoSlider;


    @AfterViews
    void init() {

        questionario = (Questionario) getIntent().getSerializableExtra("questionario");

        dataFolder = getIntent().getStringExtra("path");

        int i = 1;

        for (String name : questionario.getCaminhosImagens()) {

            TextSliderView textSliderView = new TextSliderView(this);

            textSliderView.description(questionario.getPergunta() + " | Foto " + i)
                    .image(new File(dataFolder + "/" + name))
                    .setScaleType(BaseSliderView.ScaleType.FitCenterCrop);
            mDemoSlider.addSlider(textSliderView);

            i++;

        }

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.stopAutoCycle();

    }

}
