package com.identitynumber.app.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.identitynumber.app.R;
import com.identitynumber.app.activities.FamilyMembersActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FamilyMembersFragment extends Fragment {

    private static final String familyMemberType = "familyMemberType";
    private EditText dateOfBirth, dateOfDeath;
    private RadioButton living;
    private Calendar mCalendar = Calendar.getInstance();
    private TextView name;
    private int memberType;
    private Context mContext;
    private Button next;
    private RadioGroup radioLiving;
    private boolean isLiving = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            memberType = bundle.getInt(familyMemberType);
        }
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_family_member, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dateOfBirth = view.findViewById(R.id.dateOfBirth);
        dateOfDeath = view.findViewById(R.id.yearOfDeath);
        name = view.findViewById(R.id.name);
        radioLiving = view.findViewById(R.id.radioLiving);
        living = view.findViewById(R.id.living);
        next = view.findViewById(R.id.next);
        setFamilyMemberTitle();
        addDateOfBirthClickListener();
        addDateOfDeathClickListener();
        addLivingButtonClickListener();
        addFamilyMemberClickListener();
    }

    private void addFamilyMemberClickListener() {
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFamilyMemberClickListener();
            }
        });
    }

    private void addFamilyMember() {

    }

    private void setFamilyMemberTitle() {
        String title = "";
        switch (memberType) {
            case FamilyMembersActivity.TYPE_MOTHER:
                title = "Your Mother";
                break;
            case FamilyMembersActivity.TYPE_FATHER:
                title = "Your Father";
                break;
            case FamilyMembersActivity.TYPE_MATERNAL_GRANDMOTHER:
                title = "Your Maternal Grandmother";
                break;
            case FamilyMembersActivity.TYPE_MATERNAL_GRANDFATHER:
                title = "Your Maternal Grandfather";
                break;
            case FamilyMembersActivity.TYPE_PATERNAL_GRANDMOTHER:
                title = "Your Paternal Grandmother";
                break;
            case FamilyMembersActivity.TYPE_PATERNAL_GRANDFATHER:
                title = "Your Paternal Grandfather";
                break;
        }
        name.setText(title);
    }

    private void addLivingButtonClickListener() {
        living.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLivingButtonDrawable();
            }
        });
    }

    private void setLivingButtonDrawable() {
        Drawable selectedDrawable = ContextCompat.getDrawable(mContext, R.drawable.radiobtn_selected);
        Drawable unSelectedDrawable = ContextCompat.getDrawable(mContext, R.drawable.radiobtn_unselected);
        if (isLiving) {
            living.setButtonDrawable(unSelectedDrawable);
            isLiving = false;
        } else {
            living.setButtonDrawable(selectedDrawable);
            isLiving = true;
        }
    }

    protected void addDateOfDeathClickListener() {
        dateOfDeath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(getActivity(), dateOfDeathListener);
            }
        });
    }

    private void addDateOfBirthClickListener() {
        dateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(getActivity(), dateOfBirthListener);
            }
        });
    }

    private void showDatePickerDialog(Context context, DatePickerDialog.OnDateSetListener onDateSetListener) {
        new DatePickerDialog(context,
                onDateSetListener,
                mCalendar.get(Calendar.YEAR),
                mCalendar.get(Calendar.MONTH),
                mCalendar.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    DatePickerDialog.OnDateSetListener dateOfBirthListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            mCalendar.set(Calendar.YEAR, year);
            mCalendar.set(Calendar.MONTH, monthOfYear);
            mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            dateOfBirth.setText(sdf.format(mCalendar.getTime()));
        }
    };

    DatePickerDialog.OnDateSetListener dateOfDeathListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            mCalendar.set(Calendar.YEAR, year);
            mCalendar.set(Calendar.MONTH, monthOfYear);
            mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            dateOfDeath.setText(sdf.format(mCalendar.getTime()));
        }
    };

    /**
     * create new Instance of Fragment
     *
     * @param memberType member Type
     * @return fragment
     */
    public static FamilyMembersFragment newInstance(int memberType) {
        FamilyMembersFragment fragment = new FamilyMembersFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(familyMemberType, memberType);
        fragment.setArguments(bundle);
        return fragment;
    }
}
