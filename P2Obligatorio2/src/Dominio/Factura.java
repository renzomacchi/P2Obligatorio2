/*
    Renzo Macchi
    Santiago Claveré
*/
package Dominio;

import java.util.ArrayList;
import java.util.Collections;

public class Factura {
    private static int ID = 0;
    
    private int num;
    private String cliente;
    private String fecha;
    private ArrayList<ItemVenta> items;
    
    public Factura(String cliente, String fecha, ArrayList<ItemVenta> items) {
        this.num = ID;
        this.cliente = cliente;
        this.fecha = fecha;
        this.items = items;
    }
    
    public Factura() {
        this.num = -1;
        this.cliente = "";
        this.fecha = "";
        this.items = new ArrayList<ItemVenta>();
    }
    
    /**
     * Aumenta el ID y lo devuelve
     * @return 
     * <code>ID+1</code>
     */
    public static int siguienteID() {
        ID++;
        return ID;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public ArrayList<ItemVenta> getItems() {
        return items;
    }
    
    public ItemVenta getItem(ItemVenta item) {
        return this.getItems().get(this.getItems().indexOf(item));
    }

    /**
     * Agrega un item a esta factura, si ya existia, aumenta en cantidad
     * @param item 
     */
    public void addItem(ItemVenta item) {
        if (!existeItem(item)) {
            this.getItems().add(item);
            Collections.sort(this.getItems());
        } else {
            ItemVenta aux = this.getItem(item);
            aux.setCantidad(aux.getCantidad()+1);
        }
        
    }
    
    public boolean existeItem(ItemVenta item) {
        return this.getItems().contains(item);
    }
    
    
}
