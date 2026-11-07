package modelo;

public class Repuesto {
    private String codigo;
    private String nombre;
    private Double precio; // Wrapper
    private Integer stock; // Wrapper

    public Repuesto(String codigo, String nombre, Double precio, Integer stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public Double getPrecio() { return precio; }
    
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
    
    // Método para reducir stock (Usa unboxing automático)
    public void reducirStock(int cantidad) { 
        this.stock -= cantidad; 
    }
}