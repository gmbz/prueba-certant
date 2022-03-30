package certantPrueba.vtv.controller;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import certantPrueba.vtv.exception.BadRequestException;
import certantPrueba.vtv.model.Inspeccion;
import certantPrueba.vtv.model.Marca;
import certantPrueba.vtv.model.Modelo;
import certantPrueba.vtv.model.Moto;
import certantPrueba.vtv.model.TipoVehiculo;
import certantPrueba.vtv.model.Vehiculo;
import certantPrueba.vtv.service.IInspeccionService;
import certantPrueba.vtv.service.IVehiculoService;

@Component
public class VehiculoController {

    public List<Vehiculo> findAll(IVehiculoService vehiculoService) throws Exception {
        return vehiculoService.findAll();
    }

    public List<TipoVehiculo> findAllTipoVehiculo(IVehiculoService vehiculoService) throws Exception {
        return vehiculoService.findAllTipoVehiculo();
    }

    public List<Marca> findAllMarcas(IVehiculoService vehiculoService) throws Exception {
        return vehiculoService.findAllMarcas();
    }

    public List<Marca> findAllMarcasAuto(IVehiculoService vehiculoService) throws Exception {
        return vehiculoService.findAllMarcasAuto();
    }

    public List<Marca> findAllMarcasMoto(IVehiculoService vehiculoService) throws Exception {
        return vehiculoService.findAllMarcasMoto();
    }

    public List<Modelo> findAllModelosByMarca(IVehiculoService vehiculoService, Marca marca) throws Exception {
        return vehiculoService.findAllModelosByMarca(marca);
    }

    public Marca findMarcaById(IVehiculoService vehiculoService, Marca marca) throws Exception {
        return vehiculoService.findMarcaById(marca);
    }

    public Modelo findModeloById(IVehiculoService vehiculoService, Modelo modelo) throws Exception {
        return vehiculoService.findModeloById(modelo);
    }

    public List<Marca> findAllMarcasCamioneta(IVehiculoService vehiculoService) throws Exception {
        return vehiculoService.findAllMarcasCamioneta();
    }

    public List<Modelo> findAllModelos(IVehiculoService vehiculoService) throws Exception {
        return vehiculoService.findAllModelos();
    }

    public Vehiculo findById(IVehiculoService vehiculoService, Vehiculo vehiculo) throws Exception {
        return vehiculoService.findById(vehiculo);
    }

    public Vehiculo save(IVehiculoService vehiculoService, Vehiculo vehiculo) throws Exception {
        if (validaPatente(vehiculo)) {
            Marca marca = vehiculoService.findMarcaById(vehiculo.getModelo().getMarca());
            vehiculo.getModelo().setMarca(marca);
            return vehiculoService.save(vehiculo);
        }
        throw new BadRequestException("Patente invalida");
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

    public List<String> getTipos() {
        List<String> tipos = new LinkedList<>();
        tipos.add("AUTO");
        tipos.add("MOTO");
        tipos.add("CAMIONETA");
        return tipos;
    }

    private boolean validaPatente(Vehiculo vehiculo) {
        if (vehiculo.getClass().equals(Moto.class)) {
            System.out.println("ES UNA MOTO");
            return validaMotoPatente(vehiculo);
        }
        return validaAutoCamionetaPatente(vehiculo);
    }

    private boolean validaAutoCamionetaPatente(Vehiculo vehiculo) {

        Pattern patentePattern = Pattern.compile("^[A-Z]{3} [0-9]{2} [A-Z]{2}$");
        Matcher patenteMattcher = patentePattern.matcher(vehiculo.getPatente());
        if (patenteMattcher.matches()) {
            return true;
        }
        return false;
    }

    private boolean validaMotoPatente(Vehiculo vehiculo) {

        Pattern patentePattern = Pattern.compile("^[A-Z]{1}[0-9]{2} [0-9]{1}[A-Z]{3}$");
        Matcher patenteMattcher = patentePattern.matcher(vehiculo.getPatente());
        if (patenteMattcher.matches()) {
            return true;
        }
        return false;
    }

}
