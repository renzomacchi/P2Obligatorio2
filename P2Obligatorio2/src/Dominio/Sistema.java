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
        this.LGenerosSeleccionados = new ArrayList<>();
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
    

    //  TODO SOBRE AUTORES
    //--------------------------------------------------------------------------
    public ArrayList<Autor> getLAutores() {
        return LAutores;
    }
    

    public void addAutor(Autor autor) {
        this.LAutores.add(autor);
        setChanged();
        notifyObservers();
    }
    
    /**
     * Busca un Genero en this.LGeneros
     * @param autor
     * @return True si existe, False si no existe
     */
    public boolean existeAutor(Autor autor){
        return this.getLAutores().contains(autor);  
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
    public ArrayList<Genero> getLGenerosSeleccionados() {
        return this.LGenerosSeleccionados;
    }
    
    public void addGeneroSeleccionado(Genero gen) {
        this.getLGenerosSeleccionados().add(gen);
        setChanged();
        notifyObservers();
    }
    
    public void eliminarGeneroSeleccionado(Genero gen) {
        this.getLGenerosSeleccionados().remove(gen);
        setChanged();
        notifyObservers();
    }
    
    /**
     * Hace la resta del conjunto de generos y el conjunto de generos seleccionados
     * @return LGeneros - LGenerosSeleccionados
     * Puede ser vacio
     */
    public ArrayList<Genero> getLGenerosNoSeleccionados() {
        ArrayList<Genero> noSeleccion = new ArrayList<Genero>();
        Iterator<Genero> it = this.getLGeneros().iterator();
        try {
            while (it.hasNext()) {
            Genero g = it.next();
            if (!this.getLGenerosSeleccionados().contains(g)) {
                noSeleccion.add(g);
                }
            }
        } catch (NullPointerException e) {
            //Si no se ha seleccionado nada entonces devuelve todo.
            noSeleccion = this.getLGeneros();
        }
        
        return noSeleccion;
    }
    
    public void resetLGenerosSeleccionados() {
        this.LGenerosSeleccionados = new ArrayList<Genero>();
    }
    
    //  UTILIDAD
    //--------------------------------------------------------------------------
    /**
     * Devuelve un ArrayList de Strings conteniendo todos los Objetos.toString()
     * @param array
     * @return
     * String[array.size()] conteniendo todos los objectos del array en forma de String
     */
    public static String[] toStringArray(ArrayList<?> array) {
        String[] stringificar = new String[array.size()];
        Iterator<?> it = array.iterator();
        int i = 0;
        while (it.hasNext()) {
            stringificar[i] = it.next().toString();
            i++;
        }
        return stringificar;
    }
    
    
    //  BORRAR LO DE ABAJO ANTES DE ENTREGAR
    //--------------------------------------------------------------------------
    
    //1. Todas las ventanas de registrar autores tienen linkeadas
    //   la seleccion de generos
    //2. Se pueden seleccionar generos iguales en registro de autores
    
    private void cargarDatos() {
        //BORRAR esta funcion antes de entregar
        this.LEditoriales.add(new Editorial("e1","Uruguay"));
        this.LEditoriales.add(new Editorial("e2","Argentina"));
        this.LEditoriales.add(new Editorial("e3","Mexico"));
        this.LEditoriales.add(new Editorial("e4","USA"));
        this.LGeneros.add(new Genero("Accion","mucha accion :O"));
        this.LGeneros.add(new Genero("Terror","santiago es gay"));
        this.LGeneros.add(new Genero("Aventura","wabiwabo"));
    }
}


