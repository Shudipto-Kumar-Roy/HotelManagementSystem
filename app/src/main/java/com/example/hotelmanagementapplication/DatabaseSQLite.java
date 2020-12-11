package com.example.hotelmanagementapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import static android.provider.Contacts.SettingsColumns.KEY;
import static java.sql.Types.INTEGER;

public class DatabaseSQLite extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "hotel.db";
    private static final String TABLE_NAME1 = "login_details";
    private static final String TABLE_NAME2 = "hotel_details";
    private static final String TABLE_NAME3 = "hotel_payment_details";
    private static final String ID  = "Id";
    private static final String NAME  = "Name";
    private static final String PASSWORD  = "Password";
    private static final String EMAIL  = "Email";
    private static final int VERSION_NO  = 3;


    private static final String ROOM_ID  = "RoomId";
    private static final String GUEST_NAME  = "GuestName";
    private static final String GUEST_FATHER_NAME  = "GuestFatherName";
    private static final String GUEST_MOTHER_NAME  = "GuestMotherName";
    private static final String GUEST_EMAIL  = "GuestEmail";
    private static final String GUEST_ADDRESS = "GuestAddress";
    private static final String GUEST_PHONE = "GuestPhone";

    private static final String GUEST_AMOUNT = "GuestAmount";



    private Context context;
    public DatabaseSQLite(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_NO);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      try{

          Toast.makeText(context,"OnCreate is Called",Toast.LENGTH_SHORT).show();
          db.execSQL("CREATE TABLE "+TABLE_NAME1+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+NAME+" VARCHAR(20),"+PASSWORD+" VARCHAR(20), "+EMAIL+" VARCHAR(50))");
          db.execSQL("CREATE TABLE "+TABLE_NAME2+"("+ROOM_ID+" INTEGER PRIMARY KEY,"+GUEST_NAME+" VARCHAR(30),"+GUEST_FATHER_NAME+" VARCHAR(30),"+GUEST_MOTHER_NAME+" VARCHAR(30),"+GUEST_EMAIL+" VARCHAR(50),"+GUEST_ADDRESS+" VARCHAR(100),"+GUEST_PHONE+" VARCHAR(20))");
          db.execSQL("CREATE TABLE "+TABLE_NAME3+"("+ROOM_ID+" INTEGER PRIMARY KEY,"+GUEST_NAME+" VARCHAR(30),"+GUEST_FATHER_NAME+" VARCHAR(30),"+GUEST_MOTHER_NAME+" VARCHAR(30),"+GUEST_EMAIL+" VARCHAR(50),"+GUEST_ADDRESS+" VARCHAR(100),"+GUEST_PHONE+" VARCHAR(20),"+GUEST_AMOUNT+" VARCHAR(50))");


      }catch (Exception  e)
      {
          Toast.makeText(context,"Exception : "+e,Toast.LENGTH_SHORT).show();

      }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {
            Toast.makeText(context,"OnUpgrade is Called",Toast.LENGTH_SHORT).show();

            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME2);
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME3);
            onCreate(db);

        }catch (Exception e)
        {
            Toast.makeText(context,"Exception : "+e,Toast.LENGTH_SHORT).show();

        }
    }

//    For login Section

public long insertData(String name,String password,String email)
{
    SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put(NAME,name);
    contentValues.put(PASSWORD,password);
    contentValues.put(EMAIL,email);
    return  sqLiteDatabase.insert(TABLE_NAME1,null,contentValues);
}
public Cursor showData()
{
    SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME1,null);
   return  cursor;
}

//For hotel guest details section


public long insertData1(String room_Id,String name,String father_name,String mother_name,String email,String address,String phone)
{
    SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put(ROOM_ID,room_Id);
    contentValues.put(GUEST_NAME,name);
    contentValues.put(GUEST_FATHER_NAME,father_name);
    contentValues.put(GUEST_MOTHER_NAME,mother_name);
    contentValues.put(GUEST_EMAIL,email);
    contentValues.put(GUEST_ADDRESS,address);
    contentValues.put(GUEST_PHONE,phone);
    return sqLiteDatabase.insert(TABLE_NAME2,null,contentValues);

}

    public Cursor searchData()
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME2,null);
        return cursor;

    }


    public boolean update1(String room_Id,String name,String father_name,String mother_name,String email,String address,String phone)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ROOM_ID,room_Id);
        contentValues.put(GUEST_NAME,name);
        contentValues.put(GUEST_FATHER_NAME,father_name);
        contentValues.put(GUEST_MOTHER_NAME,mother_name);
        contentValues.put(GUEST_EMAIL,email);
        contentValues.put(GUEST_ADDRESS,address);
        contentValues.put(GUEST_PHONE,phone);

        sqLiteDatabase.update(TABLE_NAME2,contentValues,ROOM_ID+"= ?",new String[]{room_Id});
        return true;


    }

    public Cursor showAllGuest()
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME2,null);
        return cursor;
    }
    public int deleteGuest(String room_no){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME2,ROOM_ID+"= ?",new String[]{room_no});
    }


//    For Hotel Guest Pament
public long insertData2(String room_Id,String name,String father_name,String mother_name,String email,String address,String phone,String amount)
{
    SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put(ROOM_ID,room_Id);
    contentValues.put(GUEST_NAME,name);
    contentValues.put(GUEST_FATHER_NAME,father_name);
    contentValues.put(GUEST_MOTHER_NAME,mother_name);
    contentValues.put(GUEST_EMAIL,email);
    contentValues.put(GUEST_ADDRESS,address);
    contentValues.put(GUEST_PHONE,phone);
    contentValues.put(GUEST_AMOUNT,amount);
    return sqLiteDatabase.insert(TABLE_NAME3,null,contentValues);

}

    public Cursor showGuestPaymentList()
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME3,null);
        return cursor;
    }

    public Cursor searchData1()
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME3,null);
        return cursor;

    }

    public boolean update2(String room_Id,String name,String email,String phone,String payment)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ROOM_ID,room_Id);
        contentValues.put(GUEST_NAME,name);
        contentValues.put(GUEST_EMAIL,email);
        contentValues.put(GUEST_PHONE,phone);
        contentValues.put(GUEST_AMOUNT,payment);

        sqLiteDatabase.update(TABLE_NAME3,contentValues,ROOM_ID+"= ?",new String[]{room_Id});
        return true;


    }
}
