package modelo;

public class Auto extends Vehiculo {
    private Integer numPuertas; // Uso de clase Wrapper (Integer en lugar de int)

    public Auto(String placa, String marca, String modelo, Integer numPuertas) {
        super(placa, marca, modelo); // Llamada al constructor de la superclase
        this.numPuertas = numPuertas;
    }

    public Integer getNumPuertas() { return numPuertas; }
    public void setNumPuertas(Integer numPuertas) { this.numPuertas = numPuertas; }
    
    // Sobrescritura obligatoria aplicando Polimorfismo
    @Override
    public double calcularCostoMantenimiento() { 
        return 50.0; // Costo base sugerido para autos
    }
}