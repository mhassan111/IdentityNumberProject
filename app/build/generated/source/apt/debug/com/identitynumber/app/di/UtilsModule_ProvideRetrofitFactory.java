package com.identitynumber.app.di;

import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class UtilsModule_ProvideRetrofitFactory implements Factory<Retrofit> {
  private final UtilsModule module;

  private final Provider<Gson> gsonProvider;

  private final Provider<OkHttpClient> okHttpClientProvider;

  public UtilsModule_ProvideRetrofitFactory(
      UtilsModule module,
      Provider<Gson> gsonProvider,
      Provider<OkHttpClient> okHttpClientProvider) {
    this.module = module;
    this.gsonProvider = gsonProvider;
    this.okHttpClientProvider = okHttpClientProvider;
  }

  @Override
  public Retrofit get() {
    return Preconditions.checkNotNull(
        module.provideRetrofit(gsonProvider.get(), okHttpClientProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<Retrofit> create(
      UtilsModule module,
      Provider<Gson> gsonProvider,
      Provider<OkHttpClient> okHttpClientProvider) {
    return new UtilsModule_ProvideRetrofitFactory(module, gsonProvider, okHttpClientProvider);
  }

  public static Retrofit proxyProvideRetrofit(
      UtilsModule instance, Gson gson, OkHttpClient okHttpClient) {
    return instance.provideRetrofit(gson, okHttpClient);
  }
}
