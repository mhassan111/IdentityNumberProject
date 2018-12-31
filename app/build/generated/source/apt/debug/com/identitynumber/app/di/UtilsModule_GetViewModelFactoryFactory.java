package com.identitynumber.app.di;

import android.arch.lifecycle.ViewModelProvider;
import com.identitynumber.app.repository.Repository;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class UtilsModule_GetViewModelFactoryFactory
    implements Factory<ViewModelProvider.Factory> {
  private final UtilsModule module;

  private final Provider<Repository> myRepositoryProvider;

  public UtilsModule_GetViewModelFactoryFactory(
      UtilsModule module, Provider<Repository> myRepositoryProvider) {
    this.module = module;
    this.myRepositoryProvider = myRepositoryProvider;
  }

  @Override
  public ViewModelProvider.Factory get() {
    return Preconditions.checkNotNull(
        module.getViewModelFactory(myRepositoryProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<ViewModelProvider.Factory> create(
      UtilsModule module, Provider<Repository> myRepositoryProvider) {
    return new UtilsModule_GetViewModelFactoryFactory(module, myRepositoryProvider);
  }

  public static ViewModelProvider.Factory proxyGetViewModelFactory(
      UtilsModule instance, Repository myRepository) {
    return instance.getViewModelFactory(myRepository);
  }
}
