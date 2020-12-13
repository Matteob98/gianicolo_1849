package com.unitelma.gianicolo1849;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ReservationMadeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_made);
    }

    public void returnToItineraryClick(View view) {
        finish();
    }

    public static Intent getIntentInstance(Context context)
    {
        return new Intent(context, ReservationMadeActivity.class);
    }
}