package com.unitelma.gianicolo1849;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private Toolbar mToolbar;
    private FloatingActionButton mFAB;
    private BottomNavigationView mBottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutSettings();

        navBarInitialize(savedInstanceState);

        toolbarInitialize();
    }

    private void toolbarInitialize() {
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void navBarInitialize(Bundle savedInstanceState) {
        mBottomNav.setOnNavigationItemSelectedListener(this);
        mBottomNav.getMenu().getItem(2).setChecked(true);
        mFAB.setImageDrawable(getResources().getDrawable(R.drawable.ic_itinerary_green));

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.frameLayoutItineraryActivity, new ItineraryFragment()).commit();
        }
    }

    /**
     * Import layout items from xml
     */
    private void layoutSettings() {

        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));

        mToolbar = findViewById(R.id.toolbarItineraryActivity);
        mFAB = findViewById(R.id.floatingButtonItineraryActivity);
        mBottomNav = findViewById(R.id.bottomNavItineraryActivity);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Fragment selectedFragment = null;

        switch (id){ //Fragment not implemented yet
            case R.id.toilet:
            case R.id.bus:
            case R.id.parking:
            case R.id.restaurant:
                selectedFragment = new NotImplementedFragment();
                mFAB.setImageDrawable(getResources().getDrawable(R.drawable.ic_itinerary_light));
                break;
            default:
                return false;
        }
        getSupportFragmentManager().beginTransaction().
                replace(R.id.frameLayoutItineraryActivity, selectedFragment).commit();
        return true;
    }

    public void fabNavBarClick(View view) {
        mBottomNav.getMenu().getItem(2).setChecked(true);
        mFAB.setImageResource(R.drawable.ic_itinerary_green);
        getSupportFragmentManager().beginTransaction().
                replace(R.id.frameLayoutItineraryActivity, new ItineraryFragment()).commit();
    }


    public void stageVisitCardViewClick(View view) {
        Intent intent = VisitStageActivity.getIntentInstance(this);
        startActivity(intent);
    }

    public void shareCardViewClick(View view) {
        Intent intent = ShareActivity.getIntentInstance(this);
        startActivity(intent);
    }

    public void guideCardViewClick(View view) {
        Intent intent = GuideActivity.getIntentInstance(this);
        startActivity(intent);
    }
}