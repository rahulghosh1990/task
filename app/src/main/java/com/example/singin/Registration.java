package com.example.singin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class Registration extends AppCompatActivity {

    TextInputEditText Email, Password, firstName, lastName, phoneNumber, dateOfBirth, confirmPassword;
    TextView Register, tv_male, tv_female;
    TextView log_in;
    String first_name, last_name, dob, phone, email, pW, cPW;
    Boolean EditTextEmptyHolder;
    SQLiteDatabase sqLiteDatabaseObj;
    String SQLiteDataBaseQueryHolder;
    SqliteHelperClass sqLiteHelper;
    Cursor cursor;
    String F_Result = "Not_Found";

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        //Email, Password, firstName, lastName, phoneNumber, dateOfBirth, confirmPassword


        tv_male.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View v) {
                tv_male.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tv_onclick_background));
                tv_female.setBackground(getResources().getDrawable(R.drawable.tv_border));
            }
        });

        tv_female.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View v) {
                tv_female.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tv_onclick_background));
                tv_male.setBackground(getResources().getDrawable(R.drawable.tv_border));


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
        // Adding click listener to register button.

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Creating SQLite database if dose n't exists
                SQLiteDataBaseBuild();

                // Creating SQLite table if dose n't exists.
                SQLiteTableBuild();

                // Checking EditText is empty or Not.
                CheckEditTextStatus();

                // Method to check Email is already exists or not.
                CheckingEmailAlreadyExistsOrNot();

                // Empty EditText After done inserting process.
                EmptyEditTextAfterDataInsert();


            }
        });

    }


    // SQLite database build method.
    public void SQLiteDataBaseBuild() {

        sqLiteDatabaseObj = openOrCreateDatabase(SqliteHelperClass.DATABASE_NAME, Context.MODE_PRIVATE, null);

    }

    // SQLite table build method.
    public void SQLiteTableBuild() {

        sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS " + SqliteHelperClass.TABLE_NAME + "(" + SqliteHelperClass.Table_Column_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SqliteHelperClass.Table_Column_1_fName + " VARCHAR, " + SqliteHelperClass.Table_Column_2_lName + " VARCHAR, " + SqliteHelperClass.Table_Column_3_Dob + " VARCHAR, " + SqliteHelperClass.Table_Column_4_email + " VARCHAR, " + SqliteHelperClass.Table_Column_5_phone + " VARCHAR, " + SqliteHelperClass.Table_Column_6_PW + " VARCHAR, " + SqliteHelperClass.Table_Column_7_CPW + " VARCHAR);");

    }

    // Insert data into SQLite database method.
    @SuppressLint("UseCompatLoadingForDrawables")
    public void InsertDataIntoSQLiteDatabase() {

        // If editText is not empty then this block will executed.
        if (EditTextEmptyHolder == true) {

            // SQLite query to insert data into table.
            SQLiteDataBaseQueryHolder = "INSERT INTO " + SqliteHelperClass.TABLE_NAME + " (firstname,lastname,dateofbirth,emailaddress,phonenumber,password,confirmpassword) VALUES('" + first_name + "', '" + last_name + "', '" + dob + "', '" + email + "', '" + phone + "',  '" + pW + "', '" + cPW + "');";

            // Executing query.
            sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);

            // Closing SQLite database object.
            sqLiteDatabaseObj.close();

            // Printing toast message after done inserting.
            Toast.makeText(Registration.this, "User Registered Successfully", Toast.LENGTH_LONG).show();
            Register.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.confirm_tv_border));
            tv_female.setBackground(getResources().getDrawable(R.drawable.tv_border));
            tv_male.setBackground(getResources().getDrawable(R.drawable.tv_border));





        }
        // This block will execute if any of the registration EditText is empty.
        else {

            // Printing toast message if any of EditText is empty.
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
        first_name = firstName.getText().toString();
        last_name = lastName.getText().toString();
        phone = phoneNumber.getText().toString();
        email = Email.getText().toString();
        pW = Password.getText().toString();
        cPW = confirmPassword.getText().toString();
        dob = dateOfBirth.getText().toString();


        if (TextUtils.isEmpty(first_name) || TextUtils.isEmpty(last_name) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(email) || TextUtils.isEmpty(email)
                || TextUtils.isEmpty(pW) || TextUtils.isEmpty(cPW) || TextUtils.isEmpty(dob)) {

            EditTextEmptyHolder = false;

        } else {

            EditTextEmptyHolder = true;

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