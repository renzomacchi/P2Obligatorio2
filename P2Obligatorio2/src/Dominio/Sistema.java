/*
    Renzo Macchi
    Santiago Claveré
*/
package Dominio;

import java.util.ArrayList;
import java.util.HashMap;

public class Sistema {
    private HashMap<String,String> HMEditoriales;
    private HashMap<String,String> HMGeneros;
    private ArrayList<Autor> lAutores;
    private ArrayList<Libro> lLibros;
    private ArrayList<Venta> lVentas;

    public HashMap<String,String> getHMEditoriales() {
        return HMEditoriales;
    }

    public void addEditorial(Editorial editorial) {
        this.HMEditoriales.put(editorial.getNombre(), editorial.getPais());
    }
    
    public Editorial getEditorial(String nombre) {
        return new Editorial(nombre,this.HMEditoriales.get(nombre));
    }

    public HashMap<String,String> getHMGeneros() {
        return HMGeneros;
    }

    public void addGenero(Genero genero) {
        this.HMGeneros.put(genero.getNombre(), genero.getDesc());
    }

    public ArrayList<Autor> getlAutores() {
        return lAutores;
    }

    public void addAutor(Autor autor) {
        this.lAutores.add(autor);
    }

    public ArrayList<Libro> getlLibros() {
        return lLibros;
    }

    public void addLibro(Libro libro) {
        this.lLibros.add(libro);
    }

    public ArrayList<Venta> getlVentas() {
        return lVentas;
    }

    public void addVenta(Venta venta) {
        this.lVentas.add(venta);
    }
    

    
    
    
    
    
}


