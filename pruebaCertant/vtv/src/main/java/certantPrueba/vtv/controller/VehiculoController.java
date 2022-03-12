package certantPrueba.vtv.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import certantPrueba.vtv.model.Inspeccion;
import certantPrueba.vtv.model.Vehiculo;
import certantPrueba.vtv.service.IInspeccionService;
import certantPrueba.vtv.service.IVehiculoService;

@Component
public class VehiculoController {

    public List<Vehiculo> findAll(IVehiculoService vehiculoService) throws Exception {
        return vehiculoService.findAll();
    }

    public Vehiculo findById(IVehiculoService vehiculoService, Vehiculo vehiculo) throws Exception {
        return vehiculoService.findById(vehiculo);
    }

    public Vehiculo save(IVehiculoService vehiculoService, Vehiculo vehiculo) throws Exception {
        return vehiculoService.save(vehiculo);
    }

    public Vehiculo update(IVehiculoService vehiculoService, Vehiculo vehiculo) throws Exception {
        return vehiculoService.update(vehiculo);
    }

    public boolean delete(IVehiculoService vehiculoService, Vehiculo vehiculo) throws Exception {
        return vehiculoService.delete(vehiculo);
    }

    public List<Inspeccion> findAllEstados(IInspeccionService inspeccionService) throws Exception {
        return inspeccionService.findVehiculoEstadoActual();
    }

    public List<Inspeccion> findUltimaSemana(IInspeccionService inspeccionService) throws Exception {
        LocalDateTime fechaFin = LocalDateTime.now();
        LocalDateTime fechaInicio = fechaFin.minusDays(7);

        return inspeccionService.findFechaBetween(fechaInicio, fechaFin);
    }

}
