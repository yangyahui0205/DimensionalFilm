package com.baway.dimensional_film.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.baway.dimensional_film.R;
import com.baway.dimensional_film.app.utils.EncryptUtil;
import com.baway.dimensional_film.di.component.DaggerRegisterComponent;
import com.baway.dimensional_film.mvp.contract.RegisterContract;
import com.baway.dimensional_film.mvp.presenter.RegisterPresenter;
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
 * Created by MVPArmsTemplate on 08/06/2019 20:36
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterContract.View {

    @BindView(R.id.et_nickname_register)
    EditText etNicknameRegister;
    @BindView(R.id.et_sex_register)
    EditText etSexRegister;
    @BindView(R.id.et_data_register)
    EditText etDataRegister;
    @BindView(R.id.et_userPhone_register)
    EditText etUserPhoneRegister;
    @BindView(R.id.et_email_register)
    EditText etEmailRegister;
    @BindView(R.id.et_userPwd_register)
    EditText etUserPwdRegister;
    @BindView(R.id.btn_register)
    Button btnRegister;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerRegisterComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_register; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
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


    @OnClick(R.id.btn_register)
    public void onViewClicked() {
        String birthday = etDataRegister.getText().toString();
        String phone = etUserPhoneRegister.getText().toString();
        String pwd = etUserPwdRegister.getText().toString();
        String nickName = etNicknameRegister.getText().toString();
        String email = etEmailRegister.getText().toString();
        String sex = etSexRegister.getText().toString();
        int gender = 1;
        if (sex.equals("男")) {
            gender = 1;
        } else if (sex.equals("女")) {
            gender = 2;
        } else {
            Toast.makeText(this, "输入性别错误", Toast.LENGTH_SHORT).show();
        }
        mPresenter.requestRegisterData(nickName, phone, EncryptUtil.encrypt(pwd), EncryptUtil.encrypt(pwd), gender, birthday, "123456", "华为", "5.0", "android", email);
    }

    @Override
    public void showRegisterData(String message) {
        Gson gson = new Gson();
        RegisterBean registerBean = gson.fromJson(message, RegisterBean.class);
        Toast.makeText(this, registerBean.getMessage(), Toast.LENGTH_SHORT).show();
        if ("0000".equals(registerBean.getStatus())) {
            //跳转登录页面
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        }
    }

    class RegisterBean {


        /**
         * message : 注册成功
         * status : 0000
         */

        private String message;
        private String status;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
