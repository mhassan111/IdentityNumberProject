package com.identitynumber.app.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public abstract class ActivitySigninBinding extends ViewDataBinding {
  @NonNull
  public final ScrollView ScrollView01;

  @NonNull
  public final RelativeLayout centerLayout;

  @NonNull
  public final TextView forgetPasswordText;

  @NonNull
  public final TextInputLayout inputEmailLayout;

  @NonNull
  public final TextInputLayout inputPasswordLayout;

  @NonNull
  public final TextView instructionText;

  @NonNull
  public final RelativeLayout loginLayout;

  @NonNull
  public final ImageView logoImage;

  @NonNull
  public final EditText password;

  @NonNull
  public final Button signIn;

  @NonNull
  public final TextView signUpLink;

  @NonNull
  public final EditText userName;

  protected ActivitySigninBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, ScrollView ScrollView01, RelativeLayout centerLayout,
      TextView forgetPasswordText, TextInputLayout inputEmailLayout,
      TextInputLayout inputPasswordLayout, TextView instructionText, RelativeLayout loginLayout,
      ImageView logoImage, EditText password, Button signIn, TextView signUpLink,
      EditText userName) {
    super(_bindingComponent, _root, _localFieldCount);
    this.ScrollView01 = ScrollView01;
    this.centerLayout = centerLayout;
    this.forgetPasswordText = forgetPasswordText;
    this.inputEmailLayout = inputEmailLayout;
    this.inputPasswordLayout = inputPasswordLayout;
    this.instructionText = instructionText;
    this.loginLayout = loginLayout;
    this.logoImage = logoImage;
    this.password = password;
    this.signIn = signIn;
    this.signUpLink = signUpLink;
    this.userName = userName;
  }

  @NonNull
  public static ActivitySigninBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivitySigninBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivitySigninBinding>inflate(inflater, com.identitynumber.app.R.layout.activity_signin, root, attachToRoot, component);
  }

  @NonNull
  public static ActivitySigninBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivitySigninBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivitySigninBinding>inflate(inflater, com.identitynumber.app.R.layout.activity_signin, null, false, component);
  }

  public static ActivitySigninBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ActivitySigninBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ActivitySigninBinding)bind(component, view, com.identitynumber.app.R.layout.activity_signin);
  }
}
