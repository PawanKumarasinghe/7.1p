package com.example.lostfound;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class PostFormScreen extends AppCompatActivity {
    EditText inputName, inputContact, inputDetails, inputWhen, inputPlace;
    Spinner spinnerType;
    Button submitBtn;
    StorageHelper storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_form_screen);

        spinnerType = findViewById(R.id.spinnerPostType);
        inputName = findViewById(R.id.fieldName);
        inputContact = findViewById(R.id.fieldContact);
        inputDetails = findViewById(R.id.fieldDetails);
        inputWhen = findViewById(R.id.fieldWhen);
        inputPlace = findViewById(R.id.fieldPlace);
        submitBtn = findViewById(R.id.btnSubmit);
        storage = new StorageHelper(this);

        // Setup Spinner with post type options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.post_types,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapter);

        // Setup DatePicker for inputWhen
        inputWhen.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePicker = new DatePickerDialog(this,
                    (view, year1, month1, dayOfMonth) -> {
                        String date = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
                        inputWhen.setText(date);
                    }, year, month, day);
            datePicker.show();
        });

        // Submit Button Listener
        submitBtn.setOnClickListener(view -> {
            String category = spinnerType.getSelectedItem().toString();
            storage.addEntry(
                    category,
                    inputName.getText().toString(),
                    inputContact.getText().toString(),
                    inputDetails.getText().toString(),
                    inputWhen.getText().toString(),
                    inputPlace.getText().toString()
            );
            finish();
        });
    }
}
