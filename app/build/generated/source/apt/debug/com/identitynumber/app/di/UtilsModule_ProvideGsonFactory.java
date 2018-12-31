package com.identitynumber.app.di;

import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class UtilsModule_ProvideGsonFactory implements Factory<Gson> {
  private final UtilsModule module;

  public UtilsModule_ProvideGsonFactory(UtilsModule module) {
    this.module = module;
  }

  @Override
  public Gson get() {
    return Preconditions.checkNotNull(
        module.provideGson(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<Gson> create(UtilsModule module) {
    return new UtilsModule_ProvideGsonFactory(module);
  }

  public static Gson proxyProvideGson(UtilsModule instance) {
    return instance.provideGson();
  }
}
