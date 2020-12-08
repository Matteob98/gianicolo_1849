package com.unitelma.gianicolo1849;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class VisitStageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_stage);
    }

    public static Intent getIntentInstance(Context context)
    {
        return new Intent(context, VisitStageActivity.class);
    }
}