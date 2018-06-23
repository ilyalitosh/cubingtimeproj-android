package com.litosh.ilya.cubingtimeproj.authactivity.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.litosh.ilya.cubingtimeproj.R;
import com.litosh.ilya.cubingtimeproj.app.App;
import com.litosh.ilya.cubingtimeproj.authactivity.models.UserSignInModel;
import com.litosh.ilya.cubingtimeproj.authactivity.presenters.SignInButtonPresenter;
import com.litosh.ilya.cubingtimeproj.authactivity.presenters.SignInPresenter;
import com.litosh.ilya.cubingtimeproj.authactivity.views.SignInButtonView;
import com.litosh.ilya.cubingtimeproj.db.models.UserCache;
import com.litosh.ilya.cubingtimeproj.globalmodels.InputFormsChecker;
import com.litosh.ilya.cubingtimeproj.myprofileactivity.ui.MyProfileActivity;

public class AuthActivity extends MvpAppCompatActivity
        implements InputFormsChecker, SignInButtonView {

    @InjectPresenter
    SignInButtonPresenter mSignInButtonPresenter;
    @InjectPresenter
    SignInPresenter mSignInPresenter;
    private static final String TAG = "AuthActivity";
    private AppCompatButton mSignInButton;
    private AppCompatButton mSignUpButton;
    private AppCompatEditText mInputEmail;
    private AppCompatEditText mInputPass;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        mSignInPresenter.trySignIn();

        initComponents();
        initListeners();

    }

    private void initComponents() {
        mInputEmail = findViewById(R.id.activity_auth_input_email);
        mInputPass = findViewById(R.id.activity_auth_input_pass);
        mSignInButton = findViewById(R.id.activity_auth_button_sign_in);
        mSignUpButton = findViewById(R.id.activity_auth_button_sign_up);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle(getString(R.string.activity_auth_progress_dialog_sign_in_title));
    }

    private void initListeners() {
        mSignInButton.setOnClickListener(v -> {
            mSignInButtonPresenter.signIn(getInitializedUserSignInModel());
        });
    }

    private UserSignInModel getInitializedUserSignInModel() {
        UserSignInModel userSignInModel = null;
        if (!isSomeEmpty()) {
            userSignInModel = new UserSignInModel();
            userSignInModel.setEmail(mInputEmail.getText().toString());
            userSignInModel.setPass(mInputPass.getText().toString());
        } else {
            Toast.makeText(this, "Какое-то поле пустое", Toast.LENGTH_SHORT).show();
        }
        return userSignInModel;
    }

    @Override
    public boolean isSomeEmpty() {
        if (TextUtils.isEmpty(mInputEmail.getText())) {
            return true;
        } else if (TextUtils.isEmpty(mInputPass.getText())) {
            return true;
        }
        return false;
    }

    @Override
    public void startProfileActivity() {
        Intent intent = new Intent(this, MyProfileActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void showSignInButtonToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressDialog() {
        mProgressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        mProgressDialog.dismiss();
    }
}
