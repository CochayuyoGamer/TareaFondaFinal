package cl.dsy1102.fonda;

public class BebidaAlcoholica extends Bebida implements ConsumoResponsable{
    //Constante de clase: El limite de 3 unidades es fijo y compartido por todas las bebidas alcoholicas
    public static final int LIMITE_UNIDADES_POR_CLIENTE = 3;

    private double gradosAlcohol;
    private boolean certificada;
    private boolean ventaRestringida;

    //Constructor que recibe los datos propios y los de la superclase
    public BebidaAlcoholica(String nombre, int volumenML, int stock, double gradosAlcohol, boolean certificada){
        //Super: envia nombre, volumen y stock al constructor de 'Bebida'
        super(nombre, volumenML, stock);

        setGradosAlcohol(gradosAlcohol);
        setCertificada(certificada);

        //por defecto la venta inicia sin restriccion
        this.ventaRestringida= false;
    }

    public double getGradosAlcohol() {
        return gradosAlcohol;
    }

    public void setGradosAlcohol(double gradosAlcohol) {
        if (gradosAlcohol < 0.5 || gradosAlcohol > 45.0){
            throw new IllegalArgumentException("Los grados de alcohol deben estar entre 0.5° y 45°");
        }
        this.gradosAlcohol=gradosAlcohol;
    }

    public boolean isCertificada() {
        return certificada;
    }

    public void setCertificada(boolean certificada) {
        this.certificada = certificada;
    }

    //Implementacion ConsumoResponsable
    @Override
    public boolean tieneVentaRestringida(){
        //Retorna la variable del objeto (sin parentesis!!!)
        return ventaRestringida;
    }

    @Override
    public void restringirVenta(){
        //Activa el bloqueo de venta
        this.ventaRestringida=true;
    }

    @Override
    public boolean superaLimite(int unidades){
        //devuelve true si pides mas de 3 bebidas alcoholicas
        return unidades > LIMITE_UNIDADES_POR_CLIENTE;
    }

    //calculo de precio especifico
    @Override
    public double calcularPrecio(){
        double precioBase = 3500;
        //si no esta certificada por el proveedor
        if (!certificada){
            //Recargo del 20%
            precioBase *= 1.20;
        }
        return precioBase;
    }

    @Override
    public String obtenerDetalle(){
        return "Tipo: Bebida Alcoholica | Nombre: "+ getNombre()+" | Volumen: "+getVolumenML()+" ml | Stock: "+getStock()+" | Grados: "+gradosAlcohol+" |Certificada: "+(certificada? "Si" : "No")+"\n Venta Restringida: "+(ventaRestringida? "Si" : "No")+" | Precio: $"+(int)calcularPrecio();
    }
}
