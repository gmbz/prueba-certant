package certantPrueba.vtv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import certantPrueba.vtv.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {

    @Query(nativeQuery = true, value = "SELECT COUNT(v.patente) FROM cliente AS c INNER JOIN vehiculo AS v ON c.dni=v.dni WHERE c.dni=:dni")
    Integer countVehiculos(@Param("dni") String dni);
}
