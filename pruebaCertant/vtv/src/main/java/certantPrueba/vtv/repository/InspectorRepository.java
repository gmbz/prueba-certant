package certantPrueba.vtv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import certantPrueba.vtv.model.Inspector;

@Repository
public interface InspectorRepository extends JpaRepository<Inspector, String> {

    @Query(nativeQuery= true, value = "SELECT MAX(legajo) FROM inspector;")
    int findFirstOrderByLegajo();
}
