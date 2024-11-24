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
import LecturaEscrituraArchivos.*;

public class Sistema extends java.util.Observable {
    private ArrayList<Editorial> LEditoriales;
    private ArrayList<Genero> LGeneros;
    private ArrayList<Autor> LAutores;
    private ArrayList<Libro> LLibros;
    private ArrayList<Factura> LFacturas;
    private ArrayList<Genero> LGenerosSeleccionados;

    public Sistema() {
        this.LEditoriales = new ArrayList<>();
        this.LGeneros = new ArrayList<>();
        this.LAutores = new ArrayList<>();
        this.LLibros = new ArrayList<>();
        this.LFacturas = new ArrayList<>();
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
                System.out.println(Arrays.toString(fac.getDetalle(busqueda)));
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
                Validate.mensaje("Hubo un error al intentar guardar la foto.");
                System.err.println(e);
        }
    }

    //  TODO SOBRE FACTURAS
    //--------------------------------------------------------------------------
    public ArrayList<Factura> getLFacturas() {
        return LFacturas;
    }

    public void addFactura(Factura factura) {
        factura.setNum(Factura.getID());
        this.LFacturas.add(factura);
        Factura.siguienteID();
        setChanged();
        notifyObservers();
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
                this.eliminarLibro(l);
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
    
    //  BORRAR LO DE ABAJO ANTES DE ENTREGAR
    //--------------------------------------------------------------------------
    
    //1. El archivo de ventas lo guarda donde el usuario quiera
    
    private void cargarDatos() {
        //BORRAR esta funcion antes de entregar
        Editorial e1 = new Editorial("Editorial extraplana","Uruguay");
        Editorial e2 = new Editorial("Editorial inflada","Argentina");
        Editorial e3 = new Editorial("Editorial amurallada","Mexico");
        Editorial e4 = new Editorial("Editorial libre","USA");
        Genero g1 = new Genero("Accion","mucha accion :O");
        Genero g2 = new Genero("Terror","santiago es gay");
        Genero g3 = new Genero("Aventura","wabiwabo");
        Genero g4 = new Genero("Empty","ejemplo sin Autores");
        Genero g5 = new Genero("Mafia Boss lvl99","you should kys rn.");
        ArrayList<Genero> gs1_2 = new ArrayList<>();
        gs1_2.add(g1);
        gs1_2.add(g2);
        ArrayList<Genero> gs2_3 = new ArrayList<>();
        gs2_3.add(g2);
        gs2_3.add(g3);
        ArrayList<Genero> gs5 = new ArrayList<>();
        gs5.add(g5);
        ArrayList<Genero> gs1_2_3_5 = new ArrayList<>();
        gs1_2_3_5.add(g1);
        gs1_2_3_5.add(g2);
        gs1_2_3_5.add(g3);
        gs1_2_3_5.add(g5);
        Autor a1 = new Autor("Rhoi verokai","Prussia",gs1_2);
        Autor a2 = new Autor("RickRoller23","Guatepeor",gs2_3);
        Autor a3 = new Autor("Bing chilling","Letonia",gs1_2_3_5);
        Autor a4 = new Autor("demoknightTF2","The Greatkeep",gs1_2);
        Autor a5 = new Autor("Dr sex","Fachalandia",gs2_3);
        Autor a6 = new Autor("Leproso","Imperio Aleman",gs1_2_3_5);
        Autor a7 = new Autor("MepicanlosCocos","Jamaica",gs5);
        Libro l1 = new Libro("DRSEX","Aviacion 1",e1,g1,a1,300,222,100);
        Libro l6 = new Libro("123LOG","Comecactus",e4,g3,a5,200,111,100);
        Libro l2 = new Libro("KLMNQ","Aviacion 2",e2,g2,a1,100,99,1000);
        Libro l4 = new Libro("MANCE2","Bombinomicon",e3,g1,a4,rng(),rng(),rng());
        Libro l5 = new Libro("3JESUS","Zapatos",e3,g5,a6,rng(),rng(),rng());
        Libro l3 = new Libro("B1232","Aviacion 3",e3,g1,a1,rng(),rng(),rng());
        ItemVenta iv1 = new ItemVenta(l1,rng());
        ItemVenta iv1_ = new ItemVenta(l1,rng());
        ItemVenta iv1__ = new ItemVenta(l1,rng());
        ItemVenta iv1___ = new ItemVenta(l1,rng());
        ItemVenta iv2 = new ItemVenta(l2,rng());
        ItemVenta iv2_ = new ItemVenta(l2,rng());
        ItemVenta iv3 = new ItemVenta(l3,rng());
        ItemVenta iv3_ = new ItemVenta(l3,rng());
        ItemVenta iv3__ = new ItemVenta(l3,rng());
        ItemVenta iv4 = new ItemVenta(l4,rng());
        ItemVenta iv4_ = new ItemVenta(l4,rng());
        ItemVenta iv4__ = new ItemVenta(l4,rng());
        ItemVenta iv5 = new ItemVenta(l5,rng());
        ItemVenta iv5_ = new ItemVenta(l5,rng());
        ItemVenta iv5__ = new ItemVenta(l5,rng());
        ArrayList<ItemVenta> ivs1_3_5 = new ArrayList<>();
        ivs1_3_5.add(iv1);
        ivs1_3_5.add(iv3);
        ivs1_3_5.add(iv5);
        ArrayList<ItemVenta> ivs1_3_5_ = new ArrayList<>();
        ivs1_3_5_.add(iv1__);
        ivs1_3_5_.add(iv3__);
        ivs1_3_5_.add(iv5__);
        ArrayList<ItemVenta> ivs1_2_4 = new ArrayList<>();
        ivs1_2_4.add(iv1_);
        ivs1_2_4.add(iv2);
        ivs1_2_4.add(iv4);
        ArrayList<ItemVenta> ivs1_2_4_ = new ArrayList<>();
        ivs1_2_4_.add(iv1___);
        ivs1_2_4_.add(iv2_);
        ivs1_2_4_.add(iv4__);
        ArrayList<ItemVenta> ivs3_4_5 = new ArrayList<>();
        ivs3_4_5.add(iv3_);
        ivs3_4_5.add(iv4_);
        ivs3_4_5.add(iv5_);
        Factura f1 = new Factura("Atenta2","11/09/2001",ivs1_3_5);
        Factura f2 = new Factura("Franchesco Virgolini","32/13/2025",ivs1_2_4);
        Factura f3 = new Factura("El pepe","A/y/er",ivs3_4_5);
        Factura f4 = new Factura("Y Messi, Messi, Messi Y viene Messi","f/ra/ca",ivs1_3_5_);
        Factura f5 = new Factura("Flint lockwood","31/02/1985",ivs1_2_4_);
        this.addEditorial(e1);
        this.addEditorial(e2);
        this.addEditorial(e3);
        this.addEditorial(e4);
        this.addGenero(g1);
        this.addGenero(g2);
        this.addGenero(g3);
        this.addGenero(g4);
        this.addGenero(g5);
        this.addAutor(a1);
        this.addAutor(a2);
        this.addAutor(a3);
        this.addAutor(a4);
        this.addAutor(a5);
        this.addAutor(a6);
        this.addAutor(a7);
        this.addLibro(l1);
        this.addLibro(l2);
        this.addLibro(l3);
        this.addLibro(l4);
        this.addLibro(l5);
        this.addLibro(l6);
        this.addFactura(f1);
        this.addFactura(f2);
        this.addFactura(f3);
        this.addFactura(f4);
        this.addFactura(f5);
        System.out.println("Datos por defecto:");
        System.out.println("------------------------");
        System.out.println("Autores:");
        System.out.println(this.LAutores);
        System.out.println("Editoriales:");
        System.out.println(this.LEditoriales);
        System.out.println("Facturas:");
        System.out.println(this.LFacturas);
        System.out.println("Generos:");
        System.out.println(this.LGeneros);
        System.out.println("Libros:");
        System.out.println(this.LLibros);
        System.out.println("------------------------");
    }
    
    public static int rng() {
        return (int)(Math.random()*50+1);
    }
    
    
}


