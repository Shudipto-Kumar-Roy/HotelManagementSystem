package com.example.hotelmanagementapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddNewGuest extends AppCompatActivity {
    private Typeface typeface;
    private TextView roomIDText,nameText,fatherNameText,motherNameText,emailText,addressText,phoneText;
    private EditText roomId,name,father_name,mother_name,email,address,phone;
    private Button addButton;

    DatabaseSQLite databaseSQLite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_guest);

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

        roomId = findViewById(R.id.UserRoomId);
        name = findViewById(R.id.NameId);
        father_name = findViewById(R.id.FatherNameId);
        mother_name = findViewById(R.id.MotherNameId);
        email = findViewById(R.id.EmailId);
        address = findViewById(R.id.AddressId);
        phone = findViewById(R.id.PhoneId);

        addButton  =findViewById(R.id.addButtonId);

        roomIDText.setTypeface(typeface);
        nameText.setTypeface(typeface);
        fatherNameText.setTypeface(typeface);
        motherNameText.setTypeface(typeface);
        emailText.setTypeface(typeface);
        addressText.setTypeface(typeface);
        phoneText.setTypeface(typeface);
        addButton.setTypeface(typeface);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                else
                {
                   long rowId = databaseSQLite.insertData1(room_id1,name1,father_name1,mother_name1,email1,address1,phone1);
                   if(rowId==-1)
                   {
                       Toast.makeText(getApplicationContext(),"Failed!!",Toast.LENGTH_LONG).show();
                   }
                   else
                   {
                       Toast.makeText(getApplicationContext(),"Information added Successfully!!",Toast.LENGTH_LONG).show();
                   }

                }

            }
        });

    }
}
