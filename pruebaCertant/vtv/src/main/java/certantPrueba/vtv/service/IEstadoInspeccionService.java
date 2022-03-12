package certantPrueba.vtv.service;

import java.util.List;

import certantPrueba.vtv.model.EstadoInspeccion;

public interface IEstadoInspeccionService {
    public List<EstadoInspeccion> findAll() throws Exception;

    public EstadoInspeccion findById(EstadoInspeccion estado) throws Exception;

    public EstadoInspeccion save(EstadoInspeccion estado) throws Exception;

    public EstadoInspeccion update(EstadoInspeccion estado) throws Exception;

    public boolean delete(EstadoInspeccion estado) throws Exception;

    public EstadoInspeccion findByDescripcion(EstadoInspeccion estado) throws Exception;
}
