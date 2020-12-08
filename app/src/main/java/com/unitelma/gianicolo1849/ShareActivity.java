package com.unitelma.gianicolo1849;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class ShareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
    }

    public static Intent getIntentInstance(Context context)
    {
        Intent intent = new Intent(context, ShareActivity.class);
        return intent;
    }
}