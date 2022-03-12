package certantPrueba.vtv.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import certantPrueba.vtv.model.Cliente;
import certantPrueba.vtv.model.Vehiculo;
import certantPrueba.vtv.repository.VehiculoRepository;

@Service
public class VehiculoServiceImpl implements IVehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

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

}
