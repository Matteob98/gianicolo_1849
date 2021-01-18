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
import android.widget.ImageView;
import android.widget.TextView;

public class VisitStageActivity extends AppCompatActivity {

    private final static String visitStageTag = "TAG";

    private final String sanPancrazioDescription = "Le mura gianicolensi furono costruite nel 1643 dal papa urbano viii barberini, come attestato dalle numerose lapidi poste sulle mura, che recano lo stemma pontificio con le api, emblema della famiglia barberini. Le mura iniziano a porta portese, risalgono il gianicolo e si collegano alle mura vaticane nei pressi di porta cavalleggeri. Sono munite di dodici bastioni, numerati a partire da porta portese. In sommita' del tracciato si apre la porta san pancrazio. Queste mura erano ancora efficienti nel 1849, e costituirono la principale opera di difesa durante l'assedio.\n" +
            " \n" +
            "Porta san pancrazio venne difesa tenacemente fino all'ultimo. Dopo un furioso bombardamento la parte alta della porta crollo' seppellendo i difensori sotto le macerie. La figura, tratta da una litografia dell'epoca, mostra truppe francesi che entrano in citta' dalla porta semidistrutta.\n" +
            " \n" +
            "La porta venne ricostruita nella forma attuale nel 1854 (arch. V.Vespignani). In anni piu' recenti sono stati aperti i varchi laterali, per consentire il traffico stradale.";

    private final String villaSavorelliDescription = "Villa Savorelli (XV- XVIII secolo) è la testimonianza di secoli di storia che – dal Rinascimento passando per il Barocco e fino all’Età Romantica – hanno plasmato architettura e natura in un insieme suggestivo tra i più rilevanti tra le ville storiche della Tuscia viterbese. Costituita su 3 piani, a pianta quadrata, attualmente è predisposta per accogliere eventi e ospita la sede del Parco Regionale.";

    private final String mausoleoDescription = "Progettato dall'architetto Giovanni Jacobucci (1895-1970) e solennemente inaugurato il 3 novembre del 1941, dopo due anni di lavori, il Mausoleo accoglie i resti dei caduti nelle battaglie per Roma Capitale dal 1849 al 1870.\n" +
            "L’esigenza di ricordare degnamente i caduti per Roma fu posta con forza all’indomani della presa di Porta Pia. Nel 1878-79 lo stesso Garibaldi e il figlio Menotti furono tra i promotori della legge che riconobbe nel Gianicolo il luogo dove raccogliere i resti dei patrioti. Fu quindi realizzato il primo sepolcreto sulla base di minuziose ricognizioni effettuate per individuare le salme, alcune delle quali erano tumulate al Campo Verano, mentre quelle del 1870 erano ancora sepolte sui luoghi delle battaglie presso le Mura.";

    private final String brecciaDescription ="Questo tratto delle Mura Gianicolensi attualmente corrisponde alle mura di Villa Sciarra, nei pressi di Largo Berchet. La postazione del fotografo è sulla strada, che corrisponde al viale delle Mura Gianicolensi. Sulla sinistra si nota la porterula o posterla, che oggi costituisce ingresso laterale di Villa Sciarra. Dopo aver occupato la breccia, i francesi vi si trincerarono, piazzandovi una batteria, la batteria n° 11: sono visibili la rampa d’accesso e la gabbionata costruita per la protezione della batteria. Sul lato sinistro della breccia, all’interno delle mura, le tre aste verticali sono un residuo dell’impalcatura in legno che serviva ai difensori per raggiungere le feritoie. Da notare la precisione con cui l’artiglieria francese, per eliminare il fuoco della fucileria romana, ha demolito tutta la parte di mura al di sopra del cordolo, dove erano i parapetti e le feritoie dei difensori.";

    private TextView mDescriptionTextView, mTitleTextView, mSubtitleTextView, mDurationTextView;
    private ImageView mImageView;
    private Toolbar mToolbar;

    public enum StageName {
        SAN_PANCRAZIO,
        VILLA_SAVORELLI,
        MAUSOLEO,
        BRECCIA
    }
    private StageName thisStage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_stage);

        thisStage = StageName.valueOf(getIntent().getStringExtra(visitStageTag));

        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));

        layoutSettings(); //Item imports from xml

        toolbarSettings();
    }

    private void toolbarSettings() {
        mToolbar = findViewById(R.id.toolbarVisitStageActivity);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        switch (thisStage) {
            case SAN_PANCRAZIO:
                getSupportActionBar().setTitle("Tappa numero 1");
                break;
            case VILLA_SAVORELLI:
                getSupportActionBar().setTitle("Tappa numero 2");
                break;
            case MAUSOLEO:
                getSupportActionBar().setTitle("Tappa numero 3");
                break;
            case BRECCIA:
                getSupportActionBar().setTitle("Tappa numero 4");
                break;
        }
    }

    /**
     * Item imports from xml file
     */
    private void layoutSettings() {
        mDescriptionTextView = findViewById(R.id.stageTextViewVisitStageActivity);
        mTitleTextView = findViewById(R.id.titleTextViewVisitStageActivity);
        mSubtitleTextView = findViewById(R.id.subtitleTextView);
        mDurationTextView = findViewById(R.id.durationTextView);
        mImageView = findViewById(R.id.visitStageImageView);

        setDescription();
        setTitleTextView();
        setImage();
    }

    private void setImage() {
        switch (thisStage) {
            case SAN_PANCRAZIO:
                mImageView.setImageResource(R.drawable.san_pancrazio_quad);
                break;
            case VILLA_SAVORELLI:
                mImageView.setImageResource(R.drawable.villa_savorelli);
                break;
            case MAUSOLEO:
                mImageView.setImageResource(R.drawable.ossario_garibaldino);
                break;
            case BRECCIA:
                mImageView.setImageResource(R.drawable.breccia);
                break;
        }
    }

    private void setTitleTextView() {
        switch (thisStage) {
            case SAN_PANCRAZIO:
                mTitleTextView.setText("Porta di San Pancrazio");
                mSubtitleTextView.setText("Vista Piazzale Aurelio");
                mDurationTextView.setText("Durata: 30 minuti");
                break;
            case VILLA_SAVORELLI:
                mTitleTextView.setText("Villa Savorelli");
                mSubtitleTextView.setText("Vista ingresso");
                mDurationTextView.setText("Durata: 1 ora");
                break;
            case MAUSOLEO:
                mTitleTextView.setText("Mausoleo Garibaldino");
                mSubtitleTextView.setText("Vista frontale");
                mDurationTextView.setText("Durata: 30 minuti");
                break;
            case BRECCIA:
                mTitleTextView.setText("La Breccia");
                mSubtitleTextView.setText("Vista dall' esterno");
                mDurationTextView.setText("Durata: 30 minuti");
                break;
        }
    }

    private void setDescription() {
        switch (thisStage) {
            case SAN_PANCRAZIO:
                mDescriptionTextView.setText(sanPancrazioDescription);
                break;
            case VILLA_SAVORELLI:
                mDescriptionTextView.setText(villaSavorelliDescription);
                break;
            case MAUSOLEO:
                mDescriptionTextView.setText(mausoleoDescription);
                break;
            case BRECCIA:
                mDescriptionTextView.setText(brecciaDescription);
                break;
        }
    }

    public static Intent getIntentInstance(Context context, StageName stageName)
    {
        Intent intent = new Intent(context, VisitStageActivity.class);
        intent.putExtra(visitStageTag,stageName.name());
        return intent;
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

        String URL = "";

        switch (thisStage) {
            case SAN_PANCRAZIO:
                URL = "https://it.wikipedia.org/wiki/Porta_San_Pancrazio";
                break;
            case VILLA_SAVORELLI:
                URL = "http://www.retedimorestorichelazio.it/dimora/vt/sutri/villa-savorelli/";
                break;
            case MAUSOLEO:
               URL = "http://www.sovraintendenzaroma.it/i_luoghi/roma_medioevale_e_moderna/monumenti/mausoleo_ossario_garibaldino";
                break;
            case BRECCIA:
                URL = "https://comitatogianicolo.it/settimo-bastione";
                break;
        }

        Intent intent = new Intent("android.intent.action.VIEW",
                Uri.parse(URL));
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
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