package com.example.hotelmanagementapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateGuest extends AppCompatActivity implements View.OnClickListener {

    private Typeface typeface;
    private TextView roomIDText,nameText,fatherNameText,motherNameText,emailText,addressText,phoneText,updateTitleText;
    private EditText roomId,name,father_name,mother_name,email,address,phone;
    private Button updateButton,searchButton;

    DatabaseSQLite databaseSQLite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_guest);

        typeface = Typeface.createFromAsset(getAssets(),"Font/colus_regular.ttf");
        databaseSQLite = new DatabaseSQLite(this);
        SQLiteDatabase sqLiteDatabase = databaseSQLite.getWritableDatabase();

        roomIDText = findViewById(R.id.RoomNoTextId);
        nameText = findViewById(R.id.NameTextId);
        fatherNameText = findViewById(R.id.FatherTextId);
        motherNameText = findViewById(R.id.MotherNameTextId);
        emailText = findViewById(R.id.EmailTextId);
        addressText = findViewById(R.id.AddressTextId);
        phoneText = findViewById(R.id.PhoneTextId);
        updateTitleText = findViewById(R.id.UpdateTitleID);

        roomId = findViewById(R.id.UserRoomId);
        name = findViewById(R.id.NameId);
        father_name = findViewById(R.id.FatherNameId);
        mother_name = findViewById(R.id.MotherNameId);
        email = findViewById(R.id.EmailId);
        address = findViewById(R.id.AddressId);
        phone = findViewById(R.id.PhoneId);

        searchButton = findViewById(R.id.searchButtonId);
        updateButton  =findViewById(R.id.updateButtonId);

        roomIDText.setTypeface(typeface);
        nameText.setTypeface(typeface);
        fatherNameText.setTypeface(typeface);
        motherNameText.setTypeface(typeface);
        emailText.setTypeface(typeface);
        addressText.setTypeface(typeface);
        phoneText.setTypeface(typeface);
        updateButton.setTypeface(typeface);
        updateTitleText.setTypeface(typeface);

        searchButton.setOnClickListener(this);
        updateButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

    }
}
