package com.unitelma.gianicolo1849;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GuideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
    }

    public static Intent getIntentInstance(Context context)
    {
        return new Intent(context, GuideActivity.class);
    }

    public void selectGuideClickGuideActivity(View view) {
    }
}