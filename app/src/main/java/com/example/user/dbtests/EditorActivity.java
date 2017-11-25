package com.example.user.dbtests;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.user.dbtests.data.HotelContract;

public class EditorActivity extends AppCompatActivity {

    private EditText mNameEditText;
    private EditText mCityEditText;
    private EditText mAgeEditText;

    private Spinner mGenderSpinner;

    /**
     * Пол для гостя. Возможные варианты:
     * 0 для кошки, 1 для кота, 2 - не определен.
     */
    private int mGender = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        mNameEditText = (EditText) findViewById(R.id.edit_guest_name);
        mCityEditText = (EditText) findViewById(R.id.edit_guest_city);
        mAgeEditText = (EditText) findViewById(R.id.edit_guest_age);
        mGenderSpinner = (Spinner) findViewById(R.id.spinner_gender);

        setupSpinner();
    }

    /**
     * Настраиваем spinner для выбора пола у гостя.
     */
    private void setupSpinner() {

        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_gender_options, android.R.layout.simple_spinner_item);

        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        mGenderSpinner.setAdapter(genderSpinnerAdapter);
        mGenderSpinner.setSelection(2);

        mGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (selection.equals(getString(R.string.gender_female))) {
                    mGender = HotelContract.GuestEntry.GENDER_FEMALE; // Кошка
                } else if (selection.equals(getString(R.string.gender_male))) {
                    mGender = HotelContract.GuestEntry.GENDER_MALE; // Кот
                } else {
                    mGender = HotelContract.GuestEntry.GENDER_UNKNOWN; // Не определен
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mGender = 2; // Unknown
            }
        });
    }
}
