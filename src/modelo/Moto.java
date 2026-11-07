package modelo;

public class Moto extends Vehiculo {
    private Integer cilindraje; // Uso de clase Wrapper

    public Moto(String placa, String marca, String modelo, Integer cilindraje) {
        super(placa, marca, modelo);
        this.cilindraje = cilindraje;
    }

    public Integer getCilindraje() { return cilindraje; }
    public void setCilindraje(Integer cilindraje) { this.cilindraje = cilindraje; }
    
    // Sobrescritura obligatoria aplicando Polimorfismo
    @Override
    public double calcularCostoMantenimiento() { 
        return 30.0; // Costo base sugerido para motos
    }
}