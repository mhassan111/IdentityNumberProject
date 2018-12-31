package com.identitynumber.app.di;

import com.identitynumber.app.activities.SignInActivity;

import javax.inject.Singleton;

import dagger.Component;


@Component(modules = {AppModule.class, UtilsModule.class})
@Singleton
public interface AppComponent {

    void doInjection(SignInActivity signInActivity);
}
