/*
    Renzo Macchi
    Santiago Claveré
*/
package Dominio;

import java.util.*;

public class Sistema extends java.util.Observable {
    private ArrayList<Editorial> LEditoriales;
    private ArrayList<Genero> LGeneros;
    private ArrayList<Autor> LAutores;
    private ArrayList<Libro> LLibros;
    private ArrayList<Venta> LVentas;
    private ArrayList<Genero> LGenerosSeleccionados;

    public Sistema() {
        this.LEditoriales = new ArrayList<>();
        this.LGeneros = new ArrayList<>();
        this.LAutores = new ArrayList<>();
        this.LLibros = new ArrayList<>();
        this.LVentas = new ArrayList<>();
        this.cargarDatos();
    }

    //  TODO SOBRE EDITORIALES
    //--------------------------------------------------------------------------
    public ArrayList<Editorial> getLEditoriales() {
        return this.LEditoriales;
    }

    /**
     * Agrega una Editorial a this.LEditoriales
     * @param editorial 
     */
    public void addEditorial(Editorial editorial) {
        this.getLEditoriales().add(editorial);
        setChanged();
        notifyObservers();
    }
    
    /**
     * Busca una Editorial en this.LEditoriales
     * @param editorial
     * @return True si existe, False si no existe
     */
    public boolean existeEditorial(Editorial editorial) {
        return this.getLEditoriales().contains(editorial);
    }
    
    /**
     * Busca una Editorial que tenga a {@code nombre} como nombre
     * @param nombre El String es case sensitive
     * @return Devuelve una Editorial, si no encuentra devuelve una Editorial nula
     */
    public Editorial getEditorial(String nombre) {
        Editorial busco = new Editorial(nombre,"");
        int index = this.getLEditoriales().indexOf(busco);
        if (index == -1) {
            busco = new Editorial(null,null);
        } else {
            busco = this.getLEditoriales().get(index);
        }
        return busco;
    }

    //  TODO SOBRE GENEROS
    //--------------------------------------------------------------------------
    public ArrayList<Genero> getLGeneros() {
        return LGeneros;
    }
    
    /**
     * Agrega un Genero a this.LGeneros
     * @param genero 
     */
    public void addGenero(Genero genero) {
        this.getLGeneros().add(genero);
        setChanged();
        notifyObservers();
    }
    
    /**
     * Busca un Genero en this.LGeneros
     * @param genero
     * @return True si existe, False si no existe
     */
    public boolean existeGenero(Genero genero) {
        return this.getLGeneros().contains(genero);
    }
    
    /**
     * Busca un Genero que tenga a {@code nombre} como nombre
     * @param nombre El String es case sensitive
     * @return Devuelve un Genero, si no encuentra devuelve un Genero nulo
     */
    public Genero getGenero(String nombre) {
        Genero busco = new Genero(nombre,"");
        int index = this.getLGeneros().indexOf(busco);
        if (index == -1) {
            busco = new Genero(null,null);
        } else {
            busco = this.getLGeneros().get(index);
        }
        return busco;
    }
    
    /**
     * Devuelve un array de Strings conteniendo todos los Generos.toString()
     * @return
     */
    public String[] getLStringGeneros() {
        String[] LStrGeneros = new String[this.getLGeneros().size()];
        Iterator<Genero> it = this.getLGeneros().iterator();
        int i = 0;
        while (it.hasNext()) {
            LStrGeneros[i] = it.next().toString();
            i++;
        }
        return LStrGeneros;
    }

    //  TODO SOBRE AUTORES
    //--------------------------------------------------------------------------
    public ArrayList<Autor> getLAutores() {
        return LAutores;
    }
    

    public void addAutor(Autor autor) {
        this.LAutores.add(autor);
    }
    
    /**
     * Busca un Genero en this.LGeneros
     * @param autor
     * @return True si existe, False si no existe
     */
    public boolean existeAutor(Autor autor){
        return this.getLAutores().contains(autor);  
    }
    
    /**
     * Devuelve un array de Strings conteniendo todos los Generos.toString()
     * @return
     */
    public String[] getLStringAutores() {
        String[] LStrAutores = new String[this.getLAutores().size()];
        Iterator<Autor> it = this.getLAutores().iterator();
        int i = 0;
        while (it.hasNext()) {
            LStrAutores[i] = it.next().toString();
            i++;
        }
        return LStrAutores;
    }
    
    //  TODO SOBRE LIBROS
    //--------------------------------------------------------------------------
    public ArrayList<Libro> getLLibros() {
        return LLibros;
    }

    public void addLibro(Libro libro) {
        this.LLibros.add(libro);
    }

    //  TODO SOBRE VENTAS
    //--------------------------------------------------------------------------
    public ArrayList<Venta> getlVentas() {
        return LVentas;
    }

    public void addVenta(Venta venta) {
        this.LVentas.add(venta);
    }
    
    //  TODO SOBRE GENEROS SELECCIONADOS
    //--------------------------------------------------------------------------
    public ArrayList<Genero> getlGenerosSeleccionados() {
        return this.LGenerosSeleccionados;
    }
    
    public void addGeneroSeleccionado(String nomGenero) {
        this.getlGenerosSeleccionados().add(this.getGenero(nomGenero));
    }
    
    public ArrayList<Genero> getGenerosNoSeleccionados() {
        return new ArrayList<Genero>();
    }
    
    //  BORRAR LO DE ABAJO ANTES DE ENTREGAR
    //--------------------------------------------------------------------------
    
    //1. Todas las ventanas de registrar autores tienen linkeadas
    //      la seleccion de generos
    
    private void cargarDatos() {
        //BORRAR esta funcion antes de entregar
        this.LEditoriales.add(new Editorial("e1","Uy"));
        this.LEditoriales.add(new Editorial("e2","ar"));
        this.LEditoriales.add(new Editorial("e3","mex"));
        this.LEditoriales.add(new Editorial("e4","us"));
        this.LGeneros.add(new Genero("g","salsa"));
    }
}


