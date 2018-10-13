package com.benjizaid.myapp.model;

import java.io.Serializable;

public class BarberosEntity implements Serializable {

    private int IDBarberia;
    private boolean bActivo;
    private int id;
    private String vDescripcion;
    private String vEmail;
    private String vFoto;
    private String vName;
    private String vTelefono;

    public BarberosEntity() {
    }

    public int getIDBarberia() {
        return IDBarberia;
    }

    public BarberosEntity setIDBarberia(int IDBarberia) {
        this.IDBarberia = IDBarberia;
        return this;
    }

    public boolean isbActivo() {
        return bActivo;
    }

    public BarberosEntity setbActivo(boolean bActivo) {
        this.bActivo = bActivo;
        return this;
    }

    public int getId() {
        return id;
    }

    public BarberosEntity setId(int id) {
        this.id = id;
        return this;
    }

    public String getvDescripcion() {
        return vDescripcion;
    }

    public BarberosEntity setvDescripcion(String vDescripcion) {
        this.vDescripcion = vDescripcion;
        return this;
    }

    public String getvEmail() {
        return vEmail;
    }

    public BarberosEntity setvEmail(String vEmail) {
        this.vEmail = vEmail;
        return this;
    }

    public String getvFoto() {
        return vFoto;
    }

    public BarberosEntity setvFoto(String vFoto) {
        this.vFoto = vFoto;
        return this;
    }

    public String getvName() {
        return vName;
    }

    public BarberosEntity setvName(String vName) {
        this.vName = vName;
        return this;
    }

    public String getvTelefono() {
        return vTelefono;
    }

    public BarberosEntity setvTelefono(String vTelefono) {
        this.vTelefono = vTelefono;
        return this;
    }
}
