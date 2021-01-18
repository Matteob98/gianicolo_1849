package com.unitelma.gianicolo1849;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unitelma.gianicolo1849.classes.Reserve;

public class ItineraryFragment extends Fragment {

    private View view;
    private ConstraintLayout mGuideLayout, mReservationLayout;
    public boolean existReservation;
    public Reserve myReserve;

    public ItineraryFragment() {
        // Required empty public constructor
    }

    public static ItineraryFragment newInstance() {
        ItineraryFragment fragment = new ItineraryFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_itinerary, container, false);

        mGuideLayout = view.findViewById(R.id.grayViewItineraryFragment);
        mReservationLayout = view.findViewById(R.id.guideViewItineraryFragment);
        existReservation = false;
        mGuideLayout.setVisibility(View.VISIBLE);
        mReservationLayout.setVisibility(View.INVISIBLE);

        mGuideLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guideCardViewClick(v);
            }
        });
        mReservationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reservationCardViewClick(v);
            }
        });

        return view;
    }

    private void reservationCardViewClick(View v) {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        DialogFragment dialog = new ReserveResumeDialogFragment(this);
        dialog.show(ft, "test");
    }

    public void guideCardViewSettings() {
        if(existReservation){
            mGuideLayout.setVisibility(View.INVISIBLE);
            mReservationLayout.setVisibility(View.VISIBLE);
        }
        else {
            mGuideLayout.setVisibility(View.VISIBLE);
            mReservationLayout.setVisibility(View.INVISIBLE);
        }
    }

    public void guideCardViewClick(View view) {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        DialogFragment dialog = new ReserveGuideDialogFragment(this);
        dialog.show(ft, "test");
    }
}