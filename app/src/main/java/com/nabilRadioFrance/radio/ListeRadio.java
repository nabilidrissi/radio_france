package com.nabilRadioFrance.radio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.nabilRadioFrance.radio.R;

import java.util.ArrayList;
import java.util.List;

public class ListeRadio extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Radio> listitems;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_radio);
        recyclerView = findViewById(R.id.radiosview);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        int[] imageId = {R.drawable.france_culture,R.drawable.france_inter,R.drawable.france_info,R.drawable.europe1,R.drawable.rfm,R.drawable.rtl,R.drawable.rtl_2
                ,R.drawable.fun_radio_france,R.drawable.virgin_radio,R.drawable.rmc
                ,R.drawable.skyrock,R.drawable.radio_classique,R.drawable.oui_fm,R.drawable.jazz_radio
                ,R.drawable.m_radio,R.drawable.france_musique,R.drawable.fip,
                R.drawable.fip_disquaire,R.drawable.fip_electro,R.drawable.fip_jazz,R.drawable.fip_groove,R.drawable.fip_rock,R.drawable.fip_monde
        };
        String[] nomRadio = {"france culture","France Inter","France Info","Europe 1","RFM","RTL","RTL2","Fun Radio France"
                ,"Virgin Radio","RMC","Skyrock","Radio Classique","oui FM","Jazz Radio","M Radio","France Musique","FIP","FIP Disquaire","FIP Electro","FIP Jazz","FIP Groove","FIP Rock","FIP Monde"};
        String[] url = {"http://direct.franceculture.fr/live/franceculture-midfi.mp3","http://direct.franceinter.fr/live/franceinter-midfi.mp3","http://icecast.radiofrance.fr/franceinfo-midfi.mp3","http://ais-live.cloud-services.paris/europe1.mp3","http://ais-live.cloud-services.paris:8000/rfm.mp3","http://icecast.rtl.fr/rtl-1-44-64?listen=webCwsBCggNCQgLDQUGBAcGBg","http://icecast.rtl.fr/rtl2-1-44-128?listen=webCwsBCggNCQgLDQUGBAcGBg"
                ,"http://icecast.funradio.fr/fun-1-44-128?listen=webCwsBCggNCQgLDQUGBAcGBg","http://stream.virginradio.fr/virgin.mp3","http://rmc.bfmtv.com/rmcinfo-mp3","http://www.skyrock.fm/stream.php/tunein16_128mp3.mp3","http://radioclassique.ice.infomaniak.ch/radioclassique-high.mp3","http://ouifm.ice.infomaniak.ch/ouifm-high.mp3","http://jazzradio.ice.infomaniak.ch/jazzradio-high.mp3","http://mfm.ice.infomaniak.ch/mfm-128.mp3","http://direct.francemusique.fr/live/francemusique-midfi.mp3"
        ,"http://direct.fipradio.fr/live/fip-midfi.mp3","http://direct.fipradio.fr/live/fip-webradio6.mp3","http://direct.fipradio.fr/live/fip-webradio8.mp3",
        "http://direct.fipradio.fr/live/fip-webradio2.mp3","http://direct.fipradio.fr/live/fip-webradio3.mp3","http://direct.fipradio.fr/live/fip-webradio1.mp3"
        ,"http://direct.fipradio.fr/live/fip-webradio4.mp3"};

        ArrayList<Radio> radioArrayList = new ArrayList<>();

        for(int i = 0;i< imageId.length;i++){

            Radio user = new Radio(nomRadio[i],url[i],imageId[i]);
            radioArrayList.add(user);

        }

        adapter = new MyAdapter(this,radioArrayList);
        recyclerView.setAdapter(adapter);


    }
}