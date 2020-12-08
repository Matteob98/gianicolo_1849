package com.unitelma.gianicolo1849;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

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

        mBottomNav.setOnNavigationItemSelectedListener(this);
        mBottomNav.getMenu().getItem(2).setChecked(true);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.frameLayoutItineraryActivity, new ItineraryFragment()).commit();
        }

        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomNav.getMenu().getItem(2).setChecked(true);
                mFAB.setColorFilter(R.color.colorPrimary);
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.frameLayoutItineraryActivity, new ItineraryFragment()).commit();
            }
        });


    }

    /**
     * Import layout items from xml
     */
    private void layoutSettings() {
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
                mFAB.setColorFilter(null);
                break;
            default:
                return false;
        }
        getSupportFragmentManager().beginTransaction().
                replace(R.id.frameLayoutItineraryActivity, selectedFragment).commit();
        return true;
    }
}