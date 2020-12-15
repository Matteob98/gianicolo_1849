package com.unitelma.gianicolo1849;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.jgabrielfreitas.core.BlurImageView;

public class GuideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        toolbarSettings();
    }

    public static Intent getIntentInstance(Context context)
    {
        return new Intent(context, GuideActivity.class);
    }

    private void toolbarSettings() {
        Toolbar mToolbar = findViewById(R.id.toolbarGuideActivity);
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Contatta una guida");
    }

    public void selectGuideClickGuideActivity(View view) {
        Intent intent = ReserveGuideActivity.getIntentInstance(this);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}