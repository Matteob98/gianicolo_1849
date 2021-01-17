package com.unitelma.gianicolo1849;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.unitelma.gianicolo1849.utilities.BlurBuilder;

import java.util.Arrays;
import java.util.List;

public class ReserveGuideDialogFragment extends DialogFragment{

    private LinearLayout mImageLinearLayout, mGuideAvatarLayout;
    private ConstraintLayout mChooseGuideLayout;
    private LinearLayout mDataLayout, mCalendarLayout, mTimeLayout, mPeopleNumberLayout, mResumeLayout;
    private ImageButton mGuideIB, mDataIB, mCalendarIB, mTimeIB, mPeopleIB, mResumeIB;
    private CardView mCard1, mCard2, mCard3, mCard4, mCard5, mCard6, mCard7, mCard8, mCard9;
    private MaterialAlertDialogBuilder alertDialogBuilder;
    View view;
    private ItineraryFragment activity;

    public enum ReservationFragment {
        CHOOSE_GUIDE,
        DATA_INSERT,
        CALENDAR,
        TIME,
        PEOPLE,
        RESUME
    }
    private ReservationFragment currentStep;

    public ReserveGuideDialogFragment (ItineraryFragment activity) {
        this.activity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL , R.style.GuideDialogFragment);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.reserve_guide_layout, container, false);

        layoutSettings();

        toolbarSettings();

        initializeCurrentFragment();

        initializeExitAlertDialog();

        return view;
    }

    private void toolbarSettings() {
        Toolbar toolbar = view.findViewById(R.id.toolbarReserveGuide);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogBuilder.show();
            }
        });
        toolbar.setTitle("Itinerario Garibaldino");
    }

    private void initializeExitAlertDialog() {
        alertDialogBuilder = new MaterialAlertDialogBuilder(view.getContext());
        alertDialogBuilder.setTitle("Vuoi uscire?");
        alertDialogBuilder.setMessage("Se confermi perderai tutti i dati inseriti nella prenotazione");

        alertDialogBuilder.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialogBuilder.setNegativeButton("Esci", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                dismiss();
            }
        });
    }

    private void initializeCurrentFragment() {
        currentStep = ReservationFragment.CHOOSE_GUIDE;
        setImageButtonsColorByCurrentFragment(currentStep);
        viewTransaction(currentStep);
    }

    /**
     * Import xml items and initialize items
     */
    private void layoutSettings() {
        mImageLinearLayout = view.findViewById(R.id.reserveGuideLinearLayout);
        mGuideAvatarLayout = view.findViewById(R.id.guideAvatarLayout);
        mGuideIB = view.findViewById(R.id.guideImageButtonReserveGuideActivity);
        mDataIB = view.findViewById(R.id.dataImageButtonReserveGuideActivity);
        mCalendarIB = view.findViewById(R.id.calendarImageButtonReserveGuideActivity);
        mTimeIB = view.findViewById(R.id.timeImageButtonReserveGuideActivity);
        mPeopleIB = view.findViewById(R.id.peopleImageButtonReserveGuideActivity);
        mResumeIB = view.findViewById(R.id.resumeImageButtonReserveGuideActivity);
        mChooseGuideLayout = view.findViewById(R.id.chooseGuideLayout);
        mDataLayout =  view.findViewById(R.id.dataInsertLayout);
        mCalendarLayout =  view.findViewById(R.id.calendarLayout);
        mTimeLayout =  view.findViewById(R.id.timeLayout);
        mPeopleNumberLayout =  view.findViewById(R.id.peopleNumberLayout);
        mResumeLayout =  view.findViewById(R.id.resumeLayout);
        chooseGuideCardViewSettings();
        insertDataClickSettings();
        calendarClickSettings();
        timeClickSettings();
        peopleClickSettings();
        resumeClickSettings();
        navigationBarClickSettings();

        Bitmap originalBitmap = BitmapFactory.decodeResource(view.getResources(), R.drawable.garibaldi);
        Bitmap blurredBitmap = BlurBuilder.blur( view.getContext(), originalBitmap );
        mImageLinearLayout.setBackground(new BitmapDrawable(view.getResources(), blurredBitmap));

    }

    private void navigationBarClickSettings() {
        List<ImageButton> bts = Arrays.asList(mGuideIB, mDataIB, mCalendarIB, mTimeIB, mPeopleIB, mResumeIB);
        for (ImageButton bt : bts)
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imageCardClick(v);
                }
            });
    }

    private void resumeClickSettings() {
        MaterialButton button = view.findViewById(R.id.containedButtonResume);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.existReservation=true;
                activity.guideCardViewSettings();
                dismiss();
            }
        });
    }

    private void peopleClickSettings() {
        MaterialCardView card1, card2, card3, card4, card5, card6;
        card1 = view.findViewById(R.id.timeCard1);
        card2 = view.findViewById(R.id.timeCard2);
        card3 = view.findViewById(R.id.timeCard3);
        card4 = view.findViewById(R.id.timeCard4);
        card5 = view.findViewById(R.id.timeCard5);
        card6 = view.findViewById(R.id.timeCard6);

        List<MaterialCardView> cards = Arrays.asList(card1, card2, card3, card4, card5, card6);
        for (MaterialCardView card: cards) {
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewTransaction(ReservationFragment.RESUME);
                }
            });
        }
    }

    private void timeClickSettings() {
        MaterialCardView card1, card2, card3, card4, card5;
        card1 = view.findViewById(R.id.timeViewCard1);
        card2 = view.findViewById(R.id.timeViewCard2);
        card3 = view.findViewById(R.id.timeViewCard3);
        card4 = view.findViewById(R.id.timeViewCard4);
        card5 = view.findViewById(R.id.timeViewCard5);

        List<MaterialCardView> cards = Arrays.asList(card1, card2, card3, card4, card5);

        for (MaterialCardView card: cards)
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewTransaction(ReservationFragment.PEOPLE);
                }
            });
    }

    private void calendarClickSettings() {
        CalendarView calendar = view.findViewById(R.id.calendarCalendarFragment);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                viewTransaction(ReservationFragment.TIME);
            }
        });
    }

    private void insertDataClickSettings() {
        Button mButton = view.findViewById(R.id.containedButtonInsertData);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewTransaction(ReservationFragment.CALENDAR);
            }
        });
    }

    private void chooseGuideCardViewSettings() {
        mCard1 = view.findViewById(R.id.cardView1);
        mCard2 = view.findViewById(R.id.cardView2);
        mCard3 = view.findViewById(R.id.cardView3);
        mCard4= view.findViewById(R.id.cardView4);
        mCard5 = view.findViewById(R.id.cardView5);
        mCard6 = view.findViewById(R.id.cardView6);
        mCard7 = view.findViewById(R.id.cardView7);
        mCard8 = view.findViewById(R.id.cardView8);
        mCard9 = view.findViewById(R.id.cardView9);


        List<CardView> cards = Arrays.asList(mCard1, mCard2, mCard3, mCard4, mCard5, mCard6, mCard7, mCard8, mCard9);

        for (CardView c: cards) {
            c.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewTransaction(ReservationFragment.DATA_INSERT);
                }
            });
        }
    }

    public void imageCardClick(View view) {
        int id = view.getId();
        int value;
        switch (id) {
            case R.id.guideImageButtonReserveGuideActivity:
                value = 1;
                if(getReservationFragmentValue(currentStep) > value) {
                    currentStep = ReservationFragment.CHOOSE_GUIDE;
                    viewTransaction(currentStep);
                }
                break;
            case R.id.dataImageButtonReserveGuideActivity:
                value = 2;
                if(getReservationFragmentValue(currentStep) > value) {
                    currentStep = ReservationFragment.DATA_INSERT;
                    viewTransaction(currentStep);
                }
                break;
            case R.id.calendarImageButtonReserveGuideActivity:
                value = 3;
                if(getReservationFragmentValue(currentStep) > value) {
                    currentStep = ReservationFragment.CALENDAR;
                    viewTransaction(currentStep);
                }
                break;
            case R.id.timeImageButtonReserveGuideActivity:
                value = 4;
                if(getReservationFragmentValue(currentStep) > value) {
                    currentStep = ReservationFragment.TIME;
                    viewTransaction(currentStep);
                }
                break;
            case R.id.peopleImageButtonReserveGuideActivity:
                value = 5;
                if(getReservationFragmentValue(currentStep) > value) {
                    currentStep = ReservationFragment.PEOPLE;
                    viewTransaction(currentStep);
                }
                break;
            case R.id.resumeImageButtonReserveGuideActivity:
                break;
        }
    }

    public void viewTransaction(ReservationFragment step) {
        currentStep = step;

        mChooseGuideLayout.setVisibility(View.GONE);
        mDataLayout.setVisibility(View.GONE);
        mCalendarLayout.setVisibility(View.GONE);
        mTimeLayout.setVisibility(View.GONE);
        mPeopleNumberLayout.setVisibility(View.GONE);
        mResumeLayout.setVisibility(View.GONE);

        switch (step) {
            case CHOOSE_GUIDE:
                mChooseGuideLayout.setVisibility(View.VISIBLE);
                break;
            case DATA_INSERT:
                mDataLayout.setVisibility(View.VISIBLE);
                break;
            case CALENDAR:
                mCalendarLayout.setVisibility(View.VISIBLE);
                break;
            case TIME:
                mTimeLayout.setVisibility(View.VISIBLE);
                break;
            case PEOPLE:
                mPeopleNumberLayout.setVisibility(View.VISIBLE);
                break;
            case RESUME:
                mResumeLayout.setVisibility(View.VISIBLE);
                break;
        }


        if (step == ReservationFragment.CHOOSE_GUIDE)
            mGuideAvatarLayout.setVisibility(View.INVISIBLE);
        else
            mGuideAvatarLayout.setVisibility(View.VISIBLE);

        setImageButtonsColorByCurrentFragment(currentStep);
    }

    /**
     * Get value of input fragment
     * @param fragment ReservationFragment
     * @return 1 <= X <= 6
     */
    private int getReservationFragmentValue(ReservationFragment fragment) {
        switch (fragment){
            case CHOOSE_GUIDE:
                return 1;
            case DATA_INSERT:
                return 2;
            case CALENDAR:
                return 3;
            case TIME:
                return 4;
            case PEOPLE:
                return 5;
            case RESUME:
                return 6;
        }
        return -1;
    }

    /**
     * Set tint color of cardView's imageButtons in base of fragment value passed by value
     * @param fragment
     */
    private void setImageButtonsColorByCurrentFragment(ReservationFragment fragment) {
        switch (fragment) {
            case CHOOSE_GUIDE:
                mGuideIB.setImageResource(R.drawable.ic_people_one_accent);
                mDataIB.setImageResource(R.drawable.ic_data_light);
                mCalendarIB.setImageResource(R.drawable.ic_calendar_light);
                mTimeIB.setImageResource(R.drawable.ic_time_light);
                mPeopleIB.setImageResource(R.drawable.ic_people_light);
                mResumeIB.setImageResource(R.drawable.ic_check_light);
                break;
            case DATA_INSERT:
                mGuideIB.setImageResource(R.drawable.ic_people_one_accent);
                mDataIB.setImageResource(R.drawable.ic_data_accent);
                mCalendarIB.setImageResource(R.drawable.ic_calendar_light);
                mTimeIB.setImageResource(R.drawable.ic_time_light);
                mPeopleIB.setImageResource(R.drawable.ic_people_light);
                mResumeIB.setImageResource(R.drawable.ic_check_light);
                break;
            case CALENDAR:
                mGuideIB.setImageResource(R.drawable.ic_people_one_accent);
                mDataIB.setImageResource(R.drawable.ic_data_accent);
                mCalendarIB.setImageResource(R.drawable.ic_calendar_accent);
                mTimeIB.setImageResource(R.drawable.ic_time_light);
                mPeopleIB.setImageResource(R.drawable.ic_people_light);
                mResumeIB.setImageResource(R.drawable.ic_check_light);
                break;
            case TIME:
                mGuideIB.setImageResource(R.drawable.ic_people_one_accent);
                mDataIB.setImageResource(R.drawable.ic_data_accent);
                mCalendarIB.setImageResource(R.drawable.ic_calendar_accent);
                mTimeIB.setImageResource(R.drawable.ic_time_accent);
                mPeopleIB.setImageResource(R.drawable.ic_people_light);
                mResumeIB.setImageResource(R.drawable.ic_check_light);
                break;
            case PEOPLE:
                mGuideIB.setImageResource(R.drawable.ic_people_one_accent);
                mDataIB.setImageResource(R.drawable.ic_data_accent);
                mCalendarIB.setImageResource(R.drawable.ic_calendar_accent);
                mTimeIB.setImageResource(R.drawable.ic_time_accent);
                mPeopleIB.setImageResource(R.drawable.ic_people_accent);
                mResumeIB.setImageResource(R.drawable.ic_check_light);
                break;
            case RESUME:
                mGuideIB.setImageResource(R.drawable.ic_people_one_accent);
                mDataIB.setImageResource(R.drawable.ic_data_accent);
                mCalendarIB.setImageResource(R.drawable.ic_calendar_accent);
                mTimeIB.setImageResource(R.drawable.ic_time_accent);
                mPeopleIB.setImageResource(R.drawable.ic_people_accent);
                mResumeIB.setImageResource(R.drawable.ic_check_accent);
                break;
        }
    }

}

