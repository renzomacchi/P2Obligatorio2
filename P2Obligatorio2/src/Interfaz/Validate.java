/*
    Renzo Macchi Gay
    Santiago Claveré
*/
package Interfaz;

import javax.swing.JOptionPane;

/**
 * Una clase que hace checks de validacion a
 * diferentes componentes que requieren inputs del usuario
 * Tambien contiene mensajes de feedback en variables estaticas.
 */
public abstract class Validate {
    //Mensajes de error por mal input del usuario
    public final static String TXT_VACIO = "Debe llenar el formulario";
    public final static String EDITORIAL_REPETIDO = "Ya se ha ingresado una Editorial con ese nombre";
    public final static String GENERO_REPETIDO = "Ya se ha ingresado un Genero con ese nombre";
    public final static String GENERO_SELECCION_VACIA = "Seleccione al menos un Genero";
    public final static String GENERO_NO_SELECCIONADO = "Seleccione un Genero";
    public final static String AUTOR_REPETIDO = "Ya se ha ingresado un Autor con ese nombre";
    
    public static void mensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
    
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
