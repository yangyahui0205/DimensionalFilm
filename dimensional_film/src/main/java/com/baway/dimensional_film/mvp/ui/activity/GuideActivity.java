package com.baway.dimensional_film.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;

import com.baway.dimensional_film.R;
import com.baway.dimensional_film.di.component.DaggerGuideComponent;
import com.baway.dimensional_film.mvp.contract.GuideContract;
import com.baway.dimensional_film.mvp.presenter.GuidePresenter;
import com.baway.dimensional_film.mvp.ui.adapter.MyFragmentPagerAdapter;
import com.baway.dimensional_film.mvp.ui.fragment.guide_fragment.FragmentGuideFirst;
import com.baway.dimensional_film.mvp.ui.fragment.guide_fragment.FragmentGuideFourth;
import com.baway.dimensional_film.mvp.ui.fragment.guide_fragment.FragmentGuideSecond;
import com.baway.dimensional_film.mvp.ui.fragment.guide_fragment.FragmentGuideThird;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 08/06/2019 15:15
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class GuideActivity extends BaseActivity<GuidePresenter> implements GuideContract.View {

    @BindView(R.id.guide_viewPager)
    ViewPager guideViewPager;
    @BindView(R.id.guide_radioGroup)
    RadioGroup guideRadioGroup;
    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerGuideComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_guide; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        FragmentGuideFirst fragmentGuideFirst = new FragmentGuideFirst();
        FragmentGuideSecond fragmentGuideSecond = new FragmentGuideSecond();
        FragmentGuideThird fragmentGuideThird = new FragmentGuideThird();
        FragmentGuideFourth fragmentGuideFourth = new FragmentGuideFourth();
        fragments.add(fragmentGuideFirst);
        fragments.add(fragmentGuideSecond);
        fragments.add(fragmentGuideThird);
        fragments.add(fragmentGuideFourth);
        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        guideViewPager.setAdapter(myFragmentPagerAdapter);
        guideViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                guideRadioGroup.check(guideRadioGroup.getChildAt(i).getId());
               if (i == fragments.size()-1 ) {
                    startActivity(new Intent(GuideActivity.this, LoginActivity.class));
                    finish();
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

}
