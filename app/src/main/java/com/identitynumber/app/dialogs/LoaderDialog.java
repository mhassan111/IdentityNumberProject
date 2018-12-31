package com.identitynumber.app.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.identitynumber.app.R;

public class LoaderDialog extends Dialog {

    public LoaderDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.loader_dialog);
        Window mWindow = getWindow();
        if (mWindow == null)
            return;
        getWindow().getDecorView().setBackground(new ColorDrawable(Color.TRANSPARENT));
    }
}
