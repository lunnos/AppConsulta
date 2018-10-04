package org.neocities.dolphintech.appconsulta.model;


public class Consulta {

    private String id, nome, sintomas, data, telefone, idUsuario;

        @Override
    public String toString() {
        return nome + "\n" + data;
    }

    public String getData() { return data; }

    public void setData(String data) { this.data = data; }

    public String getId() {
        return id;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() { return nome; }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}


