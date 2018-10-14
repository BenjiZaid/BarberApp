package com.benjizaid.myapp.app;

public class WebService {
    private static String BASE_URL = "http://devmovil.qaliwarma.gob.pe/WSBarber/AndroidService.svc/";

    public static String ListarBarberias(int id) {
        return BASE_URL + "barberias/?id=" + id;
    }

    public static String ListarBarberos(int id) {
        return BASE_URL + "barberos/?id=" + id;
    }

    public static String login(String email, String password) {
        return BASE_URL + "login/?vEmail=" + email + "&vPassword=" + password;
    }

    public static String registerUser() {
        return BASE_URL + "insert";
    }

    public static String agregarFavoritoBarbero(int idUsuario,int idBarbero){
        return BASE_URL + "activarbarberofavorito/?IDUsuario="+  idUsuario + "&IDBarbero=" + idBarbero;
    }
    public static String desactivarFavoritoBarbero(int idUsuario,int idBarbero){
        return BASE_URL + "desactivarbarberofavorito/?IDUsuario="+  idUsuario + "&IDBarbero=" + idBarbero;
    }

    public static String agregarFavoritoBarberia(int idUsuario,int idBarberia){
        return BASE_URL + "activarbarberiafavorito/?IDUsuario="+  idUsuario + "&IDBarberia=" + idBarberia;
    }

    public static String desactivarFavoritoBarberia(int idUsuario,int idBarberia){
        return BASE_URL + "desactivarbarberiafavorito/?IDUsuario="+  idUsuario + "&IDBarberia=" + idBarberia;
    }

}
