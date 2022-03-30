package certantPrueba.vtv.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import certantPrueba.vtv.model.Cliente;
import certantPrueba.vtv.model.EstadoInspeccion;
import certantPrueba.vtv.model.Inspeccion;
import certantPrueba.vtv.model.Inspector;
import certantPrueba.vtv.model.Medicion;
import certantPrueba.vtv.model.Observacion;
import certantPrueba.vtv.model.Persona;
import certantPrueba.vtv.model.TipoCliente;
import certantPrueba.vtv.model.Vehiculo;
import certantPrueba.vtv.repository.ClienteRepository;
import certantPrueba.vtv.repository.EstadoInspeccionRepository;
import certantPrueba.vtv.repository.InspeccionRepository;
import certantPrueba.vtv.repository.VehiculoRepository;
import certantPrueba.vtv.service.EstadoInspeecionImpl;
import certantPrueba.vtv.service.InspeccionServiceImpl;
import certantPrueba.vtv.service.InspectorServiceImpl;
import certantPrueba.vtv.service.VehiculoServiceImpl;

@SpringBootTest
public class InspeccionControllerTest {

    @Autowired
    @InjectMocks
    private InspeccionServiceImpl inspeccionService;

    @Autowired
    @InjectMocks
    private EstadoInspeecionImpl estadoInspecionService;

    @Autowired
    @InjectMocks
    private VehiculoServiceImpl vehiculoService;

    @Autowired
    @InjectMocks
    private InspectorServiceImpl inspectorService;

    @Autowired
    private InspeccionController inspeccionController;

    @Autowired
    private VehiculoController vehiculoController;

    @Autowired
    private InspectorController inspectorController;

    @Spy
    private InspeccionRepository inspeccionRepository;

    @Spy
    private VehiculoRepository vehiculoRepository;

    @Spy
    private ClienteRepository clienteRepository;

    @Spy
    private EstadoInspeccionRepository estadoInspeccionRepository;

    // private Inspeccion inspeccion;
    // private Observacion observacion;
    // private Medicion medicion;
    // private Vehiculo vehiculo;
    // private Cliente cliente;
    // private EstadoInspeccion estadoInspeccion;
    // private TipoCliente tipoCliente;
    // private TipoCliente tipoCliente2;

    @BeforeEach
    void setUp() {
        // medicion = new Medicion();
        // medicion.setContaminacion("APTO");
        // medicion.setFrenos("APTO");
        // medicion.setTren_delantero("APTO");
        // medicion.setSuspencion("APTO");

        // observacion = new Observacion();
        // observacion.setChasis("APTO");
        // observacion.setEspejos("APTO");
        // observacion.setEmergencia("APTO");
        // observacion.setLuces("APTO");
        // observacion.setPatente("APTO");
        // observacion.setVidrios_seguridad("APTO");

        // tipoCliente2 = new TipoCliente();
        // tipoCliente2.setId_tipo(2);
        // tipoCliente2.setDescripcion("Comun");

        // tipoCliente = new TipoCliente();
        // tipoCliente.setId_tipo(1);
        // tipoCliente.setDescripcion("Extento");

        // cliente = new Cliente();
        // cliente.setDni("10111000");
        // cliente.setNombre("Leonel");
        // cliente.setApellido("Messi");
        // cliente.setTipo(tipoCliente);

        // vehiculo = new Vehiculo();
        // vehiculo.setPatente("ASV 01 EN");
        // vehiculo.setCliente(cliente);

        // estadoInspeccion = new EstadoInspeccion();
        // estadoInspeccion.setId_estado(0);
        // estadoInspeccion.setDescripcion("APTO");

        // inspeccion = new Inspeccion();
        // inspeccion.setObservacion(observacion);
        // inspeccion.setMedicion(medicion);
        // inspeccion.setVehiculo(vehiculo);
        // inspeccion.setEstado(estadoInspeccion);
        // inspeccion.setNro_inspeccion(1);
        // inspeccion.setFecha(LocalDateTime.now().minusDays(3));

    }

    @Test
    void testSave() throws Exception {
        // Vehiculo v = new Vehiculo();
        // v.setPatente("AJX 03 HO");
        // Vehiculo vehiculoFinded = vehiculoController.findById(vehiculoService, v);
        // vehiculoFinded.setOblea(null);
        // Inspector inspector = new Inspector();
        // inspector.setDni("99888999");
        // Inspector inspectorFinded = inspectorController.findById(inspectorService,
        //         inspector);

        // Inspeccion newInspeccion = new Inspeccion();
        // newInspeccion.setInspector(inspectorFinded);
        // newInspeccion.setVehiculo(vehiculoFinded);

        // Inspeccion inspeccionSaved = inspeccionService.save(newInspeccion);
        // assertNotNull(inspeccionSaved);
        // assertEquals(inspeccionSaved.getVehiculo().getPatente(),
        // vehiculoFinded.getPatente());
        // assertEquals(inspeccionSaved.getInspector().getDni(),
        // inspectorFinded.getDni());
    }

    @Test
    void testFinalizarInspeccion() throws Exception {
        // EstadoInspeccion estadoInspeccion = new EstadoInspeccion();
        // estadoInspeccion.setDescripcion("APTO");
        // EstadoInspeccion estadoInspeccion2 = new EstadoInspeccion();
        // estadoInspeccion2.setDescripcion("RECHAZADO");
        // EstadoInspeccion estadoInspeccion3 = new EstadoInspeccion();
        // estadoInspeccion3.setDescripcion("CONDICIONAL");
        // when(estadoInspeccionRepository.findByDescripcion("APTO")).thenReturn(estadoInspeccion);
        // when(estadoInspeccionRepository.findByDescripcion("RECHAZADO")).thenReturn(estadoInspeccion2);
        // when(estadoInspeccionRepository.findByDescripcion("CONDICIONAL")).thenReturn(estadoInspeccion3);

        // Inspeccion inspeccionTerminada =
        // inspeccionController.finalizarInspeccion(inspeccionService,
        // estadoInspecionService, inspeccion);
        // assertEquals("APTO", inspeccionTerminada.getEstado().getDescripcion());

        // Inspeccion i;

        // when(inspeccionRepository.findLastInspeccion(any(String.class),
        // any(Integer.class))).thenReturn(inspeccion);

        // i = inspeccionController.finalizarInspeccion(inspeccionService,
        // estadoInspecionService, inspeccion);
        // assertEquals("APTO", i.getEstado().getDescripcion());
        // assertNotNull(i.getVehiculo().getOblea());

        // inspeccion.getObservacion().setChasis("CONDICIONAL");
        // inspeccion.getVehiculo().setOblea(null);
        // i = inspeccionController.finalizarInspeccion(inspeccionService,
        // estadoInspecionService, inspeccion);
        // assertEquals("CONDICIONAL", i.getEstado().getDescripcion());
        // assertNull(i.getVehiculo().getOblea());

        // inspeccion.getObservacion().setChasis("RECHAZADO");
        // inspeccion.getVehiculo().setOblea(null);
        // i = inspeccionController.finalizarInspeccion(inspeccionService,
        // estadoInspecionService, inspeccion);
        // assertEquals("RECHAZADO", i.getEstado().getDescripcion());
        // assertNull(i.getVehiculo().getOblea());

        // i = inspeccionController.finalizarInspeccion(inspeccionService,
        // estadoInspecionService, inspeccion);
        // assertEquals(inspeccion.isDebe_pagar(), false);

        // Cliente cli = new Cliente();
        // cli.setDni("07000777");
        // cli.setNombre("Rodrigo");
        // cli.setApellido("De Paul");
        // cli.setTipo(tipoCliente2);

        // inspeccion.getVehiculo().setCliente(cli);
        // i = inspeccionController.finalizarInspeccion(inspeccionService,
        // estadoInspecionService, inspeccion);
        // assertEquals(inspeccion.isDebe_pagar(), true);

        // inspeccion.setFecha(LocalDateTime.now().minusHours(3));
        // i = inspeccionController.finalizarInspeccion(inspeccionService,
        // estadoInspecionService, inspeccion);
        // assertEquals(inspeccion.isDebe_pagar(), true);

        // inspeccion.setFecha(LocalDateTime.now().minusHours(3));
        // inspeccion.getEstado().setDescripcion("CONDICIONAL");
        // i = inspeccionController.finalizarInspeccion(inspeccionService,
        // estadoInspecionService, inspeccion);
        // assertEquals(inspeccion.isDebe_pagar(), false);
    }

    @Test
    void testDelete() throws Exception {
        Inspeccion inspeccionToDelete = new Inspeccion();
        inspeccionToDelete.setNro_inspeccion(20);
        Inspeccion inspeccionFinded = inspeccionController.findById(inspeccionService, inspeccionToDelete);
        assertTrue(inspeccionController.delete(inspeccionService, inspeccionFinded));
    }

    @Test
    void testFindAll() throws Exception {
        List<Inspeccion> inspecciones = inspeccionController.findAll(inspeccionService);
        assertNotNull(inspecciones);
    }

    @Test
    void testFindById() throws Exception {
        Inspeccion inspeccionToFind = new Inspeccion();
        inspeccionToFind.setNro_inspeccion(1);
        Inspeccion inspeccionFinded = inspeccionController.findById(inspeccionService, inspeccionToFind);
        assertEquals(inspeccionFinded.getNro_inspeccion(), inspeccionToFind.getNro_inspeccion());

    }

    @Test
    void testUpdate() throws Exception {
        Inspeccion inspeccionToFind = new Inspeccion();
        inspeccionToFind.setNro_inspeccion(1);

        Inspeccion inspeccionFinded = inspeccionController.findById(inspeccionService, inspeccionToFind);

        Inspector oldInspector = new Inspector();
        oldInspector.setDni(inspeccionFinded.getInspector().getDni());

        Inspector inspectorToFind = new Inspector();
        inspectorToFind.setDni("99444999");

        Inspector inspectorFinded = inspectorController.findById(inspectorService, inspectorToFind);

        inspeccionFinded.setInspector(inspectorFinded);

        Inspeccion inspeccionUpdated = inspeccionController.update(inspeccionService, inspeccionFinded);

        assertEquals(inspeccionUpdated.getNro_inspeccion(), inspeccionFinded.getNro_inspeccion());
        assertEquals(inspeccionUpdated.getInspector().getNombre(), inspeccionFinded.getInspector().getNombre());
        assertNotEquals(inspeccionUpdated.getInspector().getDni(), oldInspector.getDni());

    }

    @Test
    void testUpdateMedicion() throws Exception {
        Inspeccion inspeccionToUpdate = new Inspeccion();
        inspeccionToUpdate.setNro_inspeccion(1);

        Medicion medicion = new Medicion();
        medicion.setContaminacion("RECHAZADO");
        medicion.setFrenos("APTO");
        medicion.setSuspencion("APTO");
        medicion.setTren_delantero("APTO");

        inspeccionToUpdate.setMedicion(medicion);

        Inspeccion inspeccionUpdated = inspeccionController.updateMedicion(inspeccionService, inspeccionToUpdate);

        assertEquals(inspeccionUpdated.getNro_inspeccion(), inspeccionToUpdate.getNro_inspeccion());
        assertEquals(inspeccionUpdated.getMedicion().getContaminacion(),
                inspeccionToUpdate.getMedicion().getContaminacion());

    }

    @Test
    void testUpdateObservacion() throws Exception {
        Inspeccion inspeccionToUpdate = new Inspeccion();
        inspeccionToUpdate.setNro_inspeccion(1);

        Observacion observacion = new Observacion();
        observacion.setChasis("CONDICIONAL");
        observacion.setEmergencia("APTO");
        observacion.setEspejos("APTO");
        observacion.setLuces("APTO");
        observacion.setPatente("APTO");
        observacion.setVidrios_seguridad("APTO");

        inspeccionToUpdate.setObservacion(observacion);

        Inspeccion inspeccionUpdated = inspeccionController.updateObservacion(inspeccionService, inspeccionToUpdate);

        assertEquals(inspeccionUpdated.getNro_inspeccion(), inspeccionToUpdate.getNro_inspeccion());
        assertEquals(inspeccionUpdated.getObservacion().getChasis(), inspeccionToUpdate.getObservacion().getChasis());

    }
}
