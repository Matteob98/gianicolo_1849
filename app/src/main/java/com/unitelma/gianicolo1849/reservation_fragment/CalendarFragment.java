package com.unitelma.gianicolo1849.reservation_fragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import com.unitelma.gianicolo1849.R;
import com.unitelma.gianicolo1849.ReserveGuideActivity;

public class CalendarFragment extends Fragment {

    private static ReserveGuideActivity parentActivity;

    public CalendarFragment(ReserveGuideActivity parentActivity) {
        CalendarFragment.parentActivity = parentActivity;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CalendarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalendarFragment newInstance() {
        CalendarFragment fragment = new CalendarFragment(parentActivity);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        CalendarView mCalendarView = view.findViewById(R.id.calendarCalendarFragment);


        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth)  {
                parentActivity.fragmentTransaction(ReserveGuideActivity.ReservationFragment.TIME);
            }
        });
    }

}