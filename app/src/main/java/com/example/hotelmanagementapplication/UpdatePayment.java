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

public class UpdatePayment extends AppCompatActivity implements View.OnClickListener {

    private Typeface typeface;
    private TextView roomIDText,nameText,emailText,phoneText,updatePaymentTitleText;
    private EditText roomId,name,email,phone,payment;
    private Button updatePaymentButton,searchButton;

    DatabaseSQLite databaseSQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_payment);

        typeface = Typeface.createFromAsset(getAssets(),"Font/colus_regular.ttf");
        databaseSQLite = new DatabaseSQLite(this);
        SQLiteDatabase sqLiteDatabase = databaseSQLite.getWritableDatabase();

        roomIDText = findViewById(R.id.RoomNoTextId);
        nameText = findViewById(R.id.NameTextId);
        emailText = findViewById(R.id.EmailTextId);
        phoneText = findViewById(R.id.PhoneTextId);
        updatePaymentTitleText = findViewById(R.id.UpdatePaymentTitleID);

        roomId = findViewById(R.id.UserRoomId);
        name = findViewById(R.id.NameId);
        email = findViewById(R.id.EmailId);
        phone = findViewById(R.id.PhoneId);
        payment = findViewById(R.id.PaymentId2);

        searchButton = findViewById(R.id.searchButtonId2);
        updatePaymentButton  =findViewById(R.id.updatePaymentButtonId);

        roomIDText.setTypeface(typeface);
        nameText.setTypeface(typeface);
        emailText.setTypeface(typeface);
        phoneText.setTypeface(typeface);
        updatePaymentButton.setTypeface(typeface);
        updatePaymentTitleText.setTypeface(typeface);

        searchButton.setOnClickListener(this);
        updatePaymentButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.searchButtonId2)
        {
            Cursor searchValue = databaseSQLite.searchData1();
            if(searchValue.getCount()==0)
            {

                Toast.makeText(getApplicationContext(),"No Guest booked in this room no",Toast.LENGTH_SHORT).show();


            }
            else {
                String roomId1 = roomId.getText().toString();
                while (searchValue.moveToNext())
                {
                    if(searchValue.getString(0).equals(roomId1))
                    {
                        name.setText(searchValue.getString(1));
                        email.setText(searchValue.getString(4));
                        phone.setText(searchValue.getString(6));
                        payment.setText(searchValue.getString(7));
                    }
                    else
                    {

                        Toast.makeText(getApplicationContext(),"No match found",Toast.LENGTH_SHORT).show();

                    }

                }
            }

        }
        else if(v.getId()==R.id.updatePaymentButtonId)
        {
            String room_id1 = roomId.getText().toString();
            String name1 = name.getText().toString();
            String email1 = email.getText().toString();
            String phone1 = phone.getText().toString();
            String payment1 = payment.getText().toString();

            if(room_id1.equals("") || name1.equals("") || email1.equals("") || phone1.equals("") || payment1.equals(""))
            {
                Toast.makeText(getApplicationContext(),"Please fill up and try again..",Toast.LENGTH_SHORT).show();

            }

            boolean isUpdated = databaseSQLite.update2(room_id1,name1,email1,phone1,payment1);
            if(isUpdated==true)
            {
                Toast.makeText(getApplicationContext(), "Guest Payemnt updated successfully!!", Toast.LENGTH_LONG).show();

            }
            else
            {
                Toast.makeText(getApplicationContext(), "Failed!!", Toast.LENGTH_LONG).show();

            }
        }
    }
}
