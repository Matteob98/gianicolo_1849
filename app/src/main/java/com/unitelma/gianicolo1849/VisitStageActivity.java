package com.unitelma.gianicolo1849;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class VisitStageActivity extends AppCompatActivity {

    private final String stageDescription = "Le mura gianicolensi furono costruite nel 1643 dal papa urbano viii barberini, come attestato dalle numerose lapidi poste sulle mura, che recano lo stemma pontificio con le api, emblema della famiglia barberini. Le mura iniziano a porta portese, risalgono il gianicolo e si collegano alle mura vaticane nei pressi di porta cavalleggeri. Sono munite di dodici bastioni, numerati a partire da porta portese. In sommita' del tracciato si apre la porta san pancrazio. Queste mura erano ancora efficienti nel 1849, e costituirono la principale opera di difesa durante l'assedio.\n" +
            " \n" +
            "Porta san pancrazio venne difesa tenacemente fino all'ultimo. Dopo un furioso bombardamento la parte alta della porta crollo' seppellendo i difensori sotto le macerie. La figura, tratta da una litografia dell'epoca, mostra truppe francesi che entrano in citta' dalla porta semidistrutta.\n" +
            " \n" +
            "La porta venne ricostruita nella forma attuale nel 1854 (arch. V.Vespignani). In anni piu' recenti sono stati aperti i varchi laterali, per consentire il traffico stradale.";

    private TextView mTextView;
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_stage);

        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));

        layoutSettings(); //Item imports from xml

        toolbarSettings();

        mTextView.setText(stageDescription);
    }

    private void toolbarSettings() {
        mToolbar = findViewById(R.id.toolbarVisitStageActivity);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Tappa numero 3");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Item imports from xml file
     */
    private void layoutSettings() {
        mTextView = findViewById(R.id.stageTextViewVisitStageActivity);
    }

    public static Intent getIntentInstance(Context context)
    {
        return new Intent(context, VisitStageActivity.class);
    }

    /**
     * Open default camera in Android
     * @param view the view
     */
    public void cameraButtonClick(View view) {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
    }

    /**
     * Redirect to wikipedia page
     * @param view the view
     */
    public void viewMoreClick(View view) {
        Intent intent = new Intent("android.intent.action.VIEW",
                Uri.parse("https://it.wikipedia.org/wiki/Porta_San_Pancrazio"));
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.shareItem:
                shareClick();
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void shareClick() {
        Intent intent = ShareActivity.getIntentInstance(this);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.visit_stage_menu, menu);
        return true;
    }

}