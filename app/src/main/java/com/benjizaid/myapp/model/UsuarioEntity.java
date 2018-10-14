package com.benjizaid.myapp.model;

public class UsuarioEntity {

    private int id;
    private String vNombres;
    private String vApellidos;

    public UsuarioEntity() {

    }

    public int getId() {
        return id;
    }

    public UsuarioEntity setId(int id) {
        this.id = id;
        return this;
    }

    public String getvNombres() {
        return vNombres;
    }

    public UsuarioEntity setvNombres(String vNombres) {
        this.vNombres = vNombres;
        return this;
    }

    public String getvApellidos() {
        return vApellidos;
    }

    public UsuarioEntity setvApellidos(String vApellidos) {
        this.vApellidos = vApellidos;
        return this;
    }

}
