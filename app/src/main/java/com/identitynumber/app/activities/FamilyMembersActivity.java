package com.identitynumber.app.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.github.vivchar.viewpagerindicator.ViewPagerIndicator;
import com.identitynumber.app.R;
import com.identitynumber.app.fragments.FamilyMembersFragment;

public class FamilyMembersActivity extends BaseActivity {

    public static final int TYPE_MOTHER = 1;
    public static final int TYPE_FATHER = 2;
    public static final int TYPE_MATERNAL_GRANDMOTHER = 3;
    public static final int TYPE_MATERNAL_GRANDFATHER = 4;
    public static final int TYPE_PATERNAL_GRANDMOTHER = 5;
    public static final int TYPE_PATERNAL_GRANDFATHER = 6;

    private ViewPager mViewPager;
    private ViewPagerIndicator mViewPagerIndicator;
    private static final int totalPages = 6;
    private FamilyMembersFragment mMotherInfoFragment;
    private FamilyMembersFragment mFatherInfoFragment;
    private FamilyMembersFragment mMaternalGrandMotherInfoFragment;
    private FamilyMembersFragment mMaternalGrandFatherInfoFragment;
    private FamilyMembersFragment mPaternalGrandMotherInfoFragment;
    private FamilyMembersFragment mPaternalGrandFatherInfoFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_members);
        initializeToolBarViews();
        setUpToolBar(this, "Add Family");
        mViewPager = findViewById(R.id.viewpager);
        mViewPagerIndicator = findViewById(R.id.view_pager_indicator);
        ViewPagerAdapter mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPagerIndicator.setupWithViewPager(mViewPager);
    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        private ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    if (mMotherInfoFragment == null)
                        mMotherInfoFragment = FamilyMembersFragment.newInstance(TYPE_MOTHER);
                    return mMotherInfoFragment;
                case 1:
                    if (mFatherInfoFragment == null)
                        mFatherInfoFragment = FamilyMembersFragment.newInstance(TYPE_FATHER);
                    return mFatherInfoFragment;
                case 2:
                    if (mMaternalGrandMotherInfoFragment == null)
                        mMaternalGrandMotherInfoFragment = FamilyMembersFragment.newInstance(TYPE_MATERNAL_GRANDMOTHER);
                    return mMaternalGrandMotherInfoFragment;
                case 3:
                    if (mMaternalGrandFatherInfoFragment == null)
                        mMaternalGrandFatherInfoFragment = FamilyMembersFragment.newInstance(TYPE_MATERNAL_GRANDFATHER);
                    return mMaternalGrandFatherInfoFragment;
                case 4:
                    if (mPaternalGrandMotherInfoFragment == null)
                        mPaternalGrandMotherInfoFragment = FamilyMembersFragment.newInstance(TYPE_PATERNAL_GRANDMOTHER);
                    return mPaternalGrandMotherInfoFragment;
                case 5:
                    if (mPaternalGrandFatherInfoFragment == null)
                        mPaternalGrandFatherInfoFragment = FamilyMembersFragment.newInstance(TYPE_PATERNAL_GRANDFATHER);
                    return mPaternalGrandFatherInfoFragment;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return totalPages;
        }
    }
}
