package certantPrueba.vtv.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import certantPrueba.vtv.model.Inspeccion;
import certantPrueba.vtv.model.Vehiculo;
import certantPrueba.vtv.repository.InspeccionRepository;
import certantPrueba.vtv.repository.VehiculoRepository;
import certantPrueba.vtv.service.InspeccionServiceImpl;
import certantPrueba.vtv.service.VehiculoServiceImpl;

@SpringBootTest
public class VehiculoControllerTest {

    @InjectMocks
    private VehiculoServiceImpl vehiculoService;

    @InjectMocks
    private InspeccionServiceImpl inspeccionService;

    @Autowired
    private VehiculoController vehiculoController;

    @Mock
    private VehiculoRepository vehiculoRepository;

    @Mock
    private InspeccionRepository inspeccionRepository;

    private Vehiculo vehiculo;

    @BeforeEach
    void setUp() {
        vehiculo = new Vehiculo();
        vehiculo.setPatente("AEW 21 DSA");
        vehiculo.setMarca("Opel");
        vehiculo.setModelo("Corsa");
        vehiculo.setColor("Blanco");

        when(vehiculoRepository.save(any(Vehiculo.class))).thenReturn(vehiculo);

    }

    @Test
    void testFindById() throws Exception {
        Optional<Vehiculo> vOptional = Optional.of(vehiculo);
        when(vehiculoRepository.findById(any(String.class))).thenReturn(vOptional);
        Vehiculo v = vehiculoController.findById(vehiculoService, vehiculo);
        assertEquals(v, vehiculo);
    }

    @Test
    void testDelete() throws Exception {
        Vehiculo v = vehiculoController.save(vehiculoService, vehiculo);
        assertTrue(vehiculoController.delete(vehiculoService, v));
    }

    @Test
    void testFindAll() throws Exception {
        when(vehiculoRepository.findAll()).thenReturn(Arrays.asList(vehiculo));
        assertNotNull(vehiculoController.findAll(vehiculoService));
    }

    @Test
    void testFindAllEstados() throws Exception {
        Inspeccion inspeccion = new Inspeccion();
        when(inspeccionRepository.findVehiculoEstadoActual()).thenReturn(Arrays.asList(inspeccion));
        assertNotNull(vehiculoController.findAllEstados(inspeccionService));
    }

    @Test
    void testFindUltimaSemana() throws Exception {
        List<Inspeccion> inspecciones = new ArrayList<>();
        Inspeccion inspeccion = new Inspeccion();
        inspeccion.setFecha(LocalDateTime.now());
        LocalDateTime fechaFin = LocalDateTime.now();
        LocalDateTime fechaInicio = fechaFin.minusDays(7);

        when(inspeccionRepository.findFechaBetween(fechaInicio, fechaFin))
                .thenReturn(Arrays.asList(inspeccion));
        inspecciones = vehiculoController.findUltimaSemana(inspeccionService);
        assertNotNull(inspecciones);
        assertEquals(LocalDate.now(), inspecciones.get(0).getFecha());
    }

    @Test
    void testSave() throws Exception {
        when(vehiculoRepository.save(any(Vehiculo.class))).thenReturn(vehiculo);
        assertNotNull(vehiculoController.save(vehiculoService, vehiculo));
    }

    @Test
    void testUpdate() throws Exception {
        Vehiculo oldVehiculo = new Vehiculo();
        oldVehiculo.setPatente(vehiculo.getPatente());
        oldVehiculo.setColor(vehiculo.getColor());

        Optional<Vehiculo> vOptional = Optional.of(vehiculo);
        when(vehiculoRepository.findById(any(String.class))).thenReturn(vOptional);

        Vehiculo vehiculoToUpdate = new Vehiculo();
        vehiculoToUpdate.setPatente(vehiculo.getPatente());
        vehiculoToUpdate.setColor("Negro");

        when(vehiculoRepository.save(vehiculoToUpdate)).thenReturn(vehiculoToUpdate);

        Vehiculo vehiculoUpdated = vehiculoController.update(vehiculoService, vehiculoToUpdate);

        assertEquals(vehiculoUpdated.getPatente(), oldVehiculo.getPatente());
        assertEquals(vehiculoUpdated.getColor(), vehiculoToUpdate.getColor());
        assertNotEquals(vehiculoUpdated.getColor(), oldVehiculo.getColor());
    }
}
