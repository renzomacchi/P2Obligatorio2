/*
    Renzo Macchi
    Santiago Claveré
*/
package Dominio;

public class Genero {
    private String nombre;
    private String desc;

    public Genero(String nombre, String desc) {
        this.nombre = (nombre);
        this.desc =(desc);
    }
    
    public Genero(String nombre) {
        this.nombre = nombre;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    @Override
    public String toString() {
        return this.nombre + " - " + this.desc;
    }
    
    @Override
    public boolean equals(Object o) {
        Genero g = (Genero)o;
        return this.getNombre().equals(g.getNombre());
    }
    
}
