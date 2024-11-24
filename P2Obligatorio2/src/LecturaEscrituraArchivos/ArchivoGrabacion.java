
package LecturaEscrituraArchivos;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;

public class ArchivoGrabacion {
    private Formatter out;
    
    public ArchivoGrabacion(String unNombre) {
        try {
            this.out = new Formatter(unNombre);
        } catch (FileNotFoundException e) {
            
        }
    }
    
    public ArchivoGrabacion(String unNombre, boolean ext) {
        try {
            FileWriter f = new FileWriter(unNombre,ext);
            this.out = new Formatter(f);
        } catch (IOException e) {
            
        }
    }
    
    public void grabarLinea(String linea) {
        this.out.format("%s%n", linea);
    }
    
    public void cerrar() {
        this.out.close();
    }
}
