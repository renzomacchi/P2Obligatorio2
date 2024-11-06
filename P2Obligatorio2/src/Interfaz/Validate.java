/*
    Renzo Macchi Gay
    Santiago Claveré
*/
package Interfaz;

/**
 * Una clase que hace checks de validacion a
 * diferentes componentes que requieren inputs del usuario
 * Tambien contiene mensajes de feedback en variables estaticas.
 */
public abstract class Validate {

    public final static String TXT_VACIO = "Debe llenar el formulario";
    
    /**
     * Dado un array de string, checkea que cada string no este vacio.
     * @param campos
     * @return 
     */
    public static boolean sonTxtVacios(String[] campos) {
        boolean vacios = true;
        for(int i = 0; i < campos.length && vacios; i++) {
            vacios = esTxtVacio(campos[i]);
        }
        return vacios;
    }
    
    /**
     * Dado un string devuelve si esta vacio (o con espacios)
     * @param campo
     * @return 
     */
    public static boolean esTxtVacio(String campo) {
        return campo.trim().equals("");
    }
}
