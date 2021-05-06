package com.example.singin.presentation;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.singin.view.SqliteHelperClass;
import com.google.android.material.textfield.TextInputEditText;

public class RegistrationPresenter implements  RegistrationContract {
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
    public void getediitextData(TextInputEditText Email, TextInputEditText Password, TextInputEditText firstName, TextInputEditText lastName, TextInputEditText phoneNumber, TextInputEditText dateOfBirth, TextInputEditText confirmPassword) {
        firstname_ = firstName.getText().toString().trim();
        lastname_ = lastName.getText().toString().trim();
        do_b = dateOfBirth.getText().toString().trim();
        e_mail = Email.getText().toString().trim();
        p_W = Password.getText().toString().trim();
        c_PW = confirmPassword.getText().toString().trim();
        p_hone = phoneNumber.getText().toString().trim();
        //view.setEdditTextData(firstname_,lastname_,do_b,e_mail,p_W,c_PW,p_hone);
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
        sqLiteDatabaseObj = sqLiteHelper.getWritableDatabase();
        cursor = sqLiteDatabaseObj.query(SqliteHelperClass.TABLE_NAME, null, " " + SqliteHelperClass.Table_Column_4_email + "=?", new String[]{email}, null, null, null);
        while (cursor.moveToNext()) {
            if (cursor.isFirst()) {
                cursor.moveToFirst();
                F_Result = "Email Found";
                cursor.close();
                view.getpassword(F_Result);
            }
        }
    }
}
