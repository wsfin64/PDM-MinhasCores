package com.example.minhascores;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class TarefaAdapter extends ArrayAdapter<TarefaCor> {


    public TarefaAdapter(@NonNull Context context, @NonNull ArrayList<TarefaCor> tarefaCors) {
        super(context, 0, tarefaCors);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TarefaCor tarefaCor = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.tarefa_layout, parent, false);
        }

        TextView textViewDescricao = convertView.findViewById(R.id.tvTarefaDescricao);
        TextView textViewCores = convertView.findViewById(R.id.tvTarefaCores);

        ImageView corIcone = convertView.findViewById(R.id.ivIcone);

        textViewDescricao.setText(tarefaCor.getDescricao());

        String coresFormatadas = "(" + tarefaCor.getCores()[0] + ", " + tarefaCor.getCores()[1] + ", " + tarefaCor.getCores()[2] + ")";
        textViewCores.setText(coresFormatadas);

        int color = Color.rgb(tarefaCor.getCores()[0], tarefaCor.getCores()[1], tarefaCor.getCores()[2]);

        corIcone.setColorFilter(color);

        convertView.setMinimumHeight(100);

        return convertView;
    }
}
