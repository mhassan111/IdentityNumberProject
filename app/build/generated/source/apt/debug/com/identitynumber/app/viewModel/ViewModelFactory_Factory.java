package com.identitynumber.app.viewModel;

import com.identitynumber.app.repository.Repository;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ViewModelFactory_Factory implements Factory<ViewModelFactory> {
  private final Provider<Repository> repositoryProvider;

  public ViewModelFactory_Factory(Provider<Repository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public ViewModelFactory get() {
    return new ViewModelFactory(repositoryProvider.get());
  }

  public static Factory<ViewModelFactory> create(Provider<Repository> repositoryProvider) {
    return new ViewModelFactory_Factory(repositoryProvider);
  }
}
