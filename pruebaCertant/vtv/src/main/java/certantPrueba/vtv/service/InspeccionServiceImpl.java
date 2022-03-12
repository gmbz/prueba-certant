package certantPrueba.vtv.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import certantPrueba.vtv.model.Cliente;
import certantPrueba.vtv.model.Inspeccion;
import certantPrueba.vtv.model.Inspector;
import certantPrueba.vtv.repository.InspeccionRepository;

@Service
public class InspeccionServiceImpl implements IInspeccionService {

    @Autowired
    private InspeccionRepository inspeccionRepository;

    @Override
    public List<Inspeccion> findAll() throws Exception {
        try {
            List<Inspeccion> inspecciones = inspeccionRepository.findAll();
            return inspecciones;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Inspeccion findById(Inspeccion inspeccion) throws Exception {
        try {
            Optional<Inspeccion> entity = inspeccionRepository.findById(inspeccion.getNro_inspeccion());
            return entity.get();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Inspeccion save(Inspeccion inspeccion) throws Exception {
        try {
            return inspeccionRepository.save(inspeccion);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Inspeccion update(Inspeccion inspeccion) throws Exception {
        try {
            return inspeccionRepository.save(inspeccion);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean delete(Inspeccion inspeccion) throws Exception {
        try {
            inspeccionRepository.deleteById(inspeccion.getNro_inspeccion());
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Inspeccion> findVehiculoEstadoActual() throws Exception {
        try {
            List<Inspeccion> inspecciones = inspeccionRepository.findVehiculoEstadoActual();
            return inspecciones;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Inspeccion> findFechaBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws Exception {
        try {
            List<Inspeccion> inspecciones = inspeccionRepository.findFechaBetween(fechaInicio, fechaFin);
            return inspecciones;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Inspeccion> findInspectorFechaBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin,
            Inspector inspector)
            throws Exception {
        try {
            List<Inspeccion> inspecciones = inspeccionRepository.findInspectorFechaBetween(fechaInicio, fechaFin,
                    inspector.getLegajo());
            return inspecciones;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Inspeccion> findCliente(Cliente cliente) throws Exception {
        try {
            List<Inspeccion> inspecciones = inspeccionRepository.findCliente(cliente.getId_cliente());
            return inspecciones;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Inspeccion findLastInspeccion(Cliente cliente, Inspeccion inspeccion) throws Exception {
        try {
            Inspeccion entity = inspeccionRepository.findLastInspeccion(cliente.getId_cliente(),
                    inspeccion.getNro_inspeccion());
            return entity;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
