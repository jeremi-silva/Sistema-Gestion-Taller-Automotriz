package modelo;

public class Servicio implements ElementoFacturable {
    private String codigo;
    private String descripcion;
    private double precio;

    public Servicio(String codigo, String descripcion, double precio) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public String getCodigo() { return codigo; }
    public String getDescripcion() { return descripcion; }
    public double getPrecio() { return precio; }

    // Implementación obligatoria de la interfaz
    @Override
    public double calcularSubtotal() { 
        return precio; 
    }
}