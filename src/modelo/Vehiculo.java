package modelo;

public abstract class Vehiculo {
    private String placa;
    private String marca;
    private String modelo;

    public Vehiculo(String placa, String marca, String modelo) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
    }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }
    
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    
    // Método abstracto para aplicar polimorfismo en las subclases
    public abstract double calcularCostoMantenimiento();
    
    // Sobrescritura del método equals para comparar si dos vehículos son el mismo por su placa
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vehiculo) {
            Vehiculo otro = (Vehiculo) obj; // Downcasting
            return this.placa.equals(otro.getPlaca());
        }
        return false;
    }
}