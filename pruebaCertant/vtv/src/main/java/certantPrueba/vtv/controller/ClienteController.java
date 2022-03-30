package certantPrueba.vtv.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import certantPrueba.vtv.exception.BadRequestException;
import certantPrueba.vtv.exception.NotFoundException;
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
        if (validaDni(cliente)) {
            return clienteService.save(cliente);
        }
        throw new BadRequestException("DNI invalido");
    }

    public Cliente update(IClienteService clienteService, Cliente cliente) throws Exception {
        return clienteService.update(cliente);
    }

    public boolean delete(IClienteService clienteService, Cliente cliente) throws Exception {
        Cliente clienteToDelete = clienteService.findById(cliente);
        if (clienteToDelete != null) {
            return clienteService.delete(clienteToDelete);
        }

        throw new NotFoundException("El cliente no se encuentra");
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

    public boolean validaDni(Cliente cliente) {
        Pattern patentePattern = Pattern.compile("^[0-9]{8}$");
        Matcher patenteMattcher = patentePattern.matcher(cliente.getDni());
        if (patenteMattcher.matches()) {
            return true;
        }
        return false;
    }
}
