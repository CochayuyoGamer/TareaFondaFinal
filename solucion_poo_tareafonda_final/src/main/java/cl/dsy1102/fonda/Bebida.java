package cl.dsy1102.fonda;

public abstract class Bebida {

    //ENCAPSULAMIENTO: variables de instancia privadas para proteger los datos.
    private String nombre;
    private int volumenML;
    private int stock;

    //CONSTRUCTORES: Se ejecuta automaticamente al crear un nuevo objeto 'new Bebida(...)'
    public Bebida(String nombre, int volumenML, int stock){

        //usamos los metodos setters en lugar de asignacion directa para aplicar las validaciones
        setNombre(nombre);
        setVolumenML(volumenML);
        setStock(stock);
    }

    //GETTERS (para leer los datos)
    //SETTERS (para modificar y validar datos)
    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        //Regla: no puede ser nulo ni estar vacio
        if(nombre==null|| nombre.trim().isEmpty()){
            throw new IllegalArgumentException("El nombre no puede ser nulo ni estar vacio.");
        }
        this.nombre = nombre;
        // 'this.nombre' se refiere a la variable de la clase
    }

    public int getVolumenML(){
        return volumenML;
    }

    public void setVolumenML(int volumenML){
        //Regla: debe estar entre 100 y 3000ml
        if (volumenML<100 || volumenML>3000){
            throw new IllegalArgumentException("El volumen debe estar entre 100 y 3000ml");
        }
        this.volumenML=volumenML;
    }

    public int getStock(){
        return stock;
    }
    public void setStock(int stock){
        //Regla: debe ser mayor que cero
        if (stock <= 0){
            throw new IllegalArgumentException("El stock debe ser un valor mayor que cero");
        }
        this.stock=stock;
    }


    //METODOS ABSTRACTOS: Obliga a las clases hijas a darles codigo)
    public abstract double calcularPrecio();
    public abstract String obtenerDetalle();

    //Redefinicion de toString() para mostrar solo nombre y volumen en listados resumidos
    @Override
    public String toString(){
        return "Nombre: "+nombre+"| Volumen: "+volumenML+" ml";
    }
}
