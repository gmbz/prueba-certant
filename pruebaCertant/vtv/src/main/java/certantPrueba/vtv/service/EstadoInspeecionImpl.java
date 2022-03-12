package certantPrueba.vtv.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import certantPrueba.vtv.model.EstadoInspeccion;
import certantPrueba.vtv.repository.EstadoInspeccionRepository;

@Service
public class EstadoInspeecionImpl implements IEstadoInspeccionService {

    @Autowired
    private EstadoInspeccionRepository estadoInspeccionRepository;

    @Override
    public List<EstadoInspeccion> findAll() throws Exception {
        try {
            List<EstadoInspeccion> estados = estadoInspeccionRepository.findAll();
            return estados;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public EstadoInspeccion findById(EstadoInspeccion estado) throws Exception {
        try {
            Optional<EstadoInspeccion> entity = estadoInspeccionRepository.findById(estado.getId_estado());
            return entity.get();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public EstadoInspeccion save(EstadoInspeccion estado) throws Exception {
        try {
            return estadoInspeccionRepository.save(estado);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public EstadoInspeccion update(EstadoInspeccion estado) throws Exception {
        try {
            return estadoInspeccionRepository.save(estado);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean delete(EstadoInspeccion estado) throws Exception {
        try {
            estadoInspeccionRepository.deleteById(estado.getId_estado());
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public EstadoInspeccion findByDescripcion(EstadoInspeccion estado) throws Exception {
        try {
            return estadoInspeccionRepository.findByDescripcion(estado.getDescripcion());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
