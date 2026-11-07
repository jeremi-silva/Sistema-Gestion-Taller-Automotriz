package servicio;
import modelo.*;
import java.util.ArrayList;

public class TallerService {
    // Listas dinámicas para gestionar los datos en memoria
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Vehiculo> vehiculos = new ArrayList<>();
    private ArrayList<Mecanico> mecanicos = new ArrayList<>();
    private ArrayList<Servicio> servicios = new ArrayList<>();
    private ArrayList<Repuesto> repuestos = new ArrayList<>();
    private ArrayList<OrdenTrabajo> ordenes = new ArrayList<>();
    private int contadorOrdenes = 1;

    public TallerService() {
        // Datos precargados iniciales para poder probar el sistema rápidamente
        mecanicos.add(new Mecanico("M01", "Juan Perez", "0991234567", "Motor"));
        servicios.add(new Servicio("S01", "Cambio de Aceite", 15.0));
        repuestos.add(new Repuesto("R01", "Filtro", 10.0, 5));
    }

    // --- Módulo Clientes ---
    public void registrarCliente(Cliente c) { clientes.add(c); }
    
    public Cliente buscarCliente(String id) {
        for (Cliente c : clientes) {
            if (c.getId().equals(id)) return c;
        }
        return null;
    }

    // --- Módulo Vehículos ---
    public void registrarVehiculo(Vehiculo v) { vehiculos.add(v); }
    
    public Vehiculo buscarVehiculo(String placa) {
        for (Vehiculo v : vehiculos) {
            if (v.getPlaca().equals(placa)) return v;
        }
        return null;
    }

    // --- Módulo Órdenes ---
    public void registrarOrden(Cliente c, Vehiculo v, Mecanico m) {
        OrdenTrabajo ot = new OrdenTrabajo(contadorOrdenes++, c, v, m);
        ordenes.add(ot);
        m.setDisponible(false); // Cambia el estado del mecánico
    }
    
    // --- Matrices: Generación de Reporte ---
    public String[][] generarReporteMecanicos() {
        String[][] reporte = new String[mecanicos.size()][3];
        for (int i = 0; i < mecanicos.size(); i++) {
            reporte[i][0] = mecanicos.get(i).getNombre();
            reporte[i][1] = mecanicos.get(i).getEspecialidad();
            reporte[i][2] = mecanicos.get(i).isDisponible() ? "Sí" : "No";
        }
        return reporte;
    }

    // Getters para enviar las listas a la vista
    public ArrayList<Repuesto> getRepuestos() { return repuestos; }
    public ArrayList<OrdenTrabajo> getOrdenes() { return ordenes; }
    public ArrayList<Vehiculo> getVehiculos() { return vehiculos; }
    
    public Mecanico getMecanico(int index) { 
        if(!mecanicos.isEmpty()) return mecanicos.get(index);
        return null;
    }
    public Repuesto buscarRepuesto(String codigo) {
        for (Repuesto r : repuestos) {
            if (r.getCodigo().equals(codigo)) return r;
        }
        return null;
    }
}