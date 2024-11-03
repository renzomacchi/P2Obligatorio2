/*
    Renzo Macchi
    Santiago Claveré
*/
package Dominio;

import java.util.HashMap;

class Autor {
    private String nombre;
    private HashMap<String, String> HMGeneros;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public HashMap<String,String> getHMGeneros() {
        return HMGeneros;
    }

    public void addGenero(Genero genero) {
        this.HMGeneros.put(genero.getNombre(), genero.getDesc());
    }
}
