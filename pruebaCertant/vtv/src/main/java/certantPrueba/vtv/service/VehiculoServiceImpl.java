package certantPrueba.vtv.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import certantPrueba.vtv.model.Cliente;
import certantPrueba.vtv.model.Marca;
import certantPrueba.vtv.model.Modelo;
import certantPrueba.vtv.model.TipoVehiculo;
import certantPrueba.vtv.model.Vehiculo;
import certantPrueba.vtv.repository.MarcaRepository;
import certantPrueba.vtv.repository.ModeloRepository;
import certantPrueba.vtv.repository.TipoVehiculoRepository;
import certantPrueba.vtv.repository.VehiculoRepository;

@Service
public class VehiculoServiceImpl implements IVehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private TipoVehiculoRepository tipoVehiculoRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private ModeloRepository modeloRepository;

    @Override
    public List<Vehiculo> findAll() throws Exception {
        try {
            List<Vehiculo> vehiculos = vehiculoRepository.findAll();
            return vehiculos;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Vehiculo findById(Vehiculo vehiculo) throws Exception {
        try {
            Optional<Vehiculo> entity = vehiculoRepository.findById(vehiculo.getPatente());
            return entity.get();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Vehiculo save(Vehiculo vehiculo) throws Exception {
        try {
            return vehiculoRepository.save(vehiculo);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Vehiculo update(Vehiculo vehiculo) throws Exception {
        try {
            return vehiculoRepository.save(vehiculo);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean delete(Vehiculo vehiculo) throws Exception {
        try {
            vehiculoRepository.deleteById(vehiculo.getPatente());
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Vehiculo> findAllByCliente(Cliente cliente) throws Exception {
        try {
            List<Vehiculo> vehiculos = vehiculoRepository.findAllByCliente(cliente);
            return vehiculos;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<TipoVehiculo> findAllTipoVehiculo() throws Exception {
        try {
            List<TipoVehiculo> tipos = tipoVehiculoRepository.findAll();
            return tipos;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Marca> findAllMarcas() throws Exception {
        try {
            List<Marca> marcas = marcaRepository.findAll();
            return marcas;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Modelo> findAllModelos() throws Exception {
        try {
            List<Modelo> modelos = modeloRepository.findAll();
            return modelos;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Marca findMarcaById(Marca marca) throws Exception {
        try {
            Optional<Marca> entity = marcaRepository.findById(marca.getId_marca());
            return entity.get();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Marca> findAllMarcasAuto() throws Exception {
        try {
            List<Marca> marcas = marcaRepository.findAllMarcasAuto();
            return marcas;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Marca> findAllMarcasMoto() throws Exception {
        try {
            List<Marca> marcas = marcaRepository.findAllMarcasMoto();
            return marcas;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Marca> findAllMarcasCamioneta() throws Exception {
        try {
            List<Marca> marcas = marcaRepository.findAllMarcasCamioneta();
            return marcas;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Modelo> findAllModelosByMarca(Marca marca) throws Exception {
        try {
            List<Modelo> modelos = marcaRepository.findAllModelos(marca.getId_marca());
            return modelos;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Modelo findModeloById(Modelo modelo) throws Exception {
        try {
            Optional<Modelo> entity = modeloRepository.findById(modelo.getId_modelo());
            return entity.get();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
