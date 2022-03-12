package certantPrueba.vtv.service;

import java.util.List;

import certantPrueba.vtv.model.Observacion;

public interface IObservacionService {
    public List<Observacion> findAll() throws Exception;

    public Observacion findById(Observacion observacion) throws Exception;

    public Observacion save(Observacion observacion) throws Exception;

    public Observacion update(Observacion observacion) throws Exception;

    public boolean delete(Observacion observacion) throws Exception;
}
