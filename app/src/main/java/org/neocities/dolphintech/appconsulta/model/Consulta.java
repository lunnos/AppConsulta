package org.neocities.dolphintech.appconsulta.model;

import java.util.Calendar;

public class Consulta {

    private String id, nome, sintomas, data;
    private int telefone;



    @Override
    public String toString() {
        return nome + "\n" + data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

}


