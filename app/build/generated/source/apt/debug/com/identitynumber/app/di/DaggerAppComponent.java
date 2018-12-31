package com.identitynumber.app.di;

import com.google.gson.Gson;
import com.identitynumber.app.activities.SignInActivity;
import com.identitynumber.app.activities.SignInActivity_MembersInjector;
import com.identitynumber.app.interfaces.ApiCallInterface;
import com.identitynumber.app.repository.Repository;
import com.identitynumber.app.viewModel.ViewModelFactory;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerAppComponent implements AppComponent {
  private Provider<Gson> provideGsonProvider;

  private Provider<OkHttpClient> getRequestHeaderProvider;

  private Provider<Retrofit> provideRetrofitProvider;

  private Provider<ApiCallInterface> getApiCallInterfaceProvider;

  private Provider<Repository> getRepositoryProvider;

  private DaggerAppComponent(Builder builder) {
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static AppComponent create() {
    return new Builder().build();
  }

  private ViewModelFactory getViewModelFactory() {
    return new ViewModelFactory(getRepositoryProvider.get());
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {
    this.provideGsonProvider =
        DoubleCheck.provider(UtilsModule_ProvideGsonFactory.create(builder.utilsModule));
    this.getRequestHeaderProvider =
        DoubleCheck.provider(UtilsModule_GetRequestHeaderFactory.create(builder.utilsModule));
    this.provideRetrofitProvider =
        DoubleCheck.provider(
            UtilsModule_ProvideRetrofitFactory.create(
                builder.utilsModule, provideGsonProvider, getRequestHeaderProvider));
    this.getApiCallInterfaceProvider =
        DoubleCheck.provider(
            UtilsModule_GetApiCallInterfaceFactory.create(
                builder.utilsModule, provideRetrofitProvider));
    this.getRepositoryProvider =
        DoubleCheck.provider(
            UtilsModule_GetRepositoryFactory.create(
                builder.utilsModule, getApiCallInterfaceProvider));
  }

  @Override
  public void doInjection(SignInActivity signInActivity) {
    injectSignInActivity(signInActivity);
  }

  private SignInActivity injectSignInActivity(SignInActivity instance) {
    SignInActivity_MembersInjector.injectViewModelFactory(instance, getViewModelFactory());
    return instance;
  }

  public static final class Builder {
    private UtilsModule utilsModule;

    private Builder() {}

    public AppComponent build() {
      if (utilsModule == null) {
        this.utilsModule = new UtilsModule();
      }
      return new DaggerAppComponent(this);
    }

    /**
     * @deprecated This module is declared, but an instance is not used in the component. This
     *     method is a no-op. For more, see https://google.github.io/dagger/unused-modules.
     */
    @Deprecated
    public Builder appModule(AppModule appModule) {
      Preconditions.checkNotNull(appModule);
      return this;
    }

    public Builder utilsModule(UtilsModule utilsModule) {
      this.utilsModule = Preconditions.checkNotNull(utilsModule);
      return this;
    }
  }
}
