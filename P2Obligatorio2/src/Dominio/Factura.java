/*
    Renzo Macchi
    Santiago Claveré
*/
package Dominio;

import java.util.ArrayList;
import java.util.Collections;

public class Factura {
    public static int ID = 0;
    
    private int num;
    private String cliente;
    private String fecha;
    private ArrayList<ItemVenta> items;
    private int total;
    
    public Factura(String cliente, String fecha, ArrayList<ItemVenta> items,int total) {
        this.num = ID;
        this.cliente = cliente;
        this.fecha = fecha;
        this.items = items;
        this.total= total;
    }
    
    public Factura() {
        this.num = -1;
        this.cliente = "";
        this.fecha = "";
        this.items = new ArrayList<ItemVenta>();
        this.total=0;
    }
    
    
    public int getID(){
        return this.ID;
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
    
    
    public void setTotal(int n){
        this.total= n;
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
    
    public void eliminarItem(ItemVenta item){
        if(item.getCantidad()==1){
            this.getItems().remove(item);
        }
        else{
            ItemVenta aux = this.getItem(item);
            aux.setCantidad(aux.getCantidad()-1);
        }
    }
    
    public boolean existeItem(ItemVenta item) {
        return this.getItems().contains(item);
    }
    
    public int saberTotal(){
        int t = 0;
        for(ItemVenta i: this.items){
            t+=i.getLibro().getpVenta()*i.getCantidad();
        }
        return t;
    }
    
    public int totalPosta(){
        int t= 0;
        for(ItemVenta i: this.items){
            if(i.getCantidad()>i.getLibro().getStock()){
                t+=i.getLibro().getStock();
            }
            else{
            t+=i.getLibro().getpVenta()*i.getCantidad();
            }
        }
        return t;
    }
    
    @Override
    public String toString() {
        return this.cliente+" - "+this.fecha+" - "+this.getNum();
    }
    
    
}
