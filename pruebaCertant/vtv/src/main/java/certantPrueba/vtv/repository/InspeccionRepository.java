package certantPrueba.vtv.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import certantPrueba.vtv.model.Inspeccion;

@Repository
public interface InspeccionRepository extends JpaRepository<Inspeccion, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM (SELECT MAX(i.fecha) AS fecha, v.patente FROM inspeccion AS i INNER JOIN vehiculo AS v ON i.patente = v.patente GROUP BY v.patente) v_max INNER JOIN inspeccion i ON i.patente = v_max.patente AND i.fecha = v_max.fecha INNER JOIN vehiculo AS v ON i.patente = v.patente INNER JOIN estado AS e ON e.id_estado = i.id_estado INNER JOIN cliente AS c ON c.dni = v.dni GROUP BY i.patente;")
    List<Inspeccion> findVehiculoEstadoActual();

    @Query(nativeQuery = true, value = "SELECT * FROM inspeccion AS i WHERE i.fecha BETWEEN :start AND :end GROUP BY i.patente;")
    List<Inspeccion> findFechaBetween(@Param("start") LocalDateTime fechaInicio, @Param("end") LocalDateTime fechaFin);

    @Query(nativeQuery = true, value = "SELECT * FROM inspeccion AS i INNER JOIN inspector AS ins ON ins.dni=i.dni_inspector WHERE i.fecha BETWEEN :start AND :end AND i.dni_inspector=:dni_inspector")
    List<Inspeccion> findInspectorFechaBetween(@Param("start") LocalDateTime fechaInicio,
            @Param("end") LocalDateTime fechaFin,
            @Param("dni_inspector") String dni_inspector);

    @Query(nativeQuery = true, value = "SELECT * FROM inspeccion AS i INNER JOIN vehiculo AS v ON i.patente=v.patente INNER JOIN cliente AS c ON v.dni=c.dni WHERE c.dni=:dni")
    List<Inspeccion> findCliente(@Param("dni") String dni);

    @Query(nativeQuery = true, value = "SELECT * FROM (SELECT MAX(i.fecha) AS fecha, v.patente FROM inspeccion AS i INNER JOIN vehiculo AS v ON i.patente=v.patente WHERE i.nro_inspeccion<>:nro GROUP BY v.patente) v_max INNER JOIN inspeccion i ON i.patente=v_max.patente AND i.fecha=v_max.fecha INNER JOIN vehiculo as v ON i.patente=v.patente INNER JOIN estado AS e ON e.id_estado=i.id_estado INNER JOIN cliente AS c ON c.dni=v.dni WHERE c.dni=:dni GROUP BY i.patente;")
    Inspeccion findLastInspeccion(@Param("dni") String dni, @Param("nro") int nro_inspeccion);
}
