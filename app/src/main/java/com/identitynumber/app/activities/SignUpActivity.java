package com.identitynumber.app.activities;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.identitynumber.app.R;
import com.identitynumber.app.adapters.AutoCompleteAdapter;
import com.identitynumber.app.apiCalls.GETCall;
import com.identitynumber.app.apiCalls.POSTCall;
import com.identitynumber.app.apiCalls.SignUpCall;
import com.identitynumber.app.dialogs.ErrorDialog;
import com.identitynumber.app.dialogs.LoaderDialog;
import com.identitynumber.app.inputParsing.SignUp;
import com.identitynumber.app.interfaces.CustomListener;
import com.identitynumber.app.interfaces.Response;
import com.identitynumber.app.interfaces.ServerResponse;
import com.identitynumber.app.responseParsing.Country;
import com.identitynumber.app.util.Utils;
import com.orhanobut.logger.Logger;
import com.squareup.okhttp.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignUpActivity extends BaseActivity implements View.OnClickListener, AutoCompleteAdapter.FilterListeners {

    private static final String deviceType = "android";
    private static final int TYPE_COUNTRIES_ERROR = 1;
    private static final int TYPE_INVALID_DATA_ERROR = 2;
    private static final int TYPE_SIGN_UP_ERROR = 3;
    private AutoCompleteTextView country, state;
    private EditText firstName, lastName, emailAddress, password, reEnteredPassword, dateOfBirth, mobileNumber;
    private Button signUp;
    private String gender = "1";
    private ArrayList<Country> countryList = new ArrayList<>();
    private ArrayList<String> statesList = new ArrayList<>();
    private Calendar mCalendar = Calendar.getInstance();
    private TextInputLayout firstNameLayout, lastNameLayout, emailAddressLayout, passwordLayout, reEnteredPasswordLayout,
            dateOfBirthLayout, mobileNumberLayout, countryLayout, stateLayout;
    private RadioButton femaleButton;
    private RadioButton maleButton;
    private CircleImageView profileImage;
    private final int imageSelection = 100;
    private String profileImagePath = null;
    private ArrayList<Country> filteredCountries = new ArrayList<>();
    private Country selectedCountry = null;
    private AutoCompleteAdapter mStateAdapter, mCountryAdapter;
    private LoaderDialog loaderDialog = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initializeToolBarViews();
        initializeViews();
        setUpToolBar(this, "Sign Up");
        signUPButtonClickListener();
        addDateOfBirthClickListener();
        retrieveCountriesList();
        setCountryTextChangeListener();
        addCountrySelectionListener();
        maleButton.setOnClickListener(this);
        femaleButton.setOnClickListener(this);
        profileImage.setOnClickListener(this);
    }

    private void addCountrySelectionListener() {
        country.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                retrieveStateListFromCountryName(position);
            }
        });
    }

    private void setCountryTextChangeListener() {
        country.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (selectedCountry != null && !charSequence.toString().equals(selectedCountry.getTitle())) {
                    mStateAdapter.clear();
                    statesList.clear();
                    statesList = new ArrayList<>();
                    mStateAdapter = new AutoCompleteAdapter(SignUpActivity.this, statesList,
                            android.R.layout.simple_dropdown_item_1line);
                    state.setAdapter(mStateAdapter);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private void retrieveStateListFromCountryName(int pos) {
        this.selectedCountry = filteredCountries.get(pos);
        POSTCall postCall = new POSTCall(this, Utils.TYPE_STATES_CALL, new ServerResponse() {
            @Override
            public void onSuccess(JSONObject response) {
                try {
                    if (response.getString("statusCode").equals("200")) {
                        JSONArray message = response.getJSONArray("message");
                        statesList.clear();
                        for (int i = 0; i < message.length(); i++) {
                            statesList.add(message.getJSONObject(i).getString("state"));
                        }
                        mStateAdapter = new AutoCompleteAdapter(SignUpActivity.this, statesList,
                                android.R.layout.simple_dropdown_item_1line);
                        mStateAdapter.setFilterListeners(SignUpActivity.this);
                        state.setAdapter(mStateAdapter);
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            }

            @Override
            public void onFailure(VolleyError error) {
            }
        });
        try {
            postCall.callService(new JSONObject()
                    .put("country", selectedCountry.getCountry()));
        } catch (JSONException e) {
            e.getMessage();
        }
    }

    private void retrieveCountriesList() {
        GETCall countryListCall = new GETCall(this, Utils.TYPE_GET_COUNTRIES, new ServerResponse() {
            @Override
            public void onSuccess(JSONObject response) {
                hideLoader();
                try {
                    if (response.getString("statusCode").equals("200")) {
                        JSONArray message = response.getJSONArray("message");
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        Gson gson = gsonBuilder.create();
                        countryList.addAll(Arrays.asList(gson.fromJson(message.toString(), Country[].class)));
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
                mCountryAdapter = new AutoCompleteAdapter(SignUpActivity.this,
                        android.R.layout.simple_dropdown_item_1line, countryList);
                mCountryAdapter.setFilterListeners(SignUpActivity.this);
                country.setAdapter(mCountryAdapter);
            }

            @Override
            public void onFailure(VolleyError error) {
                hideLoader();
                showErrorDialog("Error Loading", "Please try again", TYPE_COUNTRIES_ERROR);
                Logger.d("Error retrieving Countries: " + error.getMessage());
            }
        });
        countryListCall.callService();
        showLoader();
    }

    private void hideLoader() {
        if (loaderDialog != null && loaderDialog.isShowing()) {
            loaderDialog.dismiss();
        }
    }

    private void showLoader() {
        if (loaderDialog == null) {
            loaderDialog = new LoaderDialog(SignUpActivity.this);
            loaderDialog.setCanceledOnTouchOutside(false);
            loaderDialog.setCancelable(false);
        }
        loaderDialog.show();
    }

    @Override
    public void filteringFinished(ArrayList<Country> filteredCountries, ArrayList<String> filteredStates, int type) {
        if (type == 1)
            this.filteredCountries = filteredCountries;
    }

    private void addDateOfBirthClickListener() {
        dateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(SignUpActivity.this);
            }
        });
    }

    private void initializeViews() {
        profileImage = findViewById(R.id.profile_image);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        emailAddress = findViewById(R.id.emailAddress);
        password = findViewById(R.id.password);
        reEnteredPassword = findViewById(R.id.reenterPassword);
        dateOfBirth = findViewById(R.id.dateOfBirth);
        mobileNumber = findViewById(R.id.mobileNumber);
        country = findViewById(R.id.country);
        state = findViewById(R.id.state);
        signUp = findViewById(R.id.signUp);

        firstNameLayout = findViewById(R.id.firstNameEditText);
        lastNameLayout = findViewById(R.id.lastNameEditText);
        emailAddressLayout = findViewById(R.id.emailAddressEditText);
        passwordLayout = findViewById(R.id.passwordEditText);
        reEnteredPasswordLayout = findViewById(R.id.reenterPasswordEditText);
        dateOfBirthLayout = findViewById(R.id.dateOfBirthEditText);
        mobileNumberLayout = findViewById(R.id.mobileEditText);
        countryLayout = findViewById(R.id.countryEditText);
        stateLayout = findViewById(R.id.stateEditText);
        femaleButton = findViewById(R.id.radioFemale);
        maleButton = findViewById(R.id.radioMale);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.radioMale:
                maleButton.setButtonDrawable(ContextCompat.getDrawable(SignUpActivity.this, R.drawable.radiobtn_selected));
                femaleButton.setButtonDrawable(ContextCompat.getDrawable(SignUpActivity.this, R.drawable.radiobtn_unselected));
                gender = "1";
                break;
            case R.id.radioFemale:
                femaleButton.setButtonDrawable(ContextCompat.getDrawable(SignUpActivity.this, R.drawable.radiobtn_selected));
                maleButton.setButtonDrawable(ContextCompat.getDrawable(SignUpActivity.this, R.drawable.radiobtn_unselected));
                gender = "0";
                break;
            case R.id.profile_image:
                selectProfileImageFromGallery();
                break;
        }
    }

    private void selectProfileImageFromGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Profile Picture"), imageSelection);
    }

    private void signUPButtonClickListener() {
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });
    }

    private void showDatePickerDialog(Context context) {
        new DatePickerDialog(context,
                dateSetListener,
                mCalendar.get(Calendar.YEAR),
                mCalendar.get(Calendar.MONTH),
                mCalendar.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            mCalendar.set(Calendar.YEAR, year);
            mCalendar.set(Calendar.MONTH, monthOfYear);
            mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            dateOfBirth.setText(sdf.format(mCalendar.getTime()));
        }
    };

    private void signUp() {
        String fName = firstName.getText().toString().trim();
        String lName = lastName.getText().toString().trim();
        String eAddress = emailAddress.getText().toString().trim();
        String passwordText = password.getText().toString().trim();
        String reenteredPasswordText = reEnteredPassword.getText().toString().trim();
        String dateOfBirthText = dateOfBirth.getText().toString().trim();
        String mNumber = mobileNumber.getText().toString().trim();
        String countryName = country.getText().toString().trim();
        String stateName = state.getText().toString().trim();

        boolean fNameValidated = validateInput(firstNameLayout, fName);
        boolean lNameValidated = validateInput(lastNameLayout, lName);
        boolean eAddressValidated = validateInput(emailAddressLayout, eAddress);
        if (eAddressValidated) {
            eAddressValidated = validateEmailAddress(eAddress);
        }
        boolean passwordTextValidated = validateInput(passwordLayout, passwordText);
        boolean reenteredPasswordValidated = validateInput(reEnteredPasswordLayout, reenteredPasswordText);
        boolean dateOfBirthValidated = validateInput(dateOfBirthLayout, dateOfBirthText);
        boolean mobileNumberValidated = validateInput(mobileNumberLayout, mNumber);
        boolean countryNameValidated = validateInput(countryLayout, countryName);
        boolean stateValidated = validateInput(stateLayout, stateName);

        SignUp signUp = new SignUp(
                fName,
                lName,
                eAddress,
                passwordText,
                dateOfBirthText,
                mNumber,
                countryName,
                deviceType,
                gender,
                stateName,
                profileImagePath
        );


        boolean doesPasswordMatch = false;
        if (passwordTextValidated && reenteredPasswordValidated
                && passwordText.equals(reenteredPasswordText)) {
            doesPasswordMatch = true;
        } else {
            password.setText("");
            reEnteredPassword.setText("");
            passwordLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.error_background));
            reEnteredPasswordLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.error_background));
        }
        if (profileImagePath == null) {
            Toast.makeText(getApplicationContext(), "Please select Profile Image", Toast.LENGTH_LONG).show();
            return;
        }
        if (fNameValidated && lNameValidated && eAddressValidated && doesPasswordMatch && dateOfBirthValidated
                && mobileNumberValidated && countryNameValidated && stateValidated)
            callSignUPService(signUp);
        else {
            showErrorDialog("Error", "Please enter valid data", TYPE_INVALID_DATA_ERROR);
        }
    }

    private void showErrorDialog(String title, String text, final int type) {
        ErrorDialog errorDialog = new ErrorDialog(SignUpActivity.this, new CustomListener() {
            @Override
            public void onRetry() {
                if (type != TYPE_COUNTRIES_ERROR)
                    return;
                showLoader();
                retrieveCountriesList();
            }
        }, title, text);
        errorDialog.setCancelable(false);
        errorDialog.setCanceledOnTouchOutside(false);
        errorDialog.show();
    }

    private boolean validateInput(TextInputLayout inputLayout, String inputData) {
        if (inputLayout == passwordLayout || inputLayout == reEnteredPasswordLayout) {
            if (inputData.length() >= 6) {
                inputLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.edittext_background));
                return true;
            } else {
                inputLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.error_background));
                return false;
            }
        }
        if (TextUtils.isEmpty(inputData)) {
            inputLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.error_background));
            return false;
        } else {
            inputLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.edittext_background));
            return true;
        }
    }

    private boolean validateEmailAddress(String emailAddress) {
        if (Utils.isEmailAddressValid(emailAddress)) {
            emailAddressLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.edittext_background));
            return true;
        } else {
            emailAddressLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.error_background));
            return false;
        }
    }

    private void callSignUPService(SignUp signUp) {
        Gson gson = new Gson();
        try {
            JSONObject signUpObject = new JSONObject(gson.toJson(signUp));
            SignUpCall signUpCall = new SignUpCall(this, new Response() {
                @Override
                public void onSuccess(com.squareup.okhttp.Response response) {
                    onSignUPSucceed(response);
                }

                @Override
                public void onFailed(Request request, IOException e) {
                    onSignUpFailed(request, e);
                }
            });
            signUpCall.callService(signUpObject);
            showLoader();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void onSignUPSucceed(com.squareup.okhttp.Response response) {
        if (response.isSuccessful()) {
            Logger.d("User Signed Up successfully");
        } else {
            Logger.d("Error Signing Up User: " + response.toString());
        }
        hideLoader();
    }

    private void onSignUpFailed(Request request, IOException e) {
        hideLoader();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showErrorDialog("SignUP Error", "Please try Again", TYPE_SIGN_UP_ERROR);
            }
        });
        Logger.d("Error Signing Up User: " + e.getMessage());
    }

    private void setProfileImagePath(String path) {
        this.profileImagePath = path;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == imageSelection) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                profileImage.setImageBitmap(bitmap);
                String path = getRealPathFromURI(uri);
                if (path == null || TextUtils.isEmpty(path)) {
                    path = getRealPathFromURI(this, uri);
                }
                setProfileImagePath(path);
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }

    private String getRealPathFromURI(Uri contentURI) {
        String result = null;
        try {
            Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
            if (cursor == null) {
                result = contentURI.getPath();
            } else {
                if (cursor.moveToFirst()) {
                    int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    result = cursor.getString(idx);
                }
                cursor.close();
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return result;
    }

    @SuppressLint("NewApi")
    public static String getRealPathFromURI(Context context, Uri uri) {
        String filePath = "";
        try {
            String wholeID = DocumentsContract.getDocumentId(uri);
            String id = wholeID.split(":")[1];
            String[] column = {MediaStore.Images.Media.DATA};
            String sel = MediaStore.Images.Media._ID + "=?";
            Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    column, sel, new String[]{id}, null);
            int columnIndex = cursor.getColumnIndex(column[0]);
            if (cursor.moveToFirst()) {
                filePath = cursor.getString(columnIndex);
            }
            cursor.close();
        } catch (Exception e) {
            e.getMessage();
        }
        return filePath;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (!isFinishing()) {
            finish();
        }
    }
}
