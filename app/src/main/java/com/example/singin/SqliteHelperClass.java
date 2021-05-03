package com.example.singin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteHelperClass extends SQLiteOpenHelper {

    static String DATABASE_NAME="UserDataBase";

    public static final String TABLE_NAME="UserTable";

    public static final String Table_Column_ID="id";

    public static final String Table_Column_1_fName="firstname";
    public static final String Table_Column_2_lName="lastname";
    public static final String Table_Column_3_Dob="dateofbirth";
    public static final String Table_Column_4_email="emailaddress";
    public static final String Table_Column_5_phone="phonenumber";
    public static final String Table_Column_6_PW="password";
    public static final String Table_Column_7_CPW="confirmpassword";
    public static final String Table_Column_8_GNDR="gender";


    public SqliteHelperClass(Context context) {

        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        String CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+Table_Column_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+Table_Column_1_fName+" VARCHAR, "+Table_Column_2_lName+" VARCHAR, "+Table_Column_3_Dob+" VARCHAR, "+Table_Column_4_email+" VARCHAR, "+Table_Column_5_phone+" VARCHAR, "+Table_Column_6_PW+" VARCHAR, "+Table_Column_7_CPW+" VARCHAR, "+Table_Column_8_GNDR+" VARCHAR)";
        database.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
        if (newVersion > oldVersion) {
            db.execSQL("ALTER TABLE TABLE_NAME ADD COLUMN gender INTEGER DEFAULT 0");
        }
    }

}