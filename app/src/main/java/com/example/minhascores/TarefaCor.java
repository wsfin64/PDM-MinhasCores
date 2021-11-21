package com.example.minhascores;

import java.io.Serializable;

public class TarefaCor implements Serializable {

    private int id;
    private String descricao;
    private int[] cores;

    public TarefaCor(String descricao, int[] cores){
        this.id = -1;
        this.descricao = descricao;
        this.cores = cores;
    }

    public TarefaCor(int id, String descricao, int[] cores){
        this.id = id;
        this.descricao = descricao;
        this.cores = cores;
    }

    public int getId(){
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int[] getCores() {
        return cores;
    }

    public void setCores(int[] cores) {
        this.cores = cores;
    }

    public String toString(){
        return "" + descricao  + " (" + cores[0] + ", " + " " + cores[1] + ", " + " " + cores[2] + ") ";
    }
}
