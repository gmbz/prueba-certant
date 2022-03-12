package certantPrueba.vtv.service;

import java.util.List;

import certantPrueba.vtv.model.Inspector;

public interface IInspectorService {
    public List<Inspector> findAll() throws Exception;

    public Inspector findById(Inspector inspector) throws Exception;

    public Inspector save(Inspector inspector) throws Exception;

    public Inspector update(Inspector inspector) throws Exception;

    public boolean delete(Inspector inspector) throws Exception;
}
