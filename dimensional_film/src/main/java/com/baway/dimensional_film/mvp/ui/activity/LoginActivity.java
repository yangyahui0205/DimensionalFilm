package com.baway.dimensional_film.mvp.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baway.dimensional_film.R;
import com.baway.dimensional_film.app.beans.LoginBean;
import com.baway.dimensional_film.app.utils.EncryptUtil;
import com.baway.dimensional_film.di.component.DaggerLoginComponent;
import com.baway.dimensional_film.mvp.contract.LoginContract;
import com.baway.dimensional_film.mvp.presenter.LoginPresenter;
import com.google.gson.Gson;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 08/06/2019 17:21
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.et_userPhone_login)
    EditText etUserPhoneLogin;
    @BindView(R.id.et_userPwd_login)
    EditText etUserPwdLogin;
    @BindView(R.id.iv_login_eye)
    ImageView ivLoginEye;
    @BindView(R.id.tv_quickRegister_login)
    TextView tvQuickRegisterLogin;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.iv_weixin)
    ImageView ivWeixin;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLoginComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_login; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

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


    @OnClick({R.id.iv_login_eye, R.id.tv_quickRegister_login, R.id.btn_login, R.id.iv_weixin})
    public void onViewClicked(View view) {
        String phone = etUserPhoneLogin.getText().toString();
        String pwd = etUserPwdLogin.getText().toString();
        switch (view.getId()) {
            case R.id.iv_login_eye:
                break;
            case R.id.tv_quickRegister_login:
                //跳转注册页面
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.btn_login:
                //登录
                try {
                    mPresenter.requestLoginData(phone, EncryptUtil.encrypt(pwd));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("LoginActivity", EncryptUtil.encrypt(pwd));
                break;
            case R.id.iv_weixin:
                break;
        }
    }

    @Override
    public void showLoginData(String message) {
        Gson gson = new Gson();
        LoginBean loginBean = gson.fromJson(message, LoginBean.class);
        Toast.makeText(this, loginBean.getMessage(), Toast.LENGTH_SHORT).show();

        SharedPreferences user = getSharedPreferences("user", MODE_PRIVATE);
        SharedPreferences.Editor edit = user.edit();

        //判断是否登录成功
        if ("0000".equals(loginBean.getStatus())) {
            int userId = loginBean.getResult().getUserId();
            String sessionId = loginBean.getResult().getSessionId();
            //跳转展示页面
            edit.putInt("userId", userId);
            edit.putString("sessionId", sessionId);
            edit.commit();
            startActivity(new Intent(LoginActivity.this, FilmHomeActivity.class));
            finish();
        }
    }
}
