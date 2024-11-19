package com.example.internacionalisacao;


import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;



public class Cadastro extends AppCompatActivity {

    private EditText edtPlaca, edtQuantidade;
    private Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        edtPlaca = findViewById(R.id.edtPlaca);
        edtQuantidade = findViewById(R.id.edtQuantidade);
        btnEnviar = findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String placa = edtPlaca.getText().toString();
                String quantidade = edtQuantidade.getText().toString();

                if (!placa.isEmpty() && !quantidade.isEmpty()) {
                    new EnviarDadosTask().execute(placa, quantidade);
                } else {
                    Toast.makeText(Cadastro.this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private class EnviarDadosTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String placa = params[0];
            String quantidade = params[1];

            try {
                URL url = new URL("http://10.0.2.2/comercial/registro.php"); // Substitua pela URL do seu serviço
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                conn.setDoOutput(true);

                JSONObject jsonParam = new JSONObject();
                jsonParam.put("placa", placa);
                jsonParam.put("quantidade", quantidade);

                OutputStream os = conn.getOutputStream();
                os.write(jsonParam.toString().getBytes("UTF-8"));
                os.close();

                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    return "Dados enviados com sucesso!";
                } else {
                    return "Erro ao enviar dados. Código: " + responseCode;
                }

            } catch (Exception e) {
                return "Erro: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Toast.makeText(Cadastro.this, result, Toast.LENGTH_SHORT).show();
        }
    }

    public void telaHome(View view){
        Intent tela = new Intent(this, MainActivity.class);
        startActivity(tela);

    }


}
