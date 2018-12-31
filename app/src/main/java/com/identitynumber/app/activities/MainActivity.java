package com.identitynumber.app.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.identitynumber.app.R;
import com.identitynumber.app.fragments.ResearchFragment;

public class MainActivity extends BaseActivity {

    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeToolBarViews();
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mNavigationView = findViewById(R.id.nav_view);
        mNavigationView.bringToFront();
        setUpToolBar(this, "Main");
        addNavigationViewClickListener();
    }

    private void addNavigationViewClickListener() {
        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        setSelectedMenuItem(menuItem);
                        openSelectedMenuItemFragment(menuItem.getTitle().toString());
                        closeDrawerLayout();
                        return true;
                    }
                });
    }

    private void openSelectedMenuItemFragment(String title) {
        Fragment fragment = null;
        String TAG = null;
        switch (title) {
            case "Research":
                fragment = ResearchFragment.newInstance();
                TAG = ResearchFragment.TAG;
                break;
        }
        if (fragment != null) {
            setToolBarTitle(title);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment, TAG)
                    .commit();
            getSupportFragmentManager()
                    .executePendingTransactions();
        }
    }

    private void setSelectedMenuItem(MenuItem menuItem) {
        menuItem.setChecked(true);
    }

    private void closeDrawerLayout() {
        if (mDrawerLayout.isDrawerOpen(mNavigationView)) {
            mDrawerLayout.closeDrawer(mNavigationView);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        closeDrawerLayout();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
