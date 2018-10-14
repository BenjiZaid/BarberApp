package com.benjizaid.myapp.model;

public class CorteEntity {

    private int id;
    private String vDescripcion;
    private String vFoto;

    public CorteEntity() {
    }

    public int getId() {
        return id;
    }

    public CorteEntity setId(int id) {
        this.id = id;
        return this;
    }

    public String getvDescripcion() {
        return vDescripcion;
    }

    public CorteEntity setvDescripcion(String vDescripcion) {
        this.vDescripcion = vDescripcion;
        return this;
    }

    public String getvFoto() {
        return vFoto;
    }

    public CorteEntity setvFoto(String vFoto) {
        this.vFoto = vFoto;
        return this;
    }
}
