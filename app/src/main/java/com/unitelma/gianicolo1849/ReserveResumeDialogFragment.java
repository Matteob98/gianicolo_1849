package com.unitelma.gianicolo1849;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReserveResumeDialogFragment extends DialogFragment {

    private ItineraryFragment activity;
    private View view;
    private MaterialButton mCancelButton;
    private MaterialAlertDialogBuilder cancelAlertDialogBuilder;

    public ReserveResumeDialogFragment(ItineraryFragment activity) {
        this.activity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL , R.style.GuideDialogFragment);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.reserve_resume_layout, container, false);

        initializeCancelAlertDialog();

        toolbarSettings();

        layoutSettings();

        return view;
    }

    private void layoutSettings() {
        mCancelButton = view.findViewById(R.id.cancelButtonResume);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAlertDialogBuilder.show();
            }
        });

        layoutGuideSettings();

        layoutReserveSettings();
    }

    private void layoutReserveSettings() {
        TextView mName = view.findViewById(R.id.name_reserve);
        TextView mPhone = view.findViewById(R.id.phone_reserve);
        TextView mEmail = view.findViewById(R.id.email_reserve);
        TextView mData = view.findViewById(R.id.data_reserve);
        TextView mTime = view.findViewById(R.id.time_reserve);
        TextView mPeople = view.findViewById(R.id.people_reserve);
        TextView mPrice = view.findViewById(R.id.price_reserve);

        mName.setText(activity.myReserve.myName);
        mPhone.setText(activity.myReserve.myPhone);
        mEmail.setText(activity.myReserve.myEmail);
        mData.setText(activity.myReserve.date);
        mTime.setText(activity.myReserve.startTime + "-" + activity.myReserve.endTime);
        mPeople.setText(activity.myReserve.peopleNumber);
        mPrice.setText(activity.myReserve.totalPrice + " €");

    }

    private void layoutGuideSettings() {
        CircleImageView mCircleImageView = view.findViewById(R.id.profile_image_resume);
        TextView mTextView = view.findViewById(R.id.guide_name_resume);
        mTextView.setText(activity.myReserve.guideName);
        switch (activity.myReserve.guideName) {
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

    private void toolbarSettings() {
        Toolbar toolbar = view.findViewById(R.id.toolbarReserveGuideReserve);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        toolbar.setTitle("La tua prenotazione");
    }

    private void initializeCancelAlertDialog() {
        cancelAlertDialogBuilder = new MaterialAlertDialogBuilder(view.getContext());
        cancelAlertDialogBuilder.setTitle("Cancellare la prenotazione?");
        cancelAlertDialogBuilder.setMessage("Se confermi perderai tutti i dati inseriti nella prenotazione");

        cancelAlertDialogBuilder.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        cancelAlertDialogBuilder.setNegativeButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.existReservation=false;
                activity.myReserve = null;
                activity.guideCardViewSettings();
                dialog.dismiss();
                dismiss();
            }
        });
    }
}
