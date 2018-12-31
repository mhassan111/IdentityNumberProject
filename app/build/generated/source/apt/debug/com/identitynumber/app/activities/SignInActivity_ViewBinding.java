// Generated code from Butter Knife. Do not modify!
package com.identitynumber.app.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.identitynumber.app.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SignInActivity_ViewBinding implements Unbinder {
  private SignInActivity target;

  private View view2131230812;

  private View view2131230942;

  @UiThread
  public SignInActivity_ViewBinding(SignInActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SignInActivity_ViewBinding(final SignInActivity target, View source) {
    this.target = target;

    View view;
    target.userName = Utils.findRequiredViewAsType(source, R.id.userName, "field 'userName'", EditText.class);
    target.password = Utils.findRequiredViewAsType(source, R.id.password, "field 'password'", EditText.class);
    target.emailLayout = Utils.findRequiredViewAsType(source, R.id.inputEmailLayout, "field 'emailLayout'", TextInputLayout.class);
    target.passwordLayout = Utils.findRequiredViewAsType(source, R.id.inputPasswordLayout, "field 'passwordLayout'", TextInputLayout.class);
    target.signUpLink = Utils.findRequiredViewAsType(source, R.id.signUpLink, "field 'signUpLink'", TextView.class);
    view = Utils.findRequiredView(source, R.id.forgetPasswordText, "method 'onForgetPasswordClicked'");
    view2131230812 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onForgetPasswordClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.signIn, "method 'onLoginClicked'");
    view2131230942 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onLoginClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    SignInActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.userName = null;
    target.password = null;
    target.emailLayout = null;
    target.passwordLayout = null;
    target.signUpLink = null;

    view2131230812.setOnClickListener(null);
    view2131230812 = null;
    view2131230942.setOnClickListener(null);
    view2131230942 = null;
  }
}
