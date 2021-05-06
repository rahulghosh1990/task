package com.example.singin.presentation;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.singin.Registration;
import com.example.singin.SqliteHelperClass;
import com.google.android.material.textfield.TextInputEditText;

public class RegistrationPresenter implements  RegistrationContract {
    //TextInputEditText Email, Password, firstName, lastName, phoneNumber, dateOfBirth, confirmPassword;
    RegistrationContract.View view;
    SQLiteDatabase sqLiteDatabaseObj;
    SqliteHelperClass sqLiteHelper;
    Cursor cursor;
    String F_Result = "Not_Found";
    String firstname_, lastname_, do_b, p_hone, e_mail, p_W, c_PW;
    public RegistrationPresenter(SqliteHelperClass sqLiteHelper, RegistrationContract.View view) {
        this.sqLiteHelper=sqLiteHelper;
        this.view = view;

    }
    @Override
    public void registrat() {

    }

    @Override
    public void getediitextData(TextInputEditText Email, TextInputEditText Password, TextInputEditText firstName, TextInputEditText lastName, TextInputEditText phoneNumber, TextInputEditText dateOfBirth, TextInputEditText confirmPassword) {
        firstname_ = firstName.getText().toString().trim();
        lastname_ = lastName.getText().toString().trim();
        do_b = dateOfBirth.getText().toString().trim();
        e_mail = Email.getText().toString().trim();
        p_W = Password.getText().toString().trim();
        c_PW = confirmPassword.getText().toString().trim();
        p_hone = phoneNumber.getText().toString().trim();
        view.setEdditTextData(firstname_,lastname_,do_b,e_mail,p_W,c_PW,p_hone);
    }


    @Override
    public void emptyEdittextAfterInsertion(TextInputEditText Email,TextInputEditText Password,TextInputEditText firstName,TextInputEditText lastName,TextInputEditText phoneNumber,TextInputEditText dateOfBirth,TextInputEditText confirmPassword) {
        firstName.getText().clear();
        lastName.getText().clear();
        dateOfBirth.getText().clear();
        phoneNumber.getText().clear();
        confirmPassword.getText().clear();
        Email.getText().clear();
        Password.getText().clear();
    }



    @Override
    public void CheckingEmailAlreadyExistsOrNot(String email) {
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
                view.getpassword(F_Result);
            }
        }

    }
}
