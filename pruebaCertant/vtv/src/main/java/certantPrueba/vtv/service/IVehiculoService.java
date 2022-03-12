package certantPrueba.vtv.service;

import java.util.List;

import certantPrueba.vtv.model.Cliente;
import certantPrueba.vtv.model.Vehiculo;

public interface IVehiculoService {
    public List<Vehiculo> findAll() throws Exception;

    public Vehiculo findById(Vehiculo vehiculo) throws Exception;

    public Vehiculo save(Vehiculo vehiculo) throws Exception;

    public Vehiculo update(Vehiculo vehiculo) throws Exception;

    public boolean delete(Vehiculo vehiculo) throws Exception;

    public List<Vehiculo> findAllByCliente(Cliente cliente) throws Exception;
}
