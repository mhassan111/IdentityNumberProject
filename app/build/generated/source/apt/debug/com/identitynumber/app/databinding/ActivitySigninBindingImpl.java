package com.identitynumber.app.databinding;
import com.identitynumber.app.R;
import com.identitynumber.app.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
@javax.annotation.Generated("Android Data Binding")
public class ActivitySigninBindingImpl extends ActivitySigninBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.logoImage, 1);
        sViewsWithIds.put(R.id.instructionText, 2);
        sViewsWithIds.put(R.id.loginLayout, 3);
        sViewsWithIds.put(R.id.inputEmailLayout, 4);
        sViewsWithIds.put(R.id.userName, 5);
        sViewsWithIds.put(R.id.inputPasswordLayout, 6);
        sViewsWithIds.put(R.id.password, 7);
        sViewsWithIds.put(R.id.centerLayout, 8);
        sViewsWithIds.put(R.id.forgetPasswordText, 9);
        sViewsWithIds.put(R.id.signIn, 10);
        sViewsWithIds.put(R.id.signUpLink, 11);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivitySigninBindingImpl(@Nullable android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds));
    }
    private ActivitySigninBindingImpl(android.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.ScrollView) bindings[0]
            , (android.widget.RelativeLayout) bindings[8]
            , (android.widget.TextView) bindings[9]
            , (android.support.design.widget.TextInputLayout) bindings[4]
            , (android.support.design.widget.TextInputLayout) bindings[6]
            , (android.widget.TextView) bindings[2]
            , (android.widget.RelativeLayout) bindings[3]
            , (android.widget.ImageView) bindings[1]
            , (android.widget.EditText) bindings[7]
            , (android.widget.Button) bindings[10]
            , (android.widget.TextView) bindings[11]
            , (android.widget.EditText) bindings[5]
            );
        this.ScrollView01.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}