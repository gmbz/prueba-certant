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
import certantPrueba.vtv.model.Inspeccion;
import certantPrueba.vtv.model.Vehiculo;
import certantPrueba.vtv.repository.ClienteRepository;
import certantPrueba.vtv.repository.InspeccionRepository;
import certantPrueba.vtv.repository.VehiculoRepository;
import certantPrueba.vtv.service.ClienteServiceImpl;
import certantPrueba.vtv.service.InspeccionServiceImpl;
import certantPrueba.vtv.service.VehiculoServiceImpl;

@SpringBootTest
public class ClienteControllerTest {

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @InjectMocks
    private InspeccionServiceImpl inspeccionService;

    @InjectMocks
    private VehiculoServiceImpl vehiculoService;

    @Autowired
    private ClienteController clienteController;

    @Mock
    private InspeccionRepository inspeccionRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private VehiculoRepository vehiculoRepository;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setId_cliente(1);
        cliente.setDni("11333555");
        cliente.setNombre("Carlos");
        cliente.setApellido("Tevez");
        cliente.setEmail("carlitos@gmail.com");
        cliente.setTelefono("1010101010");

        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
    }

    @Test
    void testFindById() throws Exception {
        Optional<Cliente> clienteOptional = Optional.of(cliente);
        when(clienteRepository.findById(cliente.getId_cliente())).thenReturn(clienteOptional);
        Cliente cli = clienteController.findById(clienteService, cliente);
        assertEquals(cli, cliente);
    }

    @Test
    void testFindAll() throws Exception {
        when(clienteRepository.findAll()).thenReturn(Arrays.asList(cliente));
        assertNotNull(clienteController.findAll(clienteService));
    }

    @Test
    void testSave() throws Exception {
        assertNotNull(clienteController.save(clienteService, new Cliente()));
    }

    @Test
    void testDelete() throws Exception {
        Cliente cli = clienteController.save(clienteService, new Cliente());
        assertTrue(clienteController.delete(clienteService, cli));
    }

    @Test
    void testUpdate() throws Exception {
        Cliente oldCliente = new Cliente();
        oldCliente.setId_cliente(cliente.getId_cliente());
        oldCliente.setNombre(cliente.getNombre());

        Optional<Cliente> clienteOptional = Optional.of(cliente);
        when(clienteRepository.findById(cliente.getId_cliente())).thenReturn(clienteOptional);

        Cliente clienteToUpdate = new Cliente();
        clienteToUpdate.setId_cliente(cliente.getId_cliente());
        clienteToUpdate.setNombre("Martin");

        when(clienteRepository.save(clienteToUpdate)).thenReturn(clienteToUpdate);

        Cliente clienteUpdated = clienteController.update(clienteService, clienteToUpdate);

        assertEquals(clienteUpdated.getId_cliente(), oldCliente.getId_cliente());
        assertEquals(clienteUpdated.getNombre(), clienteToUpdate.getNombre());
        assertNotEquals(clienteUpdated.getNombre(), oldCliente.getNombre());

    }

    @Test
    void testGetInspecciones() throws Exception {
        when(clienteRepository.countVehiculos(any(Integer.class))).thenReturn(3);
        Inspeccion inspeccion = new Inspeccion();
        inspeccion.setNro_inspeccion(1);
        inspeccion.setFecha(LocalDateTime.now());
        when(inspeccionRepository.findCliente(any(Integer.class))).thenReturn(Arrays.asList(inspeccion));
        assertNotNull(clienteController.getInspecciones(inspeccionService, clienteService, cliente));
        when(clienteRepository.countVehiculos(any(Integer.class))).thenReturn(1);
        assertNull(clienteController.getInspecciones(inspeccionService, clienteService, cliente));
    }

    @Test
    void testFindAllbyCliente() throws Exception {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setPatente("ADS 11 VV");
        when(vehiculoRepository.findAllByCliente(cliente)).thenReturn(Arrays.asList(vehiculo));
        assertNotNull(clienteController.findAllbyCliente(vehiculoService, cliente));
    }

}
