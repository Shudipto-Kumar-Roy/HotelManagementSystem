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

public class GuestPayment extends AppCompatActivity implements View.OnClickListener {

    private Typeface typeface;
    private TextView roomIDText,nameText,fatherNameText,motherNameText,emailText,addressText,phoneText,paymentTitleText,paymentText;
    private EditText roomId,name,father_name,mother_name,email,address,phone,payment;
    private Button paymentButton,searchButton;

    DatabaseSQLite databaseSQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_payment);

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
        paymentText = findViewById(R.id.PaymentTextId);
        paymentTitleText = findViewById(R.id.PaymentTitleTextID);

        roomId = findViewById(R.id.UserRoomId);
        name = findViewById(R.id.NameId);
        father_name = findViewById(R.id.FatherNameId);
        mother_name = findViewById(R.id.MotherNameId);
        email = findViewById(R.id.EmailId);
        address = findViewById(R.id.AddressId);
        phone = findViewById(R.id.PhoneId);
        payment = findViewById(R.id.PayementId);

        searchButton = findViewById(R.id.searchButtonId);
        paymentButton  =findViewById(R.id.paymentButtonId);

        roomIDText.setTypeface(typeface);
        nameText.setTypeface(typeface);
        fatherNameText.setTypeface(typeface);
        motherNameText.setTypeface(typeface);
        emailText.setTypeface(typeface);
        addressText.setTypeface(typeface);
        phoneText.setTypeface(typeface);
        paymentText.setTypeface(typeface);
        paymentTitleText.setTypeface(typeface);

        searchButton.setOnClickListener(this);
        paymentButton.setOnClickListener(this);

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
        if(v.getId()==R.id.paymentButtonId)
        {
            String room_id1 = roomId.getText().toString();
            String name1 = name.getText().toString();
            String father_name1 = father_name.getText().toString();
            String mother_name1 = mother_name.getText().toString();
            String email1 = email.getText().toString();
            String address1 = address.getText().toString();
            String phone1 = phone.getText().toString();
            String amount1 = payment.getText().toString();

            if(room_id1.equals("") || name1.equals("") || father_name1.equals("") || mother_name1.equals("") || email1.equals("") || address1.equals("") || phone1.equals("") || amount1.equals(""))
            {
                Toast.makeText(getApplicationContext(),"Please fill up and try again..",Toast.LENGTH_SHORT).show();

            }
            else
            {
                long rowId = databaseSQLite.insertData2(room_id1,name1,father_name1,mother_name1,email1,address1,phone1,amount1);
                if(rowId==-1)
                {
                    Toast.makeText(getApplicationContext(),"Failed!!",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Payment added Successfully!!",Toast.LENGTH_LONG).show();
                }

            }
        }
    }
}
