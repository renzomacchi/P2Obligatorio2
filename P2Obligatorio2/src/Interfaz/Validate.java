/*
    Renzo Macchi
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
    //Mensajes para advertir al usuario
    public final static String AUTOR_NO_SELECCIONADO = "Seleccione un Autor";
    public final static String AUTOR_REPETIDO = "Ya se ha ingresado un Autor con ese nombre";
    public final static String CARGAR_SISTEMA_ERROR = "Hubo un error al cargar los datos";
    public final static String CRITERIO_VACIO = "Debe ingresar al menos un criterio de busqueda.";
    public final static String EDITORIAL_NO_SELECCIONADO = "Seleccione una Editorial";
    public final static String EDITORIAL_REPETIDO = "Ya se ha ingresado una Editorial con ese nombre";
    public final static String FACTURA_SIN_ITEMS = "Por favor ingrese algun libro";
    public final static String GENERO_NO_SELECCIONADO = "Seleccione un Genero";
    public final static String GENERO_REPETIDO = "Ya se ha ingresado un Genero con ese nombre";
    public final static String GENERO_SELECCION_VACIA = "Seleccione al menos un Genero";
    public final static String GUARDAR_SISTEMA_ERROR = "Hubo un error al guardar el sistema";
    public final static String ID_BORRARADO = "Se ha borrado con exito";
    public final static String ID_INVALIDO = "Ingrese un id valido";
    public final static String ID_NO_EXISTE = "No existe ese id";
    public final static String ID_NO_EXISTE_BORRAR = "Busque un ID valido para borrar";
    public final static String ID_VACIO = "Ingrese un id";
    public final static String ISBN_REPETIDO = "Ya se ha ingresado un Libro con ese ISBN";
    public final static String ITEM_VENTA_NO_SELECCIONADO = "Seleccione un item de su carrito";
    public final static String LIBRO_NO_SELECCIONADO = "Seleccione un Libro";
    public final static String LIBRO_REGISTRADO = "Libro registrado correctamente";
    public final static String SISTEMA_GUARDADO_ERROR = "Hubo un error al intentar guardar la foto.";
    public final static String TABLA_EXPORTADA = "Datos exportados con exito";
    public final static String TABLA_VACIA = "No hay datos cargados en la tabla";
    public final static String TXT_NO_NRO_POSITIVO = "Debe ingresar numeros enteros mayores a 0";
    public final static String TXT_VACIO = "Debe llenar el formulario";
    
    public static void mensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
    
    public static boolean darOpcion(String mensaje, String titulo) {
        int result = JOptionPane.showConfirmDialog(null, mensaje, titulo, JOptionPane.YES_NO_OPTION);
        boolean ok = false;
        if (result == 0) {
            ok = true;
        }
        return ok;
    }
    
    /**
     * Dado un array de Strings, checkea que cada string no este vacio.
     * @param campos
     * @return False si uno o mas son vacios
     */
    public static boolean sonTxtVacios(String[] campos) {
        boolean vacios = true;
        for(int i = 0; i < campos.length && vacios; i++) {
            vacios = esTxtVacio(campos[i]);
        }
        return vacios;
    }
    
    /**
     * Dado un String devuelve si esta vacio (o con espacios)
     * @param campo
     * @return 
     */
    public static boolean esTxtVacio(String campo) {
        return campo.isBlank();
    }
    
    /**
     * Dado un String devuelve si es un numero mayor o igual a 0;
     * @param campo
     * @return False si es un String<br>
     * False si es un numero n: <code>n < 0</code>
     */
    public static boolean esEnteroPositivo(String campo) {
        boolean result = true;
        try {
            result = Integer.parseInt(campo) >= 0;
        } catch (NumberFormatException e) {
            result = false;
        }
        return result;
    }
}
