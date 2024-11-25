/*
    Renzo Macchi
    Santiago Claveré
*/
package Dominio;

public class Editorial implements java.io.Serializable{
    private String nombre;
    private String pais;
    
    public Editorial(String nombre, String pais) {
        this.nombre = nombre;
        this.pais = pais;
    }
    
    public Editorial(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
    
    @Override
    public boolean equals(Object o) {
        Editorial e = (Editorial)o;
        return this.getNombre().equals(e.getNombre());
    }
    
    @Override
    public String toString() {
        return this.getNombre() + " - " + this.getPais();
    }
}
