package com.unitelma.gianicolo1849;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.unitelma.gianicolo1849.reservation_fragment.CalendarFragment;
import com.unitelma.gianicolo1849.reservation_fragment.DataInsertFragment;
import com.unitelma.gianicolo1849.reservation_fragment.PeopleNumberFragment;
import com.unitelma.gianicolo1849.reservation_fragment.ResumeReservationFragment;
import com.unitelma.gianicolo1849.reservation_fragment.TimeFragment;
import com.unitelma.gianicolo1849.utilities.BlurBuilder;

public class ReserveGuideActivity extends AppCompatActivity {

    private final int fragmentId = R.id.frameLayoutReserveGuideActivity;
    private LinearLayout mImageLinearLayout;
    private ImageButton mDataIB, mCalendarIB, mTimeIB, mPeopleIB, mResumeIB;
    private AlertDialog alertDialog;
    private AlertDialog.Builder alertDialogBuilder;
    private Toolbar mToolbar;

    public enum ReservationFragment {
        DATA_INSERT,
        CALENDAR,
        TIME,
        PEOPLE,
        RESUME
    }
    private ReservationFragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_guide);

        layoutSettings();

        toolbarSettings();

        initializeCurrentFragment();

        initializeExitAlertDialog();
    }

    private void initializeExitAlertDialog() {
        alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Vuoi uscire?");
        alertDialogBuilder.setMessage("Se confermi perderai tutti i dati inseriti nella prenotazione");

        alertDialogBuilder.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //do null
                dialog.dismiss();
            }
        });

        alertDialogBuilder.setNegativeButton("Esci", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });

        alertDialog = alertDialogBuilder.create();
    }

    private void toolbarSettings() {
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Itinerario Garibaldino");
    }

    private void initializeCurrentFragment() {
        currentFragment = ReservationFragment.DATA_INSERT;
        setImageButtonsColorByCurrentFragment(currentFragment);
        fragmentTransaction(currentFragment);
    }

    /**
     * Import xml items and initialize items
     */
    private void layoutSettings() {
        mToolbar = findViewById(R.id.toolbarReserveGuideActivity);
        mImageLinearLayout = findViewById(R.id.reserveGuideLinearLayout);
        mDataIB = findViewById(R.id.dataImageButtonReserveGuideActivity);
        mCalendarIB = findViewById(R.id.calendarImageButtonReserveGuideActivity);
        mTimeIB = findViewById(R.id.timeImageButtonReserveGuideActivity);
        mPeopleIB = findViewById(R.id.peopleImageButtonReserveGuideActivity);
        mResumeIB = findViewById(R.id.resumeImageButtonReserveGuideActivity);


        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.garibaldi);
        Bitmap blurredBitmap = BlurBuilder.blur( this, originalBitmap );
        mImageLinearLayout.setBackground(new BitmapDrawable(getResources(), blurredBitmap));

    }

    public static Intent getIntentInstance(Context context)
    {
        return new Intent(context, ReserveGuideActivity.class);
    }

    public void imageCardClick(View view) {
        int id = view.getId();
        int value;
        switch (id) {
            case R.id.dataImageButtonReserveGuideActivity:
                value = 1;
                if(getReservationFragmentValue(currentFragment) > value) {
                    currentFragment = ReservationFragment.DATA_INSERT;
                    fragmentTransaction(currentFragment);
                }
                break;
            case R.id.calendarImageButtonReserveGuideActivity:
                value = 2;
                if(getReservationFragmentValue(currentFragment) > value) {
                    currentFragment = ReservationFragment.CALENDAR;
                    fragmentTransaction(currentFragment);
                }
                break;
            case R.id.timeImageButtonReserveGuideActivity:
                value = 3;
                if(getReservationFragmentValue(currentFragment) > value) {
                    currentFragment = ReservationFragment.TIME;
                    fragmentTransaction(currentFragment);
                }
                break;
            case R.id.peopleImageButtonReserveGuideActivity:
                value = 4;
                if(getReservationFragmentValue(currentFragment) > value) {
                    currentFragment = ReservationFragment.PEOPLE;
                    fragmentTransaction(currentFragment);
                }
                break;
            case R.id.resumeImageButtonReserveGuideActivity:
                break;
        }
    }

    public void timeCardClick(View view) {
        fragmentTransaction(ReservationFragment.PEOPLE);
    }

    public void insertDataClick(View view) {
        fragmentTransaction(ReservationFragment.CALENDAR);
    }

    public void peopleNumberClick(View view) {
        fragmentTransaction(ReservationFragment.RESUME);
    }

    public void reservationClick(View view) {
        Intent intent = ReservationMadeActivity.getIntentInstance(this);
        startActivity(intent);
        finish();
    }

    public void fragmentTransaction(ReservationFragment fragment) {
        Fragment selectedFragment = null;

        switch (fragment) {
            case DATA_INSERT:
                selectedFragment = new DataInsertFragment();
                break;
            case CALENDAR:
                selectedFragment = new CalendarFragment(this);
                break;
            case TIME:
                selectedFragment = new TimeFragment();
                break;
            case PEOPLE:
                selectedFragment = new PeopleNumberFragment();
                break;
            case RESUME:
                selectedFragment = new ResumeReservationFragment();
                break;
        }

        currentFragment = fragment;
        setImageButtonsColorByCurrentFragment(currentFragment);
        getSupportFragmentManager().beginTransaction().
                replace(fragmentId, selectedFragment).commit();
    }

    /**
     * Get value of input fragment
     * @param fragment ReservationFragment
     * @return 1 <= X <= 5
     */
    private int getReservationFragmentValue(ReservationFragment fragment) {
        switch (fragment){
            case DATA_INSERT:
                return 1;
            case CALENDAR:
                return 2;
            case TIME:
                return 3;
            case PEOPLE:
                return 4;
            case RESUME:
                return 5;
        }
        return -1;
    }

    /**
     * Set tint color of cardView's imageButtons in base of fragment value passed by value
     * @param fragment
     */
    private void setImageButtonsColorByCurrentFragment(ReservationFragment fragment) {
        switch (fragment) {
            case DATA_INSERT:
                mDataIB.setImageResource(R.drawable.ic_data_accent);
                mCalendarIB.setImageResource(R.drawable.ic_calendar_light);
                mTimeIB.setImageResource(R.drawable.ic_time_light);
                mPeopleIB.setImageResource(R.drawable.ic_people_light);
                mResumeIB.setImageResource(R.drawable.ic_check_light);
                break;
            case CALENDAR:
                mDataIB.setImageResource(R.drawable.ic_data_accent);
                mCalendarIB.setImageResource(R.drawable.ic_calendar_accent);
                mTimeIB.setImageResource(R.drawable.ic_time_light);
                mPeopleIB.setImageResource(R.drawable.ic_people_light);
                mResumeIB.setImageResource(R.drawable.ic_check_light);
                break;
            case TIME:
                mDataIB.setImageResource(R.drawable.ic_data_accent);
                mCalendarIB.setImageResource(R.drawable.ic_calendar_accent);
                mTimeIB.setImageResource(R.drawable.ic_time_accent);
                mPeopleIB.setImageResource(R.drawable.ic_people_light);
                mResumeIB.setImageResource(R.drawable.ic_check_light);
                break;
            case PEOPLE:
                mDataIB.setImageResource(R.drawable.ic_data_accent);
                mCalendarIB.setImageResource(R.drawable.ic_calendar_accent);
                mTimeIB.setImageResource(R.drawable.ic_time_accent);
                mPeopleIB.setImageResource(R.drawable.ic_people_accent);
                mResumeIB.setImageResource(R.drawable.ic_check_light);
                break;
            case RESUME:
                mDataIB.setImageResource(R.drawable.ic_data_accent);
                mCalendarIB.setImageResource(R.drawable.ic_calendar_accent);
                mTimeIB.setImageResource(R.drawable.ic_time_accent);
                mPeopleIB.setImageResource(R.drawable.ic_people_accent);
                mResumeIB.setImageResource(R.drawable.ic_check_accent);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        alertDialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            alertDialog.show();
        }
        return true;
    }
}