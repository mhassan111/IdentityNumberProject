package com.identitynumber.app.presenter;

import com.identitynumber.app.model.User;

public class LoginPresenter implements ILoginPresenter {

    ILoginView loginView;

    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void onLogin(String email, String password) {
        User user = new User(email, password);
        boolean isValidData = user.isValidData();
        if (isValidData)
            loginView.onLoginResult("login success");
        else
            loginView.onLoginResult("login failed");
    }
}
