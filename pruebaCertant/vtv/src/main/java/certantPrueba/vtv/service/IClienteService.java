package certantPrueba.vtv.service;

import java.util.List;

import certantPrueba.vtv.model.Cliente;

public interface IClienteService {
    public List<Cliente> findAll() throws Exception;

    public Cliente findById(Cliente cliente) throws Exception;

    public Cliente save(Cliente cliente) throws Exception;

    public Cliente update(Cliente cliente) throws Exception;

    public boolean delete(Cliente cliente) throws Exception;

    public Integer countVehiculos(Cliente cliente) throws Exception;
}
