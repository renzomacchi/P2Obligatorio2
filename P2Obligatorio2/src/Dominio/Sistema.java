/*
    Renzo Macchi
    Santiago Claveré
*/
package Dominio;

import Interfaz.Validate;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Sistema extends java.util.Observable implements java.io.Serializable {
    private int IdFactura;
    private ArrayList<Editorial> LEditoriales;
    private ArrayList<Genero> LGeneros;
    private ArrayList<Autor> LAutores;
    private ArrayList<Libro> LLibros;
    private ArrayList<Factura> LFacturas;
    private ArrayList<Genero> LGenerosSeleccionados;

    public Sistema() {
        this.IdFactura = 0;
        this.LEditoriales = new ArrayList<>();
        this.LGeneros = new ArrayList<>();
        this.LAutores = new ArrayList<>();
        this.LLibros = new ArrayList<>();
        this.LFacturas = new ArrayList<>();
        this.LGenerosSeleccionados = new ArrayList<>();
    }
    
    //  SISTEMA
    //--------------------------------------------------------------------------
    public void guardar() throws IOException {
        //Intenta guardar el programa
        ObjectOutputStream out = new ObjectOutputStream(
            new FileOutputStream("sistema")
        );
        out.writeObject(this);
        out.close();
    }
    
    public static Sistema cargar() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(
            new FileInputStream("sistema")
        );
        Sistema modelo = (Sistema)in.readObject();
        in.close();
        return modelo;
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
     * @return Devuelve una Editorial, si no encuentra devuelve una Editorial vacia
     */
    public Editorial getEditorial(String nombre) {
        Editorial busco = new Editorial(nombre);
        int index = this.getLEditoriales().indexOf(busco);
        if (index == -1) {
            busco = new Editorial("");
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
     * Busca un Genero que tenga a {@code nombre} como nombre<br>
     * @param nombre El String es case sensitive
     * @return
     * Devuelve un Genero, si no encuentra devuelve un Genero vacio
     */
    public Genero getGenero(String nombre) {
        Genero busco = new Genero(nombre);
        int index = this.getLGeneros().indexOf(busco);
        if (index == -1) {
            busco = new Genero("");
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
    
    /**
     * Devuelve todos los autores que escriban en algun genero
     * @param gen
     * @return
     */
    public ArrayList<Autor> getLAutoresConGenero(String gen){
        ArrayList<Autor> lista = new ArrayList<>();
        Iterator<Autor> it = this.getLAutores().iterator();
        while(it.hasNext()){
            Autor miAutor = it.next();
            if (miAutor.tieneGenero(this.getGenero(gen))) {
                lista.add(miAutor);
            }
        }
        return lista;
    }
    
    /**
     * Busca un Autor que tenga a {@code nombre} como nombre<br>
     * @param nombre El String es case sensitive
     * @return
     * Devuelve un Autor, si no encuentra devuelve un Autor vacio
     */
    public Autor getAutor(String nombre){
        ArrayList<Genero> l = new ArrayList<>();
        Autor busco = new Autor(nombre);
        int index = this.getLAutores().indexOf(busco);
        if (index == -1) {
            busco = new Autor("");
        } else {
            busco = this.getLAutores().get(index);
        }
        return busco;
    }
    
    //  TODO SOBRE LIBROS
    //--------------------------------------------------------------------------
    public ArrayList<Libro> getLLibros() {
        return LLibros;
    }
    
    /**
     * Devuelve los libros con stock >= 0
     * @return 
     */
    public ArrayList<Libro> getLLibrosConStock() {
        ArrayList<Libro> result = new ArrayList<>();
        Iterator<Libro> it = this.getLLibros().iterator();
        while (it.hasNext()) {
            Libro l = it.next();
            if (l.getStock() > 0) {
                result.add(l);
            }
        }
        return result;
    }

    public void addLibro(Libro libro) {
        this.LLibros.add(libro);
        Collections.sort(this.getLLibros());
        setChanged();
        notifyObservers();
    }
    
    public boolean existeIsbn(String isbn){
        boolean existe = false;
        for(Libro l: this.getLLibros()){
            if(l.getIsbn().equals(isbn) ){
                existe=true;
            }
        }
        return existe;
    }
    
    /**
     * Busca un Libro que tenga a {@code isbn} como isbn<br>
     * @param isbn El String es case sensitive
     * @return
     * Devuelve un Libro, si no encuentra devuelve un Libro vacio
     */
    public Libro getLibro(String isbn) {
        ArrayList<Libro> l = new ArrayList<>();
        Libro busco = new Libro(isbn);
        int index = this.getLLibros().indexOf(busco);
        if (index == -1) {
            busco = new Libro("");
        } else {
            busco = this.getLLibros().get(index);
        }
        return busco;
    }
    
    public void eliminarLibro(Libro l) {
        this.getLLibros().remove(l);
        setChanged();
        notifyObservers();
    }
    
    /**
     * Obtiene todos los libros que cumplan con <br>
     * <code>miLibro.filtro(genero, titulo, autor)</code>
     * @param genero
     * @param titulo
     * @param autor
     * @return 
     * La lista de todos los libros que pasaron el filtro, puede ser vacia.
     */
    public ArrayList<Libro> consultarLibros(String autor, String genero, String titulo) {
        ArrayList<Libro> result = new ArrayList<>();
        Iterator<Libro> it = this.getLLibros().iterator();
        while (it.hasNext()) {
            Libro l = it.next();
            if (l.filtro(autor,genero,titulo)) {
                result.add(l);
            }
        }
        return result;
    }
    
    /**
     * Busca facturas que contenga items de libros con <code>isbn</code>
     * @param isbn
     * @return 
     * Devuelve una lista en el que cada elemento es un detalle de la factura<br>
     * Cada detalle es un String[] y se obtiene de la funcion <code>miFactura.getDetalle(isbn)</code><br>
     * Puede devolver una lista vacia<br>
     * String[] = {Fecha, Cliente, Numero, Cantidad, Precio, Importe}
     */
    
  
    
    public ArrayList<String[]> getLDetalleFacturas(String isbn) {
        ArrayList<String[]> result = new ArrayList<>();
        ItemVenta busqueda = new ItemVenta(new Libro(isbn),0);
        Iterator<Factura> it = this.getLFacturas().iterator();
        while (it.hasNext()) {
            Factura fac = it.next();
            if (fac.existeItem(busqueda)) {
                result.add(fac.getDetalle(busqueda));
            }
        }
        return result;
    }
    
    /**
     * Dado un camino y un isbn tomamos la foto en ese camino y la guardamos con nombre <code>isbn</code>
     * <br>
     * Crea una carpeta "img" local (si no existia) y guarda las imagenes ahi
     * @param path
     * Camino donde se encuentra la foto a copiar
     * @param isbn 
     * Identificador unico de la imagen (Isbn)
     */
    public void guardarImagen(String path,String isbn){
        //Creamos una Carpeta si es que no existe
        File carpeta = new File("img");
        carpeta.mkdirs();
        try {
            //inFile es la foto seleccionada
            File inFile = new File(path);
            //outFile es el camino a la copia de la foto y su nombre (isbn)
            File outFile = new File("img\\"+isbn);
            
            FileInputStream in = new FileInputStream(inFile);
            FileOutputStream out = new FileOutputStream(outFile);
            //Copiamos la foto y la pegamos en nuestra carpeta de img
            int c = in.read();
            //El in.read() va leyendo los bytes de la foto,
            //cuando termina de leer devuelve -1;
            while(c != -1) {
                out.write(c);
                c = in.read();
            }
            in.close();
            out.close();
        }
        catch(IOException e) {
                Validate.mensaje(Validate.FOTO_GUARDADO_ERROR);
                System.err.println(e);
        }
    }

    //  TODO SOBRE FACTURAS
    //--------------------------------------------------------------------------
    public ArrayList<Factura> getLFacturas() {
        return LFacturas;
    }

    public void addFactura(Factura factura) {
        factura.setNum(this.getFacturaID());
        this.LFacturas.add(factura);
        this.sigIdFactura();
        setChanged();
        notifyObservers();
    }
    
    public int getFacturaID(){
        return IdFactura;
    }
    
    /**
     * Aumenta el IdFactura + 1
     */
    public void sigIdFactura() {
        this.IdFactura = this.IdFactura + 1;
    }
    
    /**
     * Busca una Factura que tenga a {@code id} como id<br>
     * @param id El identificador de la factura, id >= 0
     * @return
     * Devuelve una Factura, si no encuentra devuelve una Factura vacia con <code>id = -1</code>
     */
    public Factura getFactura(int id){
        Factura busco = new Factura(id);
        int index = this.getLFacturas().indexOf(busco);
        if (index == -1) {
            busco = new Factura(-1);
        } else {
            busco = this.getLFacturas().get(index);
        }
        return busco;
    }
    
    
    
    public void actualizarStock(Factura f) {
        ArrayList<ItemVenta> items = f.getItems();
        Iterator<ItemVenta> it = items.iterator();
        while (it.hasNext()) {
            ItemVenta iv = it.next();
            Libro l = iv.getLibro();
            int cantidad = iv.getCantidad();
            int nuevoStock = l.getStock() - cantidad;
            if (nuevoStock <= 0) {
                this.getLibro(l.getIsbn()).setStock(0);
            } else {
                this.getLibro(l.getIsbn()).setStock(nuevoStock);
            }
        }
    }
    
    public void eliminarFactura(Factura f) {
        this.getLFacturas().remove(f);
        ArrayList<ItemVenta> items = f.getItems();
        Iterator<ItemVenta> it = items.iterator();
        while (it.hasNext()) {
            ItemVenta iv = it.next();
            Libro l = iv.getLibro();
            int cantidad = iv.getCantidad();
            if (this.existeIsbn(l.getIsbn())) {
                Libro lExistente = this.getLibro(l.getIsbn());
                lExistente.setStock(lExistente.getStock() + cantidad);
            } else {
                this.addLibro(l);
            }
        }
        setChanged();
        notifyObservers();
    }
    
    public boolean existeFactura(int id){
        return this.getLFacturas().contains(new Factura(id));
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
}


