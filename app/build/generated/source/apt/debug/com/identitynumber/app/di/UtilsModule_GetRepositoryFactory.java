package com.identitynumber.app.di;

import com.identitynumber.app.interfaces.ApiCallInterface;
import com.identitynumber.app.repository.Repository;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class UtilsModule_GetRepositoryFactory implements Factory<Repository> {
  private final UtilsModule module;

  private final Provider<ApiCallInterface> apiCallInterfaceProvider;

  public UtilsModule_GetRepositoryFactory(
      UtilsModule module, Provider<ApiCallInterface> apiCallInterfaceProvider) {
    this.module = module;
    this.apiCallInterfaceProvider = apiCallInterfaceProvider;
  }

  @Override
  public Repository get() {
    return Preconditions.checkNotNull(
        module.getRepository(apiCallInterfaceProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<Repository> create(
      UtilsModule module, Provider<ApiCallInterface> apiCallInterfaceProvider) {
    return new UtilsModule_GetRepositoryFactory(module, apiCallInterfaceProvider);
  }

  public static Repository proxyGetRepository(
      UtilsModule instance, ApiCallInterface apiCallInterface) {
    return instance.getRepository(apiCallInterface);
  }
}
