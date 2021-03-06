package com.login.huntvision.view.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import com.login.huntvision.models.Questionario;
import com.login.huntvision.view.fragments.QuestionarioFragment;

/**
 * Created by login on 20/03/15.
 */
public class QuestionarioFragmentPageAdapter extends FragmentPagerAdapter {

    private List<Questionario> lstQuestionario;

    public QuestionarioFragmentPageAdapter(FragmentManager fm,List<Questionario> questionarios) {

        super(fm);
        this.lstQuestionario = questionarios;

    }

    @Override
    public Fragment getItem(int i) {

        Fragment fragment = new QuestionarioFragment();

        Bundle args = new Bundle();

        args.putSerializable(QuestionarioFragment.ARG_OBJECT, lstQuestionario.get(i));

        fragment.setArguments(args);

        return fragment;

    }

    @Override
    public int getCount() {
        return lstQuestionario.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Pergunta " + (position + 1);
    }


}