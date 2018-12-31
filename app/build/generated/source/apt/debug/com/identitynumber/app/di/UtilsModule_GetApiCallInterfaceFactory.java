package com.identitynumber.app.di;

import com.identitynumber.app.interfaces.ApiCallInterface;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class UtilsModule_GetApiCallInterfaceFactory implements Factory<ApiCallInterface> {
  private final UtilsModule module;

  private final Provider<Retrofit> retrofitProvider;

  public UtilsModule_GetApiCallInterfaceFactory(
      UtilsModule module, Provider<Retrofit> retrofitProvider) {
    this.module = module;
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public ApiCallInterface get() {
    return Preconditions.checkNotNull(
        module.getApiCallInterface(retrofitProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<ApiCallInterface> create(
      UtilsModule module, Provider<Retrofit> retrofitProvider) {
    return new UtilsModule_GetApiCallInterfaceFactory(module, retrofitProvider);
  }

  public static ApiCallInterface proxyGetApiCallInterface(UtilsModule instance, Retrofit retrofit) {
    return instance.getApiCallInterface(retrofit);
  }
}
