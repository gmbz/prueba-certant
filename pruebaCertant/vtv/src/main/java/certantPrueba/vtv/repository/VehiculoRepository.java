package certantPrueba.vtv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import certantPrueba.vtv.model.Cliente;
import certantPrueba.vtv.model.Vehiculo;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, String> {

    List<Vehiculo> findAllByCliente(Cliente cliente);
}
