package cl.dsy1102.fonda;
import cl.dsy1102.fonda.model.Bebida;

import java.util.ArrayList;
import java.util.List;

public class GestorFonda {
    //Coleccion Dinamica para guardar cualquier objeto que sea una 'Bebida'
    private List<Bebida> bebidas;

    public GestorFonda(){
        this.bebidas= new ArrayList<>();
    }

    //1. Agregar bebida a la lista
    public void registrar(Bebida bebida){
        bebidas.add(bebida);
        System.out.println(bebida.getNombre()+" ("+bebida.getClass().getSimpleName()+") registrada correctamente.");
    }

    //2. Buscar bebidas por nombre
    public List<Bebida> buscarPorNombre(String nombre){
        List<Bebida> encontradas = new ArrayList<>();
        for (Bebida b : bebidas){
            if (b.getNombre().equalsIgnoreCase(nombre)){
                encontradas.add(b);
            }
        }
        return encontradas;
    }

    //3. Procesar venta evaluando el contrato de ConsumoResponsable
    public void vender(String nombre, int unidades){
        for (Bebida b : bebidas){
            if (b.getNombre().equalsIgnoreCase(nombre)){

                //La bebida implementa contrato de consumo responsable?
                if (b instanceof ConsumoResponsable){

                    //Convertimos la referencia para usar el contrato
                    ConsumoResponsable cr = (ConsumoResponsable) b;
                    if (cr.tieneVentaRestringida()){
                        System.out.println("Venta Rechazada: "+nombre+" tiene la venta restringida.");
                        return;
                    }
                    if (cr.superaLimite(unidades)){
                        System.out.println("Venta Rechazada: "+unidades+" unidades de "+nombre+" superan el limite de "+BebidaAlcoholica.LIMITE_UNIDADES_POR_CLIENTE+" por cliente.");
                        return;
                    }
                }

                //Si pasa los controles de alcohol, verificamos el stock
                if (b.getStock()>= unidades){

                    //Descontamos stock
                    b.setStock(b.getStock()-unidades);
                    int total = (int) (b.calcularPrecio()* unidades);
                    System.out.println("Venta Autorizada: "+unidades+" x "+nombre+" | Total: $"+total);
                    return;
                }else{
                    System.out.println("Venta Rechazada: Stock insuficiente para "+nombre+".");
                    return;
                }
            }
        }
        System.out.println("Venta Rechazada: Bebida no encontrada ("+nombre+").");
    }
    public List<Bebida> obtenerTodas(){
        return bebidas;
    }

}
