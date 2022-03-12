package certantPrueba.vtv.controller;

import java.util.List;

import org.springframework.stereotype.Component;

import certantPrueba.vtv.model.Cliente;
import certantPrueba.vtv.model.Inspeccion;
import certantPrueba.vtv.model.TipoCliente;
import certantPrueba.vtv.model.Vehiculo;
import certantPrueba.vtv.service.IClienteService;
import certantPrueba.vtv.service.IInspeccionService;
import certantPrueba.vtv.service.ITipoClienteService;
import certantPrueba.vtv.service.IVehiculoService;

@Component
public class ClienteController {

    public List<Cliente> findAll(IClienteService clienteService) throws Exception {
        return clienteService.findAll();
    }

    public Cliente findById(IClienteService clienteService, Cliente cliente) throws Exception {
        return clienteService.findById(cliente);
    }

    public Cliente save(IClienteService clienteService, Cliente cliente) throws Exception {
        return clienteService.save(cliente);
    }

    public Cliente update(IClienteService clienteService, Cliente cliente) throws Exception {
        return clienteService.update(cliente);
    }

    public boolean delete(IClienteService clienteService, Cliente cliente) throws Exception {
        return clienteService.delete(cliente);
    }

    public List<TipoCliente> getTipos(ITipoClienteService tipoClienteService) throws Exception {
        return tipoClienteService.findAll();
    }

    public List<Inspeccion> getInspecciones(IInspeccionService inspeccionService, IClienteService clienteService,
            Cliente cliente) throws Exception {
        if (clienteService.countVehiculos(cliente) > 1) {
            List<Inspeccion> inspecciones = inspeccionService.findCliente(cliente);
            return inspecciones;
        }
        return null;
    }

    public List<Vehiculo> findAllbyCliente(IVehiculoService vehiculoService, Cliente cliente) throws Exception {
        List<Vehiculo> vehiculos = vehiculoService.findAllByCliente(cliente);
        return vehiculos;
    }
}
