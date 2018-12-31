package com.identitynumber.app.activities;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.TypedValue;
import android.widget.TextView;

import com.identitynumber.app.R;
import com.identitynumber.app.util.FontManager;
import com.identitynumber.app.util.TextDrawable;

public class BaseActivity extends AppCompatActivity {

    private Toolbar mToolBar;
    private TextView mTitle;

    protected void initializeToolBarViews() {
        mToolBar = findViewById(R.id.toolbar);
        mTitle = findViewById(R.id.toolbar_title);
    }

    protected void setUpToolBar(Activity mActivity, String title) {
        try {
            mTitle.setText(title);
            boolean instanceOfFamilyTree = mActivity instanceof FamilyMembersActivity;
            boolean isMainActivityInstance = mActivity instanceof MainActivity;
            boolean isIdentityVerificationInstance = mActivity instanceof IdentityVerificationActivity;

            boolean isWhiteToolBarStructure = instanceOfFamilyTree || isIdentityVerificationInstance;
            if (isWhiteToolBarStructure) {
                mTitle.setTextColor(ContextCompat.getColor(mActivity, R.color.whiteColor));
                mToolBar.setBackgroundColor(ContextCompat.getColor(mActivity, R.color.blueColor));
            } else if (isMainActivityInstance) {
                mTitle.setTextColor(ContextCompat.getColor(mActivity, R.color.whiteColor));
                mToolBar.setBackgroundColor(ContextCompat.getColor(mActivity, R.color.menuColor));
            }
            setSupportActionBar(mToolBar);
            ActionBar mActionBar = getSupportActionBar();
            mActionBar.setTitle("");
            if (isMainActivityInstance) {
                mActionBar.setDisplayHomeAsUpEnabled(true);
                TextDrawable faIcon = new TextDrawable(this);
                faIcon.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
                faIcon.setTextAlign(Layout.Alignment.ALIGN_CENTER);
                faIcon.setTypeface(FontManager.getTypeface(this, FontManager.FONT_AWESOME));
                faIcon.setText(getResources().getText(R.string.fa_menu_icon));
                faIcon.setTextColor(this.getResources().getColor(R.color.whiteColor));
                mActionBar.setHomeAsUpIndicator(faIcon);
            } else if (isWhiteToolBarStructure && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Drawable mDrawable = ContextCompat.getDrawable(mActivity, R.drawable.back_button);
                if (mDrawable != null) {
                    mDrawable.setColorFilter(new
                            PorterDuffColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN));
                    mActionBar.setHomeAsUpIndicator(mDrawable);
                }
            } else if (!isWhiteToolBarStructure && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mActionBar.setHomeAsUpIndicator(ContextCompat.getDrawable(mActivity, R.drawable.back_button));
            }
            mActionBar.setHomeButtonEnabled(true);
            mActionBar.setDisplayHomeAsUpEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void setToolBarTitle(String title) {
        mTitle.setText(title);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
