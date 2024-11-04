/*
    Renzo Macchi
    Santiago Claveré
*/
package Dominio;

import java.util.*;

public class Sistema extends java.util.Observable {
    private HashMap<String,String> HMEditoriales;
    private HashMap<String,String> HMGeneros;
    private ArrayList<Autor> lAutores;
    private ArrayList<Libro> lLibros;
    private ArrayList<Venta> lVentas;

    public Sistema() {
        this.HMEditoriales = new HashMap<String,String>();
        this.HMGeneros = new HashMap<String,String>();
        this.lAutores = new ArrayList<Autor>();
        this.lLibros = new ArrayList<Libro>();
        this.lVentas = new ArrayList<Venta>();
        this.cargarDatos();
    }

    public HashMap<String,String> getHMEditoriales() {
        return HMEditoriales;
    }
    
    /**
     * Devuelve un array list conteniendo todos los objetos editoriales
     * @return 
    */
    public ArrayList<Editorial> getArrayEditoriales() {
        ArrayList<Editorial> lEditorial = new ArrayList<Editorial>();
        Iterator<String> it = this.getHMEditoriales().keySet().iterator();
        while (it.hasNext()) {
            String nombre = it.next();
            String pais = this.getHMEditoriales().get(nombre);
            lEditorial.add(new Editorial(nombre,pais));
        }
        return lEditorial;
    }

    public void addEditorial(Editorial editorial) {
        this.HMEditoriales.put(editorial.getNombre(), editorial.getPais());
        setChanged();
        notifyObservers();
    }
    
    public Editorial getEditorial(String nombre) {
        return new Editorial(nombre,this.HMEditoriales.get(nombre));
    }

    public HashMap<String,String> getHMGeneros() {
        return HMGeneros;
    }
    /**
     * Devuelve un array de Strings conteniendo todos los Generos.toString()
     * Por ejemplo {"Accion - mucha accion", "Horror - que miedo"}
     * @return 
    */
    public String[] getStrArrayGeneros() {
        String[] aGenero = new String[this.getHMGeneros().size()];
        Iterator<String> it = this.getHMGeneros().keySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            String nombre = it.next();
            String desc = this.getHMGeneros().get(nombre);
            aGenero[i] = new Genero(nombre, desc).toString();
            i++;
        }
        return aGenero;
    }

    public void addGenero(Genero genero) {
        this.HMGeneros.put(genero.getNombre(), genero.getDesc());
        setChanged();
        notifyObservers();
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
    
    private void cargarDatos() {
        //BORRAR esta funcion antes de entregar
        this.HMEditoriales.put("e1","Uy");
        this.HMEditoriales.put("e2","ar");
        this.HMEditoriales.put("e3","mex");
        this.HMEditoriales.put("e4","us");
    }
    
    
    
    
    
}


