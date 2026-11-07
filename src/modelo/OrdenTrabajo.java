package modelo;
import java.util.ArrayList;

public class OrdenTrabajo implements ElementoFacturable {
    private int id;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private Mecanico mecanico;
    private ArrayList<Servicio> servicios;
    private ArrayList<Repuesto> repuestos;
    private String estado;

    // Sobrecarga de constructores (Constructor 1: Sin mecánico asignado aún)
    public OrdenTrabajo(int id, Cliente cliente, Vehiculo vehiculo) {
        this.id = id;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.servicios = new ArrayList<>();
        this.repuestos = new ArrayList<>();
        this.estado = "Pendiente";
    }

    // Sobrecarga de constructores (Constructor 2: Con mecánico asignado)
    public OrdenTrabajo(int id, Cliente cliente, Vehiculo vehiculo, Mecanico mecanico) {
        this(id, cliente, vehiculo); // Llama al constructor de arriba
        this.mecanico = mecanico;
    }

    public void agregarServicio(Servicio s) { servicios.add(s); }
    public void agregarRepuesto(Repuesto r) { repuestos.add(r); }
    public void asignarMecanico(Mecanico m) { this.mecanico = m; }
    public void cambiarEstado(String estado) { this.estado = estado; }
    
    public String getEstado() { return estado; }
    public Vehiculo getVehiculo() { return vehiculo; }
    public int getId() { return id; }

    // Calcula el total aplicando polimorfismo en el vehículo
    @Override
    public double calcularSubtotal() {
        double total = vehiculo.calcularCostoMantenimiento(); // Polimorfismo
        for (Servicio s : servicios) { 
            total += s.calcularSubtotal(); 
        }
        for (Repuesto r : repuestos) { 
            total += r.getPrecio(); 
        }
        return total;
    }
}