/*
    Renzo Macchi
    Santiago Claveré
*/
package Dominio;

import java.util.ArrayList;

public class Autor {
    private String nombre;
    private String nacionalidad;
    private ArrayList<Genero> generos;
    
    public Autor(String nom, String nac, ArrayList<Genero> generos) {
        this.nombre = nom;
        this.nacionalidad = nac;
        this.generos = generos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public ArrayList<Genero> getGeneros() {
        return generos;
    }

    public void addGeneros(Genero genero) {
        this.generos.add(genero);
    }
    
    @Override
    public boolean equals(Object o){
        Autor a = (Autor)o;
        return (this.getNombre().equals(a.getNombre()));
    }
    
    @Override
    public String toString() {
        return this.getNombre() + " - " + this.getNacionalidad();
    }
}
