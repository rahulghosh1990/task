package com.example.singin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registration extends AppCompatActivity {

    TextInputEditText Email, Password, firstName, lastName, phoneNumber, dateOfBirth, confirmPassword;
    TextView tv_male, tv_female, Header;
    Button Register;
    TextView log_in;
    String gndr;
    String first_name, last_name, dob, phone, email, pW, cPW;
    Boolean EditTextEmptyHolder;
    SQLiteDatabase sqLiteDatabaseObj;
    String SQLiteDataBaseQueryHolder;
    SqliteHelperClass sqLiteHelper;
    Cursor cursor;
    String F_Result = "Not_Found";
    Calendar myCalendar;
    boolean textViewIsClicked;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle(R.string.regis);
        /*Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Register = findViewById(R.id.btn_continue);
        Email = findViewById(R.id.tie_email);
        tv_male = findViewById(R.id.tv_male);
        tv_female = findViewById(R.id.tv_female);
        Password = findViewById(R.id.tie_pw);
        confirmPassword = findViewById(R.id.tie_cpw);
        firstName = findViewById(R.id.tie_fname);
        lastName = findViewById(R.id.tie_flname);
        phoneNumber = findViewById(R.id.tie_phone);
        dateOfBirth = findViewById(R.id.tie_dob);
        log_in = findViewById(R.id.log_in);
        myCalendar = Calendar.getInstance();
        dateOfBirth = findViewById(R.id.tie_dob);
        firstName.addTextChangedListener(loginTextWatcher);
        lastName.addTextChangedListener(loginTextWatcher);
        phoneNumber.addTextChangedListener(loginTextWatcher);
        dateOfBirth.addTextChangedListener(loginTextWatcher);
        Email.addTextChangedListener(loginTextWatcher);
        Password.addTextChangedListener(loginTextWatcher);
        confirmPassword.addTextChangedListener(loginTextWatcher);
        tv_male.addTextChangedListener(loginTextWatcher);
        tv_female.addTextChangedListener(loginTextWatcher);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        dateOfBirth.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(Registration.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        tv_male.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View v) {
                textViewIsClicked = true;
                tv_male.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tv_border_clicked));
                tv_female.setBackground(getResources().getDrawable(R.drawable.tv_border));
                gndr = "Male";

            }
        });

        tv_female.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View v) {
                textViewIsClicked = true;
                tv_female.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tv_border_clicked));
                tv_male.setBackground(getResources().getDrawable(R.drawable.tv_border));
                gndr = "Female";

            }
        });
        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registration.this, MainActivity.class);
                startActivity(intent);
            }
        });
        sqLiteHelper = new SqliteHelperClass(this);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDataBaseBuild();
                SQLiteTableBuild();
                CheckEditTextStatus();
                CheckingEmailAlreadyExistsOrNot();
                EmptyEditTextAfterDataInsert();
            }
        });
    }

    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            first_name = firstName.getText().toString().trim();
            last_name = lastName.getText().toString().trim();
            dob = dateOfBirth.getText().toString().trim();
            email = Email.getText().toString().trim();
            pW = Password.getText().toString().trim();
            cPW = confirmPassword.getText().toString().trim();
            phone = phoneNumber.getText().toString().trim();
            if (TextUtils.isEmpty(first_name) || TextUtils.isEmpty(last_name) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(email) || TextUtils.isEmpty(email)
                    || TextUtils.isEmpty(pW) || TextUtils.isEmpty(cPW) || TextUtils.isEmpty(dob) || !textViewIsClicked) {

                EditTextEmptyHolder = false;
            } else {
                EditTextEmptyHolder = true;
            }
            Register.setEnabled(!first_name.isEmpty() && !last_name.isEmpty() && !dob.isEmpty() && !email.isEmpty() && !pW.isEmpty()
                    && !cPW.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    private void updateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        dateOfBirth.setText(sdf.format(myCalendar.getTime()));
    }

    public void SQLiteDataBaseBuild() {
        sqLiteDatabaseObj = openOrCreateDatabase(SqliteHelperClass.DATABASE_NAME, Context.MODE_PRIVATE, null);
    }

    public void SQLiteTableBuild() {
        sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS " + SqliteHelperClass.TABLE_NAME + "(" + SqliteHelperClass.Table_Column_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SqliteHelperClass.Table_Column_1_fName + " VARCHAR, " + SqliteHelperClass.Table_Column_2_lName + " VARCHAR, " + SqliteHelperClass.Table_Column_3_Dob + " VARCHAR, " + SqliteHelperClass.Table_Column_4_email + " VARCHAR, " + SqliteHelperClass.Table_Column_5_phone + " VARCHAR, " + SqliteHelperClass.Table_Column_6_PW + " VARCHAR, " + SqliteHelperClass.Table_Column_7_CPW + " VARCHAR, " + SqliteHelperClass.Table_Column_8_GNDR + " VARCHAR);");
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void InsertDataIntoSQLiteDatabase() {
        if (EditTextEmptyHolder == true) {
            SQLiteDataBaseQueryHolder = "INSERT INTO " + SqliteHelperClass.TABLE_NAME + " (firstname,lastname,dateofbirth,emailaddress,phonenumber,password,confirmpassword,gender) VALUES('" + first_name + "', '" + last_name + "', '" + dob + "', '" + email + "', '" + phone + "',  '" + pW + "', '" + cPW + "', '" + gndr + "');";
            sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);
            sqLiteDatabaseObj.close();
            Toast.makeText(Registration.this, "User Registered Successfully", Toast.LENGTH_LONG).show();
            Register.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.confirm_tv_border));
            tv_female.setBackground(getResources().getDrawable(R.drawable.tv_border));
            tv_male.setBackground(getResources().getDrawable(R.drawable.tv_border));
        } else {
            Toast.makeText(Registration.this, "Please Fill All The Required Fields.", Toast.LENGTH_SHORT).show();
        }
    }

    // Empty edittext after done inserting process method.
    public void EmptyEditTextAfterDataInsert() {
        firstName.getText().clear();
        lastName.getText().clear();
        dateOfBirth.getText().clear();
        phoneNumber.getText().clear();
        confirmPassword.getText().clear();
        Email.getText().clear();
        Password.getText().clear();
    }

    // Method to check EditText is empty or Not.
    public void CheckEditTextStatus() {
        // Getting value from All EditText and storing into String Variables.

        String regex = "\\d{10}";
        Pattern pattern = Pattern.compile(regex);
        //Creating a Matcher object
        Matcher matcher = pattern.matcher(phone);
        //Verifying whether given phone number is valid
        if (matcher.matches()) {
            System.out.println("Given phone number is valid");
        } else {
            System.out.println("Given phone number is not valid");
        }
        email = Email.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (email.trim().matches(emailPattern)) {
            Log.d("Rahul", "Valid email");
        } else {
            Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
        }
    }

    // Checking Email is already exists or not.
    public void CheckingEmailAlreadyExistsOrNot() {
        // Opening SQLite database write permission.
        sqLiteDatabaseObj = sqLiteHelper.getWritableDatabase();
        // Adding search email query to cursor.
        cursor = sqLiteDatabaseObj.query(SqliteHelperClass.TABLE_NAME, null, " " + SqliteHelperClass.Table_Column_4_email + "=?", new String[]{email}, null, null, null);
        while (cursor.moveToNext()) {

            if (cursor.isFirst()) {

                cursor.moveToFirst();

                // If Email is already exists then Result variable value set as Email Found.
                F_Result = "Email Found";

                // Closing cursor.
                cursor.close();
            }
        }
        // Calling method to check final result and insert data into SQLite database.
        CheckFinalResult();

    }


    // Checking result
    public void CheckFinalResult() {
        // Checking whether email is already exists or not.
        if (F_Result.equalsIgnoreCase("Email Found")) {
            // If email is exists then toast msg will display.
            Toast.makeText(Registration.this, "Email Already Exists", Toast.LENGTH_LONG).show();
        } else {
            // If email already dose n't exists then user registration details will entered to SQLite database.
            InsertDataIntoSQLiteDatabase();
        }
        F_Result = "Not_Found";
    }

}