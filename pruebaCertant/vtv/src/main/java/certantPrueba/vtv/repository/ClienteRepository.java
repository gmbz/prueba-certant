package certantPrueba.vtv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import certantPrueba.vtv.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query(nativeQuery = true, value = "SELECT COUNT(v.patente) FROM cliente AS c INNER JOIN vehiculo AS v ON c.id_cliente=v.id_cliente WHERE c.id_cliente=:id")
    Integer countVehiculos(@Param("id") int id_cliente);
}
