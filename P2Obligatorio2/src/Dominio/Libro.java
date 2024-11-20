/*
    Renzo Macchi
    Santiago Claveré
*/
package Dominio;

import java.io.File;

public class Libro implements Comparable<Libro> {
    private String isbn;
    private String titulo;
    private Editorial editorial;
    private Genero genero;
    private Autor autor;
    private int pCosto;
    private int pVenta;
    private int stock;
    
    public Libro(String isbn,String titulo,Editorial e,Genero g,Autor a,int pcosto,int pventa,int stock){
        this.titulo = titulo;
        this.isbn = isbn;
        this.editorial = e ;
        this.genero = g;
        this.autor = a;
        this.pCosto = pcosto;
        this.pVenta = pventa;
        this.stock = stock;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    @Override
    public String toString() {
        return this.getIsbn() + " - " + this.getTitulo();
    }
    
    @Override
    public int compareTo(Libro l) {
        int dif = this.getTitulo().toLowerCase().compareTo(l.getTitulo().toLowerCase());
        if (dif == 0) {
            dif = this.getIsbn().compareTo(l.getIsbn());
        }
        return dif;
    }
    
    
}


