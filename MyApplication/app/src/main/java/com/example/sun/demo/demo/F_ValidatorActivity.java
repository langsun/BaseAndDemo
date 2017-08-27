package com.example.sun.demo.demo;

import android.widget.EditText;

import com.example.sun.demo.R;
import com.example.sun.demo.base.BaseActivity;
import com.example.sun.demo.util.ToastUtils;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Order;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by sun on 17/8/21.
 */

public class F_ValidatorActivity extends BaseActivity implements Validator.ValidationListener {
    @Order(0)
    @NotEmpty(messageResId = R.string.input_phone)
    @Bind(R.id.et_phone)
    EditText mPhone;

    @Order(1)
    @NotEmpty(message = "请输入邮箱")
    @Bind(R.id.et_email)
    EditText mEmail;

    @Order(2)
    @NotEmpty(messageResId = R.string.input_pwd)
    @Password(messageResId = R.string.input_pwd_length)
    @Bind(R.id.et_pwd)
    EditText mPwd;


    @Bind(R.id.et_pwd1)
    EditText mPwd1;
    @Bind(R.id.et_url)
    EditText mUrl;
    private Validator validator;

    @Override
    public void setContent() {
        setContentView(R.layout.f_validator_activity);
    }

    @Override
    public void setModel() {

    }

    @Override
    public void setupView() {
        validator = new Validator(this);
        validator.setValidationListener(this);



    }

    @OnClick(R.id.tv_commit)
    public void commit() {
        validator.validate();
    }

    @Override
    public void onValidationSucceeded() {
        ToastUtils.toast(F_ValidatorActivity.this, "成功");
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        String errorMessage = errors.get(0).getCollatedErrorMessage(F_ValidatorActivity.this);
        ToastUtils.toast(F_ValidatorActivity.this, errorMessage);
    }
}
