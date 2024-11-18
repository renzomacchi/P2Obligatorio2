/*
    Renzo Macchi
    Santiago Claveré
*/
package Dominio;

import java.io.File;

public class Libro {
    private Editorial editorial;
    private Genero genero;
    private Autor autor;
    private String isbn;
    private String titulo;
    private int pCosto;
    private int pVenta;
    private int stock;
    private File imagen;
    
    public Libro(Editorial e,Genero g,Autor a,String is,String t,int pcosto,int pventa,int stock){
        this.editorial = e ;
        this.genero = g;
        this.autor = a;
        this.isbn = is;
        this.titulo = t;
        this.pCosto = pcosto;
        this.pVenta = pventa;
        this.stock = stock;
        //this.imagen = img ;
    } 

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getpCosto() {
        return pCosto;
    }

    public void setpCosto(int pCosto) {
        this.pCosto = pCosto;
    }

    public int getpVenta() {
        return pVenta;
    }

    public void setpVenta(int pVenta) {
        this.pVenta = pVenta;
    }

    public File getImagen() {
        return imagen;
    }

    public void setImagen(File imagen) {
        this.imagen = imagen;
    }
    
    @Override
    public String toString() {
        return this.getTitulo() + " - " + this.getIsbn();
    }
    
    
}


