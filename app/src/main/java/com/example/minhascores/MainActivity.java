package com.example.minhascores;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView cores;
    private FloatingActionButton fabAdd;
    private ArrayList<TarefaCor> listaCores;

    private CoresDAO coresDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.coresDAO = new CoresDAO(this);

        this.listaCores = coresDAO.listar();

        this.cores = findViewById(R.id.lvMainCores);
        this.fabAdd = findViewById(R.id.fabMainAdd);

        TarefaAdapter tarefaAdapder = new TarefaAdapter(this,  this.listaCores);

        this.cores.setAdapter(tarefaAdapder);



        // Contrato para pegar Intent da SelectorActiviy
        ActivityResultLauncher<Intent> selectorResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK){

                        TarefaCor tarefaCor = (TarefaCor) result.getData().getSerializableExtra("BACKGROUND");
                        tarefaAdapder.add(tarefaCor);

                    }
                }
        );

        // Abrir nova tela de seleção de cores
        this.fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentColetor = new Intent(MainActivity.this, SelectorActivity.class);
                selectorResult.launch(intentColetor);
            }
        });

        // click curto para visualizar informações do item
        this.cores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TarefaCor tarefaCorSelecionada = listaCores.get(position);
                Toast.makeText(getApplicationContext(), tarefaCorSelecionada.getDescricao(), Toast.LENGTH_SHORT).show();
            }
        });

        // cliok longo para remover item
        this.cores.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //listaCores.remove(position);
                TarefaCor tarefaCorSelecionada = listaCores.get(position);
                coresDAO.delete(tarefaCorSelecionada.getId());
                listaCores.remove(position);

                tarefaAdapder.notifyDataSetChanged();
                return true;
            }
        });

    }



}

