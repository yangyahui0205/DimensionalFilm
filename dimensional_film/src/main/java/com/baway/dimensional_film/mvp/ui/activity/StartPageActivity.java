package com.baway.dimensional_film.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.baway.dimensional_film.R;
import com.baway.dimensional_film.app.utils.HttpUtil;
import com.baway.dimensional_film.di.component.DaggerStartPageComponent;
import com.baway.dimensional_film.mvp.contract.StartPageContract;
import com.baway.dimensional_film.mvp.presenter.StartPagePresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 08/06/2019 16:34
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class StartPageActivity extends BaseActivity<StartPagePresenter> implements StartPageContract.View {

    @BindView(R.id.start_page)
    RelativeLayout startPage;
    private int time = 3;
    private HttpUtil util = HttpUtil.getInstance();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                time--;
                if (time > 0) {
                    handler.sendEmptyMessageDelayed(1, 1000);
                } else {
                    startActivity(new Intent(StartPageActivity.this, GuideActivity.class));
                    finish();
                }
            }
        }
    };

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerStartPageComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {

        return R.layout.activity_start_page; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        Animation anim = AnimationUtils.loadAnimation(StartPageActivity.this, R.anim.my_anim);
        anim.setDuration(3000);
        anim.setFillAfter(true);
        startPage.startAnimation(anim);
        handler.sendEmptyMessageDelayed(1, 0);
       /* startPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartPageActivity.this, GuideActivity.class));
                finish();
            }
        });*/
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
