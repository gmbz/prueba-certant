package certantPrueba.vtv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import certantPrueba.vtv.model.EstadoInspeccion;

@Repository
public interface EstadoInspeccionRepository extends JpaRepository<EstadoInspeccion, Integer> {

    EstadoInspeccion findByDescripcion(String descripcion);

}
