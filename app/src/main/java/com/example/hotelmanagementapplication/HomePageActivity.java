package com.example.hotelmanagementapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomePageActivity extends AppCompatActivity implements View.OnClickListener {
    private Typeface typeface;
    private TextView hometile,homesubtitle;
    private CardView addNewGuest,updateGuest , deleteGuest , allLivingGuest , guestPayment ,guestPaymentList,updatePayment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        typeface = Typeface.createFromAsset(getAssets(),"Font/colus_regular.ttf");

        hometile = findViewById(R.id.homeTitleID);
        homesubtitle = findViewById(R.id.homeSubTitleID);

        hometile.setTypeface(typeface);
        homesubtitle.setTypeface(typeface);

        addNewGuest = findViewById(R.id.addGuestId);
        updateGuest = findViewById(R.id.updateGuestId);
        deleteGuest = findViewById(R.id.deleteGuestId);
        allLivingGuest = findViewById(R.id.livingGuestId);
        guestPayment = findViewById(R.id.paymentGuestId);
        guestPaymentList = findViewById(R.id.paymentListGuestId);
        updatePayment = findViewById(R.id.updatePaymentId);

        addNewGuest.setOnClickListener(this);
        updateGuest.setOnClickListener(this);
        deleteGuest.setOnClickListener(this);
        allLivingGuest.setOnClickListener(this);
        guestPayment.setOnClickListener(this);
        guestPaymentList.setOnClickListener(this);
        updatePayment.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        Intent intent;

        if(v.getId()==R.id.addGuestId)
        {
            intent = new Intent(HomePageActivity.this,AddNewGuest.class);
            startActivity(intent);

        }


    }
}
