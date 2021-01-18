package com.unitelma.gianicolo1849;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unitelma.gianicolo1849.classes.Reserve;

import de.hdodenhof.circleimageview.CircleImageView;

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
        FragmentTransaction ft =
                getActivity().
                        getSupportFragmentManager().
                        beginTransaction();

        DialogFragment dialog = new ReserveResumeDialogFragment(this);
        dialog.show(ft, "test");
    }

    public void guideCardViewSettings() {
        if(existReservation){
            mGuideLayout.setVisibility(View.INVISIBLE);
            mReservationLayout.setVisibility(View.VISIBLE);

            layoutGuideSettings();

        }
        else {
            mGuideLayout.setVisibility(View.VISIBLE);
            mReservationLayout.setVisibility(View.INVISIBLE);
        }
    }

    private void layoutGuideSettings() {
        CircleImageView mCircleImageView = view.findViewById(R.id.guideButtonItineraryFragment2);
        TextView mTextView = view.findViewById(R.id.textViewGuide2);
        TextView mDataTextView = view.findViewById(R.id.myGuideDateTextView);
        mTextView.setText(myReserve.guideName);
        mDataTextView.setText("Appuntamento il: " + myReserve.date);
        switch (myReserve.guideName) {
            case "Marco Rossi":
                mCircleImageView.setImageResource(R.drawable.avatar_uomo_1);
                break;
            case "Giulia Bianchi":
                mCircleImageView.setImageResource(R.drawable.avatar_donna_1);
                break;
            case "Fabrizio Moro":
                mCircleImageView.setImageResource(R.drawable.avatar_uomo_2);
                break;
            case "Matteo Neri":
                mCircleImageView.setImageResource(R.drawable.avatar_uomo_3);
                break;
            case "Gianna Camilli":
                mCircleImageView.setImageResource(R.drawable.avatar_donna_2);
                break;
            case "Francesca Paroli":
                mCircleImageView.setImageResource(R.drawable.avatar_donna_3);
                break;
            case "Franco Messina":
                mCircleImageView.setImageResource(R.drawable.avatar_uomo_4);
                break;
            case "Paolo D'aversa":
                mCircleImageView.setImageResource(R.drawable.avatar_uomo_5);
                break;
            case "Carolina Verde":
                mCircleImageView.setImageResource(R.drawable.avatar_donna_4);
                break;
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