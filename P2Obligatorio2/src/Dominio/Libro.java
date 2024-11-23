/*
    Renzo Macchi
    Santiago Claveré
*/
package Dominio;

import java.io.File;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

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
    
    public Libro(String isbn) {
        this.isbn = isbn;
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
    
    /**
     * Devuelve la imagen de este libro, si es que existe
     * @return 
     * Imagen del libro, sino devuelve null.
     */
    public ImageIcon getImagen(){
        ImageIcon img = null;
        String extension = "";
        //Buscamos la foto. Recorremos las posibles extensiones
        for (int i = 0; i < ImageIO.getReaderFileSuffixes().length && extension.isEmpty(); i++) {
            String ext = ImageIO.getReaderFileSuffixes()[i].toLowerCase().trim();
            File f = new File("img/" + this.getIsbn() + "." + ext);
            if (f.exists()) {
                extension = ImageIO.getReaderFileSuffixes()[i];
            }
        }
        if (!extension.isEmpty()) {
            img = new ImageIcon("img/" + this.getIsbn()+"." + extension);
        }
        return img;
    }
    
    /**
     * PARA TODOS LOS PARAMETROS: Ignora mayusculas y espacios en blanco<code>.toLowerCase().trim()</code><br>
     * Si este libro cumple al menos una de las siguientes condiciones:
     * @param autor Si el "this autor nombre" contiene al parametro no vacio
     * @param genero Si el "this genero nombre" contiene al parametro no vacio
     * @param titulo Si el "this titulo" contiene al parametro no vacio
     * @return
     * True si cumple con 1, 2 o las 3 condiciones
     */
    public boolean filtro(String autor, String genero, String titulo) {
        boolean result = false;
        if (
                (
                    !autor.isBlank() &&
                    this.getAutor().getNombre().toLowerCase().trim().contains(autor.toLowerCase().trim())
                ) ||
                (
                    !genero.isBlank() &&
                    this.getGenero().getNombre().toLowerCase().trim().contains(genero.toLowerCase().trim())
                ) ||
                (
                    !titulo.isBlank() && 
                    this.getTitulo().toLowerCase().trim().contains(titulo.toLowerCase().trim())
                )
           ) 
        {
            result = true;
        }
        return result;
    }
    
    @Override
    public String toString() {
        return this.getStock() + " - " + this.getIsbn() + " - " + this.getTitulo();
    }
    
    @Override
    public int compareTo(Libro l) {
        int dif = this.getTitulo().toLowerCase().compareTo(l.getTitulo().toLowerCase());
        if (dif == 0) {
            dif = this.getIsbn().compareTo(l.getIsbn());
        }
        return dif;
    }
    
    @Override
    public boolean equals(Object o) {
        Libro l = (Libro)o;
        return this.getIsbn().equals(l.getIsbn());
    }
    
    
}


