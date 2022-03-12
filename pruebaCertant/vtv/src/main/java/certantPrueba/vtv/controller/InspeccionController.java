package certantPrueba.vtv.controller;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import certantPrueba.vtv.model.Cliente;
import certantPrueba.vtv.model.EstadoInspeccion;
import certantPrueba.vtv.model.Inspeccion;
import certantPrueba.vtv.model.Medicion;
import certantPrueba.vtv.model.Oblea;
import certantPrueba.vtv.model.Observacion;
import certantPrueba.vtv.service.IEstadoInspeccionService;
import certantPrueba.vtv.service.IInspeccionService;

@Component
public class InspeccionController {
    public List<Inspeccion> findAll(IInspeccionService inspeccionService) throws Exception {
        List<Inspeccion> inspecciones = inspeccionService.findAll();
        return inspecciones;
    }

    public Inspeccion findById(IInspeccionService inspeccionService, Inspeccion inspeccion) throws Exception {
        return inspeccionService.findById(inspeccion);
    }

    public Inspeccion save(IInspeccionService inspeccionService, Inspeccion inspeccion) throws Exception {
        inspeccion.setFecha(LocalDateTime.now());
        EstadoInspeccion estado = new EstadoInspeccion();
        estado.setId_estado(4);
        inspeccion.setEstado(estado);
        return inspeccionService.save(inspeccion);
    }

    public Inspeccion update(IInspeccionService inspeccionService, Inspeccion inspeccion) throws Exception {
        Inspeccion inspeccionActualizar = inspeccionService.findById(inspeccion);
        inspeccionActualizar.setDebe_pagar(inspeccion.isDebe_pagar());
        inspeccionActualizar.setInspector(inspeccion.getInspector());
        inspeccionActualizar.setVehiculo(inspeccion.getVehiculo());
        return inspeccionService.update(inspeccionActualizar);
    }

    public boolean delete(IInspeccionService inspeccionService, Inspeccion inspeccion) throws Exception {
        return inspeccionService.delete(inspeccion);
    }

    public Inspeccion updateObservacion(IInspeccionService inspeccionService, Inspeccion inspeccion) throws Exception {
        Inspeccion inspeccionActualizar = inspeccionService.findById(inspeccion);
        inspeccionActualizar.setObservacion(inspeccion.getObservacion());
        return inspeccionService.update(inspeccionActualizar);
    }

    public Inspeccion updateMedicion(IInspeccionService inspeccionService, Inspeccion inspeccion) throws Exception {
        Inspeccion inspeccionActualizar = inspeccionService.findById(inspeccion);
        inspeccionActualizar.setMedicion(inspeccion.getMedicion());
        return inspeccionService.update(inspeccionActualizar);
    }

    public Inspeccion finalizarInspeccion(IInspeccionService inspeccionService,
            IEstadoInspeccionService estadoInspeccionService, Inspeccion inspeccion)
            throws Exception {

        Cliente cliente = inspeccion.getVehiculo().getCliente();
        Inspeccion lastInspeccion = inspeccionService.findLastInspeccion(cliente, inspeccion);
        boolean paga = debePagar(cliente, lastInspeccion);
        inspeccion.setDebe_pagar(paga);
        asignarEstado(estadoInspeccionService, inspeccion);
        if (inspeccion.getEstado().getDescripcion().equals("APTO")) {
            generarOblea(inspeccion);
        }
        return inspeccionService.update(inspeccion);
    }

    private boolean debePagar(Cliente cliente, Inspeccion inspeccion) {
        if (inspeccion != null && inspeccion.getEstado().getDescripcion().equals("CONDICIONAL")
                && inspeccion.getFecha().getDayOfMonth() == LocalDateTime.now().getDayOfMonth()) {
            return false;
        }
        if (cliente.getTipo().getDescripcion().equals("Extento")) {
            return false;
        }
        return true;
    }

    private void generarOblea(Inspeccion inspeccion) {
        Oblea oblea = new Oblea();
        oblea.setFecha_vencimiento(inspeccion.getFecha().plusYears(1));
        inspeccion.getVehiculo().setOblea(oblea);
    }

    private void asignarEstado(IEstadoInspeccionService estadoInspeccionService, Inspeccion inspeccion)
            throws Exception {
        EstadoInspeccion estado = new EstadoInspeccion();
        if (verificaEstado(inspeccion, "RECHAZADO")) {
            estado.setDescripcion("RECHAZADO");
        } else if (verificaEstado(inspeccion, "CONDICIONAL")) {
            estado.setDescripcion("CONDICIONAL");
        } else {
            estado.setDescripcion("APTO");
        }
        inspeccion.setEstado(estadoInspeccionService.findByDescripcion(estado));
    }

    private boolean verificaEstado(Inspeccion inspeccion, String estado) {
        Observacion observacion = inspeccion.getObservacion();
        Medicion medicion = inspeccion.getMedicion();
        if ((observacion.getChasis().equals(estado)) || (observacion.getEspejos().equals(estado))
                || (observacion.getLuces().equals(estado)) || (observacion.getEmergencia().equals(estado))
                || (observacion.getPatente().equals(estado))
                || (observacion.getVidrios_seguridad().equals(estado))) {
            return true;
        } else if (medicion.getFrenos().equals(estado) || medicion.getContaminacion().equals(estado)
                || medicion.getSuspencion().equals(estado) || medicion.getTren_delantero().equals(estado)) {
            return true;
        }
        return false;
    }

    public List<String> getOpciones() {
        List<String> opciones = new LinkedList<>();
        opciones.add("APTO");
        opciones.add("CONDICIONAL");
        opciones.add("RECHAZADO");
        return opciones;
    }
}
