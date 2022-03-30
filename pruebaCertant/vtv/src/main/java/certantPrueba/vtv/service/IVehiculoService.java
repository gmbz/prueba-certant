package certantPrueba.vtv.service;

import java.util.List;

import certantPrueba.vtv.model.Cliente;
import certantPrueba.vtv.model.Marca;
import certantPrueba.vtv.model.Modelo;
import certantPrueba.vtv.model.TipoVehiculo;
import certantPrueba.vtv.model.Vehiculo;

public interface IVehiculoService {
    public List<Vehiculo> findAll() throws Exception;

    public Vehiculo findById(Vehiculo vehiculo) throws Exception;

    public Vehiculo save(Vehiculo vehiculo) throws Exception;

    public Vehiculo update(Vehiculo vehiculo) throws Exception;

    public boolean delete(Vehiculo vehiculo) throws Exception;

    public List<Vehiculo> findAllByCliente(Cliente cliente) throws Exception;

    public List<TipoVehiculo> findAllTipoVehiculo() throws Exception;

    public List<Marca> findAllMarcas() throws Exception;

    public List<Modelo> findAllModelos() throws Exception;

    public Marca findMarcaById(Marca marca) throws Exception;

    public List<Marca> findAllMarcasAuto() throws Exception;

    public List<Marca> findAllMarcasMoto() throws Exception;

    public List<Marca> findAllMarcasCamioneta() throws Exception;

    public List<Modelo> findAllModelosByMarca(Marca marca) throws Exception;

    public Modelo findModeloById(Modelo modelo) throws Exception;
}
