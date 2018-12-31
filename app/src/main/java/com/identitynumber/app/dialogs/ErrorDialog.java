package com.identitynumber.app.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import com.identitynumber.app.R;
import com.identitynumber.app.interfaces.CustomListener;

public class ErrorDialog extends Dialog {

    private CustomListener listener;
    public ErrorDialog(Context context, final CustomListener customListener, String title, String errorText) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.error_dialog);
        if (getWindow() == null)
            return;
//        getWindow().getDecorView().setBackground(new ColorDrawable(Color.TRANSPARENT));
        this.listener = customListener;
        TextView retry = findViewById(R.id.retryLink);
        TextView errorTitle = findViewById(R.id.title);
        TextView text = findViewById(R.id.text);
        errorTitle.setText(title);
        text.setText(errorText);
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                listener.onRetry();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
