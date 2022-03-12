package certantPrueba.vtv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import certantPrueba.vtv.model.Observacion;

@Repository
public interface ObservacionRepository extends JpaRepository<Observacion, Integer> {

}
