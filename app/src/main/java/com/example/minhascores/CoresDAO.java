package com.example.minhascores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

public class CoresDAO {

    private BancoHelper banco;

    public CoresDAO(Context context){
        this.banco = new BancoHelper(context, "dados", null, 1);
    }

    public void inserir(TarefaCor tarefaCor){
        ContentValues cv = new ContentValues();
        cv.put("descricao", tarefaCor.getDescricao());
        cv.put("red", tarefaCor.getCores()[0]);
        cv.put("green", tarefaCor.getCores()[1]);
        cv.put("blue", tarefaCor.getCores()[2]);
        this.banco.getWritableDatabase().insert("cores", null, cv);
    }

    public int count(){
        String sql = "select count(id) from cores";
        Cursor cursor = this.banco.getReadableDatabase().rawQuery(sql, null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    public ArrayList<TarefaCor> listar(){
        ArrayList<TarefaCor> lista = new ArrayList<>();

        String[] colunas = {"id", "descricao", "red", "green", "blue"};

        Cursor cursor = banco.getReadableDatabase().query("cores", colunas, null, null, null, null, null);
        cursor.moveToFirst();

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            int id = cursor.getInt(0);
            String descricao = cursor.getString(1);
            int red = cursor.getInt(2);
            int green = cursor.getInt(3);
            int blue = cursor.getInt(4);

            lista.add(new TarefaCor(id, descricao, new int[]{red, green, blue}));

        }

        return lista;

    }

    public TarefaCor buscar(int id){

        String[] colunas = {"id", "descricao", "red", "green", "blue"};
        String where = "id = ?";
        String[] pwhere = {String.valueOf(id)};

        Cursor cursor = banco.getReadableDatabase().query("cores", colunas, where, pwhere, null, null, null);
        cursor.moveToFirst();

        if (cursor.getCount() == 1){
            int idC = cursor.getInt(0);
            String descricao = cursor.getString(1);
            int red = cursor.getInt(2);
            int green = cursor.getInt(3);
            int blue = cursor.getInt(4);

            return new TarefaCor(id, descricao, new int[]{red, green, blue});
        }

        return null;
    }

    public void delete(int id){
        String where = "id = ?";
        String[] pwhere = {String.valueOf(id)};

        banco.getWritableDatabase().delete("cores", where, pwhere);
    }

    public void update(TarefaCor tarefaCor){

    }
}
