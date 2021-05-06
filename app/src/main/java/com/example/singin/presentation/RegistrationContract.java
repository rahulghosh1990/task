package com.example.singin.presentation;

import com.google.android.material.textfield.TextInputEditText;

public interface RegistrationContract {
    void registrat();


    void getediitextData(TextInputEditText Email, TextInputEditText Password, TextInputEditText firstName, TextInputEditText lastName, TextInputEditText phoneNumber, TextInputEditText dateOfBirth, TextInputEditText confirmPassword);

    void emptyEdittextAfterInsertion(TextInputEditText Email, TextInputEditText Password, TextInputEditText firstName, TextInputEditText lastName, TextInputEditText phoneNumber, TextInputEditText dateOfBirth, TextInputEditText confirmPassword);

    void CheckingEmailAlreadyExistsOrNot(String email);

    interface View {
        void getpassword(String ps);
        //firstname_,lastname_,do_b,e_mail,p_W,c_PW,p_hone
        void setEdditTextData(String firstname_, String lastname_, String do_b, String e_mail, String p_W, String p_hone, String c_PW);

    }
}
