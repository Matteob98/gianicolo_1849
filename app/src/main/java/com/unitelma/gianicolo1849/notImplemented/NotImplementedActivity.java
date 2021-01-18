package com.unitelma.gianicolo1849.notImplemented;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.unitelma.gianicolo1849.R;

public class NotImplementedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_implemented);
    }

    public static Intent getIntentInstance(Context context)
    {
        return new Intent(context, NotImplementedActivity.class);
    }
}