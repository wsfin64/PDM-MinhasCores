package com.example.minhascores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class SelectorActivity extends AppCompatActivity {

    private TextView cor;
    private SeekBar corRed;
    private SeekBar corGreen;
    private SeekBar corBlue;
    private Button btSalvar;
    private Button btCancelar;
    private EditText etDescricao;

    private int red;
    private int green;
    private int blue;
    int background;
    private CoresDAO coresDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);

        this.cor = findViewById(R.id.texto);
        this.corRed = findViewById(R.id.seekRed);
        this.corGreen = findViewById(R.id.seekGreen);
        this.corBlue = findViewById(R.id.seekBlue);
        this.btSalvar = findViewById(R.id.btSelectorSalvar);
        this.btCancelar = findViewById(R.id.btSelectorCanelar);
        this.etDescricao = findViewById(R.id.etFormDescricao);
        this.coresDAO = new CoresDAO(this);

        this.corRed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                red = progress;
                atualizarBackground();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        this.corGreen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                green = progress;
                atualizarBackground();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        this.corBlue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                blue = progress;
                atualizarBackground();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        this.btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });

        this.btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelar();
            }
        });

    }

    public void salvar(){
        int[] cores = {red, green, blue};
        String descricao = etDescricao.getText().toString();

        TarefaCor tarefaCor = new TarefaCor(descricao, cores);
        coresDAO.inserir(tarefaCor);

        Log.i("APP_CORES", String.valueOf(coresDAO.count()));

        Intent intentCor = new Intent().putExtra("BACKGROUND", tarefaCor);
        setResult(RESULT_OK, intentCor);
        finish();
    }

    public void cancelar(){
        finish();
    }

    public void atualizarBackground(){
        background = Color.rgb(red, green, blue);
        cor.setBackgroundColor(background);
        String hex = String.format("%02X%02X%02X", red, green, blue);
        cor.setText("#" + hex);


    }
}