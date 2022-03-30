package certantPrueba.vtv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import certantPrueba.vtv.model.Modelo;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Integer> {

}
