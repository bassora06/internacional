package com.example.internacionalisacao;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class Idioma extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_idioma);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.idioma), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });


    }

    private void changeLanguage(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);

        Resources res = getResources();
        Configuration config = new Configuration(res.getConfiguration());
        config.setLocale(locale);
        res.updateConfiguration(config, res.getDisplayMetrics());
    }

    public void telaHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        // Não precisa de recreate aqui, a atividade MainActivity vai tratar do idioma.
    }

    public void idiomaPt(View v) {
        changeLanguageAndStore("pt");
    }

    public void idiomaEn(View v) {
        changeLanguageAndStore("en");
    }

    public void idiomaEs(View v) {
        changeLanguageAndStore("es");
    }

    public void idiomaIt(View v) {
        changeLanguageAndStore("it");
    }

    public void idiomaDe(View v) {
        changeLanguageAndStore("de");
    }

    public void idiomaFr(View v) {
        changeLanguageAndStore("fr");
    }

    private void changeLanguageAndStore(String languageCode) {
        changeLanguage(languageCode);
        SharedPreferences.Editor dados = getSharedPreferences("AppPrefs", MODE_PRIVATE).edit();
        dados.putString("idioma", languageCode);
        dados.apply();
        recreate();
    }
}
