package certantPrueba.vtv.service;

import java.util.List;

import certantPrueba.vtv.model.TipoCliente;

public interface ITipoClienteService {
    public List<TipoCliente> findAll() throws Exception;

}
