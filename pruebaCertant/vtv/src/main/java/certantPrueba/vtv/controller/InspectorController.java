package certantPrueba.vtv.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import certantPrueba.vtv.model.Inspeccion;
import certantPrueba.vtv.model.Inspector;
import certantPrueba.vtv.service.IInspeccionService;
import certantPrueba.vtv.service.IInspectorService;

@Component
public class InspectorController {
    public List<Inspector> findAll(IInspectorService inspectorService) throws Exception {
        return inspectorService.findAll();
    }

    public Inspector findById(IInspectorService inspectorService, Inspector inspector) throws Exception {
        return inspectorService.findById(inspector);
    }

    public Inspector save(IInspectorService inspectorService, Inspector inspector) throws Exception {
        inspector.generaLegajo(inspectorService.findFirstOrderByLegajo());
        return inspectorService.save(inspector);
    }

    public Inspector update(IInspectorService inspectorService, Inspector inspector) throws Exception {
        return inspectorService.update(inspector);
    }

    public boolean delete(IInspectorService inspectorService, Inspector inspector) throws Exception {
        return inspectorService.delete(inspector);
    }

    public List<Inspeccion> inspeccionesUltimosTresDias(IInspeccionService inspectorService, Inspector inspector)
            throws Exception {
        LocalDateTime fechaFin = LocalDateTime.now();
        LocalDateTime fechaInicio = fechaFin.minusDays(3);
        return inspectorService.findInspectorFechaBetween(fechaInicio, fechaFin, inspector);
    }

}
