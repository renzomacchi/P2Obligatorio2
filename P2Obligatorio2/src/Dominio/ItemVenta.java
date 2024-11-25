/*
    Renzo Macchi
    Santiago Claveré
*/
package Dominio;

public class ItemVenta implements Comparable<ItemVenta>, java.io.Serializable {
    private Libro libro;
    private int cantidad;

    public ItemVenta(Libro libro, int cantidad) {
        this.libro = libro;
        this.cantidad = cantidad;
    }
    
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }
    
    @Override
    public boolean equals(Object o) {
        ItemVenta item = (ItemVenta)o;
        return this.getLibro().equals(item.getLibro());
    }
    
    @Override
    public String toString() {
        return this.getCantidad() + " - " + this.getLibro().getTitulo() + " - $ " + this.getLibro().getpVenta();
    }
    
    @Override
    public int compareTo(ItemVenta item) {
        int dif = this.getLibro().compareTo(item.getLibro());
        return dif;
    }
}
