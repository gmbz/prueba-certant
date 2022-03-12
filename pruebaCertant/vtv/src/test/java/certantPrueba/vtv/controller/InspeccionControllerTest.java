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
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import certantPrueba.vtv.model.Cliente;
import certantPrueba.vtv.model.EstadoInspeccion;
import certantPrueba.vtv.model.Inspeccion;
import certantPrueba.vtv.model.Medicion;
import certantPrueba.vtv.model.Observacion;
import certantPrueba.vtv.model.TipoCliente;
import certantPrueba.vtv.model.Vehiculo;
import certantPrueba.vtv.repository.EstadoInspeccionRepository;
import certantPrueba.vtv.repository.InspeccionRepository;
import certantPrueba.vtv.service.EstadoInspeecionImpl;
import certantPrueba.vtv.service.InspeccionServiceImpl;

@SpringBootTest
public class InspeccionControllerTest {

    @InjectMocks
    private InspeccionServiceImpl inspeccionService;

    @InjectMocks
    private EstadoInspeecionImpl estadoInspecionService;

    @Autowired
    private InspeccionController inspeccionController;

    @Mock
    private InspeccionRepository inspeccionRepository;

    @Mock
    private EstadoInspeccionRepository estadoInspeccionRepository;

    private Inspeccion inspeccion;
    private Observacion observacion;
    private Medicion medicion;
    private Vehiculo vehiculo;
    private Cliente cliente;
    private EstadoInspeccion estadoInspeccion;
    private TipoCliente tipoCliente;
    private TipoCliente tipoCliente2;

    @BeforeEach
    void setUp() {
        medicion = new Medicion();
        medicion.setContaminacion("APTO");
        medicion.setFrenos("APTO");
        medicion.setTren_delantero("APTO");
        medicion.setSuspencion("APTO");

        observacion = new Observacion();
        observacion.setChasis("APTO");
        observacion.setEspejos("APTO");
        observacion.setEmergencia("APTO");
        observacion.setLuces("APTO");
        observacion.setPatente("APTO");
        observacion.setVidrios_seguridad("APTO");

        tipoCliente2 = new TipoCliente();
        tipoCliente2.setId_tipo(2);
        tipoCliente2.setDescripcion("Comun");

        tipoCliente = new TipoCliente();
        tipoCliente.setId_tipo(1);
        tipoCliente.setDescripcion("Extento");

        cliente = new Cliente();
        cliente.setId_cliente(1);
        cliente.setNombre("Leonel");
        cliente.setApellido("Messi");
        cliente.setTipo(tipoCliente);

        vehiculo = new Vehiculo();
        vehiculo.setPatente("ADW 12 DD");
        vehiculo.setCliente(cliente);

        estadoInspeccion = new EstadoInspeccion();
        estadoInspeccion.setId_estado(0);
        estadoInspeccion.setDescripcion("APTO");

        inspeccion = new Inspeccion();
        inspeccion.setObservacion(observacion);
        inspeccion.setMedicion(medicion);
        inspeccion.setVehiculo(vehiculo);
        inspeccion.setEstado(estadoInspeccion);
        inspeccion.setNro_inspeccion(1);
        inspeccion.setFecha(LocalDateTime.now().minusDays(3));

        when(inspeccionRepository.save(any(Inspeccion.class))).thenReturn(inspeccion);
    }

    @Test
    void testFinalizarInspeccion() throws Exception {
        EstadoInspeccion estadoInspeccion = new EstadoInspeccion();
        estadoInspeccion.setDescripcion("APTO");
        EstadoInspeccion estadoInspeccion2 = new EstadoInspeccion();
        estadoInspeccion2.setDescripcion("RECHAZADO");
        EstadoInspeccion estadoInspeccion3 = new EstadoInspeccion();
        estadoInspeccion3.setDescripcion("CONDICIONAL");
        when(estadoInspeccionRepository.findByDescripcion("APTO")).thenReturn(estadoInspeccion);
        when(estadoInspeccionRepository.findByDescripcion("RECHAZADO")).thenReturn(estadoInspeccion2);
        when(estadoInspeccionRepository.findByDescripcion("CONDICIONAL")).thenReturn(estadoInspeccion3);
        Inspeccion i;

        when(inspeccionRepository.findLastInspeccion(any(Integer.class), any(Integer.class))).thenReturn(inspeccion);

        i = inspeccionController.finalizarInspeccion(inspeccionService, estadoInspecionService, inspeccion);
        assertEquals("APTO", i.getEstado().getDescripcion());
        assertNotNull(i.getVehiculo().getOblea());

        inspeccion.getObservacion().setChasis("CONDICIONAL");
        inspeccion.getVehiculo().setOblea(null);
        i = inspeccionController.finalizarInspeccion(inspeccionService, estadoInspecionService, inspeccion);
        assertEquals("CONDICIONAL", i.getEstado().getDescripcion());
        assertNull(i.getVehiculo().getOblea());

        inspeccion.getObservacion().setChasis("RECHAZADO");
        inspeccion.getVehiculo().setOblea(null);
        i = inspeccionController.finalizarInspeccion(inspeccionService, estadoInspecionService, inspeccion);
        assertEquals("RECHAZADO", i.getEstado().getDescripcion());
        assertNull(i.getVehiculo().getOblea());

        i = inspeccionController.finalizarInspeccion(inspeccionService, estadoInspecionService, inspeccion);
        assertEquals(inspeccion.isDebe_pagar(), false);

        Cliente cli = new Cliente();
        cli.setId_cliente(2);
        cli.setNombre("Rodrigo");
        cli.setApellido("De Paul");
        cli.setTipo(tipoCliente2);

        inspeccion.getVehiculo().setCliente(cli);
        i = inspeccionController.finalizarInspeccion(inspeccionService, estadoInspecionService, inspeccion);
        assertEquals(inspeccion.isDebe_pagar(), true);

        inspeccion.setFecha(LocalDateTime.now().minusHours(3));
        i = inspeccionController.finalizarInspeccion(inspeccionService, estadoInspecionService, inspeccion);
        assertEquals(inspeccion.isDebe_pagar(), true);

        inspeccion.setFecha(LocalDateTime.now().minusHours(3));
        inspeccion.getEstado().setDescripcion("CONDICIONAL");
        i = inspeccionController.finalizarInspeccion(inspeccionService, estadoInspecionService, inspeccion);
        assertEquals(inspeccion.isDebe_pagar(), false);
    }

    @Test
    void testDelete() throws Exception {
        Inspeccion ins = inspeccionController.save(inspeccionService, inspeccion);
        assertTrue(inspeccionController.delete(inspeccionService, ins));
    }

    @Test
    void testFindAll() throws Exception {
        when(inspeccionRepository.findAll()).thenReturn(Arrays.asList(inspeccion));
        assertNotNull(inspeccionController.findAll(inspeccionService));
    }

    @Test
    void testFindById() throws Exception {
        Optional<Inspeccion> iOptional = Optional.of(inspeccion);
        when(inspeccionRepository.findById(any(Integer.class))).thenReturn(iOptional);
        Inspeccion ins = inspeccionController.findById(inspeccionService, inspeccion);
        assertEquals(ins, inspeccion);
    }

    @Test
    void testSave() throws Exception {
        assertNotNull(inspeccionController.save(inspeccionService, inspeccion));
    }

    @Test
    void testUpdate() throws Exception {
        Inspeccion oldInspeccion = new Inspeccion();
        oldInspeccion.setNro_inspeccion(inspeccion.getNro_inspeccion());
        oldInspeccion.setVehiculo(inspeccion.getVehiculo());

        Optional<Inspeccion> iOptional = Optional.of(inspeccion);
        when(inspeccionRepository.findById(any(Integer.class))).thenReturn(iOptional);

        Inspeccion inspeccionToUpdate = new Inspeccion();
        inspeccionToUpdate.setNro_inspeccion(inspeccion.getNro_inspeccion());
        Vehiculo v = new Vehiculo();
        v.setPatente("ZZZ 12 SA");
        inspeccionToUpdate.setVehiculo(v);

        when(inspeccionRepository.save(inspeccionToUpdate)).thenReturn(inspeccionToUpdate);

        Inspeccion inspeccionUpdated = inspeccionController.update(inspeccionService, inspeccionToUpdate);
        assertEquals(inspeccionUpdated.getNro_inspeccion(), oldInspeccion.getNro_inspeccion());
        assertEquals(inspeccionUpdated.getVehiculo().getPatente(), inspeccionToUpdate.getVehiculo().getPatente());
        assertNotEquals(inspeccionUpdated.getVehiculo().getPatente(), oldInspeccion.getVehiculo().getPatente());

    }

    @Test
    void testUpdateMedicion() throws Exception {
        Inspeccion oldInspeccion = new Inspeccion();
        oldInspeccion.setNro_inspeccion(inspeccion.getNro_inspeccion());
        oldInspeccion.setMedicion(inspeccion.getMedicion());

        Optional<Inspeccion> iOptional = Optional.of(inspeccion);
        when(inspeccionRepository.findById(any(Integer.class))).thenReturn(iOptional);

        Inspeccion inspeccionToUpdate = new Inspeccion();
        inspeccionToUpdate.setNro_inspeccion(inspeccion.getNro_inspeccion());
        Medicion med = new Medicion();
        med.setContaminacion("CONDICIONAL");
        inspeccionToUpdate.setMedicion(med);

        when(inspeccionRepository.save(inspeccionToUpdate)).thenReturn(inspeccionToUpdate);
        Inspeccion inspeccionUpdated = inspeccionController.updateMedicion(inspeccionService, inspeccionToUpdate);
        assertEquals(inspeccionUpdated.getNro_inspeccion(), oldInspeccion.getNro_inspeccion());
        assertEquals(inspeccionUpdated.getMedicion().getContaminacion(),
                inspeccionToUpdate.getMedicion().getContaminacion());
        assertNotEquals(inspeccionUpdated.getMedicion().getContaminacion(),
                oldInspeccion.getMedicion().getContaminacion());
    }

    @Test
    void testUpdateObservacion() throws Exception {
        Inspeccion oldInspeccion = new Inspeccion();
        oldInspeccion.setNro_inspeccion(inspeccion.getNro_inspeccion());
        oldInspeccion.setObservacion(inspeccion.getObservacion());

        Optional<Inspeccion> iOptional = Optional.of(inspeccion);
        when(inspeccionRepository.findById(any(Integer.class))).thenReturn(iOptional);

        Inspeccion inspeccionToUpdate = new Inspeccion();
        inspeccionToUpdate.setNro_inspeccion(inspeccion.getNro_inspeccion());
        Observacion obs = new Observacion();
        obs.setEmergencia("CONDICIONAL");
        inspeccionToUpdate.setObservacion(obs);

        when(inspeccionRepository.save(inspeccionToUpdate)).thenReturn(inspeccionToUpdate);
        Inspeccion inspeccionUpdated = inspeccionController.updateObservacion(inspeccionService, inspeccionToUpdate);
        assertEquals(inspeccionUpdated.getNro_inspeccion(), oldInspeccion.getNro_inspeccion());
        assertEquals(inspeccionUpdated.getObservacion().getEmergencia(),
                inspeccionToUpdate.getObservacion().getEmergencia());
        assertNotEquals(inspeccionUpdated.getObservacion().getEmergencia(),
                oldInspeccion.getObservacion().getEmergencia());
    }
}
