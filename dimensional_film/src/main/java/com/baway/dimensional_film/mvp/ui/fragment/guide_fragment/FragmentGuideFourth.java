package com.baway.dimensional_film.mvp.ui.fragment.guide_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.baway.dimensional_film.R;
import com.baway.dimensional_film.mvp.ui.activity.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/*
 *@Auther:Helen
 *@Date: 19.8.6
 *@Time: 17:05:08
 *@Description:
 * */public class FragmentGuideFourth extends Fragment {
    @BindView(R.id.guide_fourth)
    LinearLayout guideFourth;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_guide_fourth_item, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.guide_fourth)
    public void onViewClicked() {
      //  startActivity(new Intent(getContext(), LoginActivity.class));
    }
}
