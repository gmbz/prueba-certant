package certantPrueba.vtv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import certantPrueba.vtv.model.Inspector;

@Repository
public interface InspectorRepository extends JpaRepository<Inspector, Integer> {

}
