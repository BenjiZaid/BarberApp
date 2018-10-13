package com.benjizaid.myapp.model;

import java.io.Serializable;

public class BarberiaEntity implements Serializable {

    private boolean bActivo;
    private int id;
    private String vDescripcion;
    private String vDireccion;
    private String vEmail;
    private String vFoto;
    private Double vLatitud;
    private Double vLongitud;
    private String vName;
    private String vTelefono;

    public BarberiaEntity() {
    }

    public boolean isbActivo() {
        return bActivo;
    }

    public BarberiaEntity setbActivo(boolean bActivo) {
        this.bActivo = bActivo;
        return this;
    }

    public int getId() {
        return id;
    }

    public BarberiaEntity setId(int id) {
        this.id = id;
        return this;
    }

    public String getvDescripcion() {
        return vDescripcion;
    }

    public BarberiaEntity setvDescripcion(String vDescripcion) {
        this.vDescripcion = vDescripcion;
        return this;
    }

    public String getvDireccion() {
        return vDireccion;
    }

    public BarberiaEntity setvDireccion(String vDireccion) {
        this.vDireccion = vDireccion;
        return this;
    }

    public String getvEmail() {
        return vEmail;
    }

    public BarberiaEntity setvEmail(String vEmail) {
        this.vEmail = vEmail;
        return this;
    }

    public String getvFoto() {
        return vFoto;
    }

    public BarberiaEntity setvFoto(String vFoto) {
        this.vFoto = vFoto;
        return this;
    }

    public Double getvLatitud() {
        return vLatitud;
    }

    public BarberiaEntity setvLatitud(Double vLatitud) {
        this.vLatitud = vLatitud;
        return this;
    }

    public Double getvLongitud() {
        return vLongitud;
    }

    public BarberiaEntity setvLongitud(Double vLongitud) {
        this.vLongitud = vLongitud;
        return this;
    }

    public String getvName() {
        return vName;
    }

    public BarberiaEntity setvName(String vName) {
        this.vName = vName;
        return this;
    }

    public String getvTelefono() {
        return vTelefono;
    }

    public BarberiaEntity setvTelefono(String vTelefono) {
        this.vTelefono = vTelefono;
        return this;
    }
}
