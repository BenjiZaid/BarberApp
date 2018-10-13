package com.benjizaid.myapp.app;

public class WebService {
    private static String BASE_URL = "http://devmovil.qaliwarma.gob.pe/WSBarber/AndroidService.svc/";

    public static String ListarBarberias(){
        return BASE_URL + "barberias";
    }

    public static String ListarBarberos(){
        return BASE_URL + "barberos" ;
    }
}
