package com.identitynumber.app.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.identitynumber.app.R;
import com.identitynumber.app.adapters.ResearchListAdapter;
import com.identitynumber.app.util.ActivityUtil;

public class ResearchFragment extends Fragment {

    public static final String TAG = ResearchFragment.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private ResearchListAdapter mAdapter = null;
    private Context mContext;
    static String[] items = {
            "Identity Verification", "Death Notices", "Family Tree", "SA Family Finder", "Marriage Transcriptions", "British Concentration Camps",
            "Maseti Files", "Immigration Information", "Old Marriage Records", "SA Voters Roll", "SA Companies", "World War Deaths",
            "Legal Gazettes", "Online Verification", "External Resources", "Contributors", "Claim Your Identity"
    };

    static int[] itemDrawables = {
            R.drawable.identity_verification,
            R.drawable.death_notices,
            R.drawable.family_tree_icon,
            R.drawable.family_finder,
            R.drawable.marriage_transcriptions,
            R.drawable.camps,
            R.drawable.files,
            R.drawable.info,
            R.drawable.records,
            R.drawable.voter,
            R.drawable.companies,
            R.drawable.world_war,
            R.drawable.legal,
            R.drawable.online,
            R.drawable.external,
            R.drawable.contributer,
            R.drawable.claim
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_research, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.researchListRV);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, LinearLayout.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new ResearchListAdapter(mContext, items, itemDrawables);
        mAdapter.setOnItemClick(new ResearchListAdapter.onItemClick() {
            @Override
            public void onClick(int position) {
                onResearchItemClicked(position);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    private void onResearchItemClicked(int position) {
        String title = items[position];
        switch (title){
            case "Identity Verification":
                ActivityUtil.launchIdentityVerificationActivity(mContext);
                break;
        }
    }

    public static ResearchFragment newInstance() {
        return new ResearchFragment();
    }
}
