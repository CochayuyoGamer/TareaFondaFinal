package cl.dsy1102.fonda; //indica en que carpeta esta guardado el archivo

public interface ConsumoResponsable {

    //1. pregunta si la bebida tiene prohibida la venta
    boolean tieneVentaRestringida();

    //2. Bloquea la venta de la bebida
    void restringirVenta();

    //3. Evalua si la cantidad solicitada supera el limite permitido
    boolean superaLimite(int unidades);
}
