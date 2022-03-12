package certantPrueba.vtv.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import certantPrueba.vtv.model.Inspector;
import certantPrueba.vtv.repository.InspectorRepository;

@Service
public class InspectorServiceImpl implements IInspectorService {

    @Autowired
    private InspectorRepository inspectorRepository;

    @Override
    public List<Inspector> findAll() throws Exception {
        try {
            List<Inspector> inspectores = inspectorRepository.findAll();
            return inspectores;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Inspector findById(Inspector inspector) throws Exception {
        try {
            Optional<Inspector> entity = inspectorRepository.findById(inspector.getLegajo());
            return entity.get();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Inspector save(Inspector inspector) throws Exception {
        try {
            return inspectorRepository.save(inspector);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Inspector update(Inspector inspector) throws Exception {
        try {
            return inspectorRepository.save(inspector);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean delete(Inspector inspector) throws Exception {
        try {
            inspectorRepository.deleteById(inspector.getLegajo());
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
