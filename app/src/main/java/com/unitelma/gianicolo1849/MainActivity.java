package com.unitelma.gianicolo1849;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private Toolbar mToolbar;
    private FloatingActionButton mNotSelectedFAB, mSelectedFAB;
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

        mNotSelectedFAB.setVisibility(FloatingActionButton.INVISIBLE);
        mSelectedFAB.setVisibility(FloatingActionButton.VISIBLE);

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
        mNotSelectedFAB = findViewById(R.id.notSelectedFloatingButtonItineraryActivity);
        mSelectedFAB = findViewById(R.id.selectedFloatingButtonItineraryActivity);
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
                mNotSelectedFAB.setVisibility(FloatingActionButton.VISIBLE);
                mSelectedFAB.setVisibility(FloatingActionButton.INVISIBLE);
                break;
            case R.id.itinerary:
                itineraryClick();
                break;
            default:
                return false;
        }
        getSupportFragmentManager().beginTransaction().
                replace(R.id.frameLayoutItineraryActivity, selectedFragment).commit();
        return true;
    }

    public void notSelectedFABNavBarClick(View view) {
        itineraryClick();
    }

    public void itineraryClick() {
        mBottomNav.getMenu().getItem(2).setChecked(true);
        mNotSelectedFAB.setVisibility(FloatingActionButton.INVISIBLE);
        mSelectedFAB.setVisibility(FloatingActionButton.VISIBLE);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = NotImplementedActivity.getIntentInstance(this);
            startActivity(intent);
        }
        return true;
    }
}