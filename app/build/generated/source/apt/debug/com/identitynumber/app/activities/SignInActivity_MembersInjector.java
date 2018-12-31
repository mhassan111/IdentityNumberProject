package com.identitynumber.app.activities;

import com.identitynumber.app.viewModel.ViewModelFactory;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class SignInActivity_MembersInjector implements MembersInjector<SignInActivity> {
  private final Provider<ViewModelFactory> viewModelFactoryProvider;

  public SignInActivity_MembersInjector(Provider<ViewModelFactory> viewModelFactoryProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
  }

  public static MembersInjector<SignInActivity> create(
      Provider<ViewModelFactory> viewModelFactoryProvider) {
    return new SignInActivity_MembersInjector(viewModelFactoryProvider);
  }

  @Override
  public void injectMembers(SignInActivity instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
  }

  public static void injectViewModelFactory(
      SignInActivity instance, ViewModelFactory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }
}
