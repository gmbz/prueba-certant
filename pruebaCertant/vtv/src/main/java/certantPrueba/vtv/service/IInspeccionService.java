package certantPrueba.vtv.service;

import java.time.LocalDateTime;
import java.util.List;

import certantPrueba.vtv.model.Cliente;
import certantPrueba.vtv.model.Inspeccion;
import certantPrueba.vtv.model.Inspector;

public interface IInspeccionService {
    public List<Inspeccion> findAll() throws Exception;

    public Inspeccion findById(Inspeccion inspeccion) throws Exception;

    public Inspeccion save(Inspeccion inspeccion) throws Exception;

    public Inspeccion update(Inspeccion inspeccion) throws Exception;

    public boolean delete(Inspeccion inspeccion) throws Exception;

    public List<Inspeccion> findVehiculoEstadoActual() throws Exception;

    public List<Inspeccion> findFechaBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws Exception;

    public List<Inspeccion> findInspectorFechaBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin,
            Inspector inspector)
            throws Exception;

    public List<Inspeccion> findCliente(Cliente cliente) throws Exception;

    public Inspeccion findLastInspeccion(Cliente cliente, Inspeccion inspeccion) throws Exception;

}
