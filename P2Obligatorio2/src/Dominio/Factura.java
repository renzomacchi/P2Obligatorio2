/*
    Renzo Macchi
    Santiago Claveré
*/
package Dominio;

import java.util.ArrayList;
import java.util.Collections;

public class Factura implements java.io.Serializable {
    private int num;
    private String cliente;
    private String fecha;
    private ArrayList<ItemVenta> items;
    
    public Factura(int id, String cliente, String fecha, ArrayList<ItemVenta> items) {
        this.num = id;
        this.cliente = cliente;
        this.fecha = fecha;
        this.items = items;
    }
    
    //Constructor de busqueda
    public Factura(int id) {
        this.num = id;
        this.cliente = "";
        this.fecha = "";
        this.items = new ArrayList<>();
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
    
    /**
     * Dado un item, devuelve el itemVenta de una factura,
     * @param item
     * @return 
     * Devuelve el item, si no existe deuvelve un item vacio y cantidad -1
     */
    public ItemVenta getItem(ItemVenta item) {
        int index = this.getItems().indexOf(item);
        ItemVenta result = new ItemVenta(null,-1);
        if (index != -1) {
            result = this.getItems().get(index);
        }
        return result;
    }
    
    /**
     * Dado un Item devuelve informacion sobre el item en esa factura<br>
     * @param item
     * @return Datos:
     * <ol>
     * <li>Fecha</li>
     * <li>Cliente</li>
     * <li>Numero (ID factura)</li>
     * <li>Cantidad del Item en esta factura</li>
     * <li>Precio por unidad del item (libro) en esta factura</li>
     * <li>Precio total del item (libro*cantidad) en esta factura</li>
     * </ol>
     */
    public String[] getDetalle(ItemVenta item) {
        ItemVenta iv = this.getItem(item);
        String[] detalle = {
            this.getFecha(),
            this.getCliente(),
            this.getNum()+"",
            iv.getCantidad()+"",
            iv.getLibro().getpVenta()+"",
            iv.getCantidad()*iv.getLibro().getpVenta()+""
        };
        return detalle;
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
    
    /**
     * Saca un item de esta factura, si hay mas de uno, disminuye en cantidad
     * @param item 
     */
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
                t+=i.getLibro().getStock()*i.getLibro().getpVenta();
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
    
    @Override
    public boolean equals(Object o) {
        Factura f = (Factura)o;
        return this.getNum() == f.getNum();
    }
    
    
}
