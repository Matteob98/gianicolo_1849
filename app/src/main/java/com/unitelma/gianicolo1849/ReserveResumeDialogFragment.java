package com.unitelma.gianicolo1849;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

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

        mCancelButton = view.findViewById(R.id.cancelButtonResume);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAlertDialogBuilder.show();
            }
        });

        Toolbar toolbar = view.findViewById(R.id.toolbarReserveGuideReserve);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        toolbar.setTitle("La tua prenotazione");

        return view;
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
                activity.guideCardViewSettings();
                dialog.dismiss();
                dismiss();
            }
        });
    }
}
