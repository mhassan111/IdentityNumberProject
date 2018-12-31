package com.identitynumber.app.di;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import okhttp3.OkHttpClient;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class UtilsModule_GetRequestHeaderFactory implements Factory<OkHttpClient> {
  private final UtilsModule module;

  public UtilsModule_GetRequestHeaderFactory(UtilsModule module) {
    this.module = module;
  }

  @Override
  public OkHttpClient get() {
    return Preconditions.checkNotNull(
        module.getRequestHeader(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<OkHttpClient> create(UtilsModule module) {
    return new UtilsModule_GetRequestHeaderFactory(module);
  }

  public static OkHttpClient proxyGetRequestHeader(UtilsModule instance) {
    return instance.getRequestHeader();
  }
}
