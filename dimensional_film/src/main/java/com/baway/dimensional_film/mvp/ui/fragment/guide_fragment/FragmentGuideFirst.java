package com.baway.dimensional_film.mvp.ui.fragment.guide_fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baway.dimensional_film.R;

/*
 *@Auther:Helen
 *@Date: 19.8.6
 *@Time: 17:05:08
 *@Description:
 * */public class FragmentGuideFirst extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_guide_first_item, container, false);
    }
}
