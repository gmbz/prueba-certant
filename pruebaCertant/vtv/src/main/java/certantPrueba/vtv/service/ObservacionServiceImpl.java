package certantPrueba.vtv.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import certantPrueba.vtv.model.Observacion;
import certantPrueba.vtv.repository.ObservacionRepository;

@Service
public class ObservacionServiceImpl implements IObservacionService {

    @Autowired
    private ObservacionRepository observacionRepository;

    @Override
    public List<Observacion> findAll() throws Exception {
        try {
            List<Observacion> observaciones = observacionRepository.findAll();
            return observaciones;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Observacion findById(Observacion observacion) throws Exception {
        try {
            Optional<Observacion> entity = observacionRepository.findById(observacion.getNro_observacion());
            return entity.get();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Observacion save(Observacion observacion) throws Exception {
        try {
            return observacionRepository.save(observacion);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Observacion update(Observacion observacion) throws Exception {
        try {
            return observacionRepository.save(observacion);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean delete(Observacion observacion) throws Exception {
        try {
            observacionRepository.deleteById(observacion.getNro_observacion());
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
