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
    
    public Autor getAutor(String nombre){
        ArrayList<Genero> l = new ArrayList<>();
        Autor busco = new Autor(nombre,"",l);
        int index = this.getLAutores().indexOf(busco);
        if (index == -1) {
            busco = new Autor(null,null,l);
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
    
    public boolean existeTitulo(String titulo){
        boolean existe = false;
        for(Libro l: this.getLLibros()){
            if(l.getTitulo().equals(titulo) ){
                existe=true;
            }
        }
        return existe;
    }
    
    /**
     * Dado un camino y un isbn tomamos la foto en ese camino y la guardamos con nombre <code>isbn</code>
     * <br>
     * Crea una carpeta "img" local (si no existia) y guarda las imagenes ahi
     * @param path
     * Camino donde se encuentra la foto
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
        this.LFacturas.add(factura);
    }
    
    public Factura getFactura(String id){
        boolean encontrado = false;
        Iterator<Factura> it = this.getLFacturas().iterator();
        Factura f = new Factura();
        while(it.hasNext()&&!encontrado){
            f = it.next();
            if(f.getNum()==Integer.parseInt(id)){
                encontrado = true;
            }
        }
        return f;
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
    
    public String[] nomeandaeltoarray(ArrayList<ItemVenta> a){
        String[] array=new String[a.size()];
        int pos=0;
        for(ItemVenta c: a){
            array[pos]=c.toString();
        }
        return array;
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
        Libro l1 = new Libro("DRSEX","Aviacion 1",e1,g1,a1,1,1,1);
        Libro l6 = new Libro("123LOG","Comecactus",e4,g3,a5,1,1,1);
        Libro l2 = new Libro("KLMNQ","Aviacion 2",e2,g2,a1,1,1,1);
        Libro l4 = new Libro("MANCE2","Bombinomicon",e3,g1,a4,1,1,1);
        Libro l5 = new Libro("3JESUS","Zapatos",e3,g5,a6,1,1,1);
        Libro l3 = new Libro("B1232","Aviacion 3",e3,g1,a1,1,1,1);
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
    }
    
    
    
}


