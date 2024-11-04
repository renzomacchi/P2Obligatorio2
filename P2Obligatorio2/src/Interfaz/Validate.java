/*
    Renzo Macchi
    Santiago Claveré
*/
package Interfaz;

public class Validate {
    //Una clase que hace checks de diferentes a componentes que requieren inputs

    public final static String TXT_VACIO = "Debe llenar el formulario";
    
    public static boolean sonTxtVacios(String[] campos) {
        boolean vacios = true;
        for(int i = 0; i < campos.length && vacios; i++) {
            vacios = esTxtVacio(campos[i]);
        }
        return vacios;
    }
    
    public static boolean esTxtVacio(String campo) {
        return campo.equals("");
    }
}
