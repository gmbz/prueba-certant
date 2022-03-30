package certantPrueba.vtv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import certantPrueba.vtv.model.Marca;
import certantPrueba.vtv.model.Modelo;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM marca AS m INNER JOIN tipo_vehiculo AS tv ON tv.id_tipo_vehiculo=m.id_tipo_vehiculo WHERE tv.tipo_vehiculo='auto'")
    List<Marca> findAllMarcasAuto();

    @Query(nativeQuery = true, value = "SELECT * FROM marca AS m INNER JOIN tipo_vehiculo AS tv ON tv.id_tipo_vehiculo=m.id_tipo_vehiculo WHERE tv.tipo_vehiculo='moto'")
    List<Marca> findAllMarcasMoto();

    @Query(nativeQuery = true, value = "SELECT * FROM marca AS m INNER JOIN tipo_vehiculo AS tv ON tv.id_tipo_vehiculo=m.id_tipo_vehiculo WHERE tv.tipo_vehiculo='camioneta'")
    List<Marca> findAllMarcasCamioneta();

    @Query(nativeQuery = true, value = "SELECT * FROM modelo AS mo INNER JOIN marca AS ma ON ma.id_marca=mo.marca WHERE ma.id_marca=:id_marca")
    List<Modelo> findAllModelos(@Param("id_marca") int id_marca);


}
