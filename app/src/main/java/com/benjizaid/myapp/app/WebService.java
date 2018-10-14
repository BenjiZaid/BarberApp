package com.benjizaid.myapp.app;

public class WebService {
    private static String BASE_URL = "http://devmovil.qaliwarma.gob.pe/WSBarber/AndroidService.svc/";

    public static String ListarBarberias(int id){
        return BASE_URL + "barberias/?id=" + id;
    }

    public static String ListarBarberos(int id){
        return BASE_URL + "barberos/?id=" + id;
    }

    public static String login(String email,String password){
        return BASE_URL + "login/?vEmail="+ email + "&vPassword=" + password;
    }

    public static String registerUser(){
        return BASE_URL + "insert";
    }

}
