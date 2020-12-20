package com.example.demoprojecthotelmanagement;

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

import com.example.hotelmanagementapplication.DatabaseSQLite;
import com.example.hotelmanagementapplication.R;

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
        if(v.getId()==R.id.searchButtonId)
        {
            Cursor searchValue = databaseSQLite.searchData();
            if(searchValue.getCount()==0)
            {

                Toast.makeText(getApplicationContext(),"No Guest booked in this room no",Toast.LENGTH_SHORT).show();


            }
            else {
                String roomId1 = roomId.getText().toString();
                while(searchValue.moveToNext())
                {
                    if(searchValue.getString(0).equals(roomId1))
                    {
                        name.setText(searchValue.getString(1));
                        father_name.setText(searchValue.getString(2));
                        mother_name.setText(searchValue.getString(3));
                        email.setText(searchValue.getString(4));
                        address.setText(searchValue.getString(5));
                        phone.setText(searchValue.getString(6));
                    }
                    else
                    {

                        Toast.makeText(getApplicationContext(),"No matche found",Toast.LENGTH_SHORT).show();

                    }

                }
            }

        }

        else if(v.getId()==R.id.updateButtonId)
        {

            String room_id1 = roomId.getText().toString();
            String name1 = name.getText().toString();
            String father_name1 = father_name.getText().toString();
            String mother_name1 = mother_name.getText().toString();
            String email1 = email.getText().toString();
            String address1 = address.getText().toString();
            String phone1 = phone.getText().toString();

            if(room_id1.equals("") || name1.equals("") || father_name1.equals("") || mother_name1.equals("") || email1.equals("") || address1.equals("") || phone1.equals(""))
            {
                Toast.makeText(getApplicationContext(),"Please fill up and try again..",Toast.LENGTH_SHORT).show();

            }

            boolean isUpdated = databaseSQLite.update1(room_id1,name1,father_name1,mother_name1,email1,address1,phone1);
            if(isUpdated==true)
            {
                Toast.makeText(getApplicationContext(), "Guest info. updated successfully!!", Toast.LENGTH_LONG).show();

            }
            else
            {
                Toast.makeText(getApplicationContext(), "Failed!!", Toast.LENGTH_LONG).show();

            }
        }

    }
}
