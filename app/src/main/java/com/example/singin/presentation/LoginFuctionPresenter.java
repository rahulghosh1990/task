package com.example.singin.presentation;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.singin.SqliteHelperClass;
import com.example.singin.model.DemoModel;

import java.util.ArrayList;

public class LoginFuctionPresenter implements LoginPresenter{
    SQLiteDatabase sqLiteDatabaseObj;
    SqliteHelperClass sqLiteHelper;
    Cursor cursor;
    String pw;
   LoginPresenter.View view;
    public  LoginFuctionPresenter( SqliteHelperClass sqLiteHelper, View view ){
        this.sqLiteHelper=sqLiteHelper;
        this.view=view;
    }
    @Override
    public void handleLogin( String EmailHolder) {
            sqLiteDatabaseObj = sqLiteHelper.getWritableDatabase();
            // Adding search email query to cursor.
            cursor = sqLiteDatabaseObj.query(SqliteHelperClass.TABLE_NAME, null, " " + SqliteHelperClass.Table_Column_4_email + "=?", new String[]{EmailHolder}, null, null, null);
            while (cursor.moveToNext()) {
                if (cursor.isFirst()) {
                    cursor.moveToFirst();
                    // Storing Password associated with entered email.
                    pw = cursor.getString(cursor.getColumnIndex(SqliteHelperClass.Table_Column_6_PW));
                    // Closing cursor.
                    cursor.close();
                    view.getpassword(pw);
            }
        }
    }

}
