/*
    Renzo Macchi
    Santiago Claveré
*/
package p2obligatorio2;

import Dominio.Sistema;
import Interfaz.vGestionLibrerias;

public class P2Obligatorio2 {

    public static void main(String[] args) {
        //Mostrar Ventana Principal
        Sistema miSistema = new Sistema();
        vGestionLibrerias gl = new vGestionLibrerias(miSistema);
        gl.setVisible(true);
    }
    
}
