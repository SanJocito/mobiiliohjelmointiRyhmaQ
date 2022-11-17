package com.example.mobiiliohjelmointiryhmaq;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

// Tämä JAVA tiedosto on sitä varten että sovellusta aukaistaessa tulee lataus ympyrä pyörimään
// saadaan xml tiedostosta käyttöön tiedot tätä kautta.
// tämän lisäksi pitää firstFragment.xml tiedostoon lisätä muutama juttu



public class FirstFragment extends AppCompatActivity {
    private Button button; // Mennään seuravaan näyttöön.

    private RelativeLayout Koti;
    private ProgressBar LadataanSivu;
    private TextView KaupunginNimi, Lampotila, Ehto, pvm;
    private TextInputEditText MuokkaaKaupunkia;
    private ImageView TaustaVariIV, SaaTilaIV, EtsiLogoIV;
    private RecyclerView RecycleSaaEnnuste;
    Date date = new Date();
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
    String str = formatter.format(date);

    private final String url = "https://api.openweathermap.org/data/2.5/weather?q=Kuopio&units=metric&appid=3b8f9987ddadf8e26fcaa86cef8bfd62";
    // Tästä tehdään GPS homma jos ehtii..

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_first);

        KaupunginNimi = findViewById(R.id.idNykyinenKaupunki);
        Lampotila = findViewById(R.id.idLampotila);
        TaustaVariIV = findViewById(R.id.idTaustaVari);
        SaaTilaIV = findViewById(R.id.idSaaTila);
        pvm = findViewById(R.id.idDate);
        button = (Button) findViewById(R.id.idHaeLisaaBtn);

        // Ei tarvita??
        //Koti = findViewById(R.id.idKoti);
        //LadataanSivu = findViewById(R.id.idPBLoading);
        //Ehto = findViewById(R.id.idEhto);
        //MuokkaaKaupunkia = findViewById(R.id.idMuokkaaKaupunkia);
        //RecycleSaaEnnuste = findViewById(R.id.idRecycleSaaEnnuste);
        //EtsiLogoIV = findViewById(R.id.idEtsiLogo);

        //---------------------------------
        //NÄMÄ TARVITAAN VIELÄ ULKOASUUN!
        //Maa = findViewById(R.id.idNykyinenMaa);
        //Paivamaara = findViewById(R.id.idPaivamaara);
        //Kosteus = findViewById(R.id.idKosteus);
        //Tuuli = findViewById(R.id.idTuuli);
        //TuntuuKuin = findViewById(R.id.idTuntuuKuin);


        getData();
        // Toinen sivu
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SecondFragment();
            }
        });

    }

    @SuppressLint("SetTextI18n")
    public void getData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            // Tarkistetaan saadaanko yhteys osoitteeseen
            Log.e("Res: ", response);

            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONObject jsonMain = jsonObject.getJSONObject("main");
                String currentWeather = jsonMain.getString("temp");
                Lampotila.setText(currentWeather);
                pvm.setText(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, error -> {

        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        KaupunginNimi.setText("Kuopio");//Voidaan mahdollisesti hakea GPS avulla, jos jää aikaa.
    }
    // Lähdetään toiselle sivulle
    public void SecondFragment() {
        Intent intent = new Intent(this, SecondFragment.class);
        startActivity(intent);
    }
}
