package certantPrueba.vtv.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
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

    @Autowired
    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Autowired
    @InjectMocks
    private InspeccionServiceImpl inspeccionService;

    @Autowired
    @InjectMocks
    private VehiculoServiceImpl vehiculoService;

    @Autowired
    @InjectMocks
    private ClienteController clienteController;

    @Spy
    private InspeccionRepository inspeccionRepository;

    @Spy
    private ClienteRepository clienteRepository;

    @Spy
    private VehiculoRepository vehiculoRepository;

    private Cliente cliente;
    private Cliente clienteExistente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setDni("44333444");
        cliente.setNombre("Carlos");
        cliente.setApellido("Tevez");
        cliente.setEmail("carlitos@gmail.com");
        cliente.setTelefono("1010101010");

        clienteExistente = new Cliente();
        clienteExistente.setDni("10111000");
        clienteExistente.setNombre("Lionel");
        clienteExistente.setApellido("Messi");
        clienteExistente.setEmail("messi10@gmail.com");
        clienteExistente.setTelefono("1010101010");
    }

    @Test
    void testFindById() throws Exception {
        Cliente clienteToFind = new Cliente();
        clienteToFind.setDni("10111000");
        Cliente clienteFinded = clienteController.findById(clienteService, clienteToFind);
        assertEquals(clienteFinded.getDni(), clienteToFind.getDni());
    }

    @Test
    void testFindAll() throws Exception {
        List<Cliente> listado = clienteController.findAll(clienteService);
        assertNotNull(listado);
    }

    @Test
    void testSave() throws Exception {
        Cliente cli = clienteController.save(clienteService, cliente);
        assertNotNull(cli);
        assertEquals(cli.getDni(), cliente.getDni());
        assertEquals(cli.getNombre(), cliente.getNombre());
    }

    @Test
    void testDelete() throws Exception {
        clienteController.save(clienteService, cliente);
        assertTrue(clienteController.delete(clienteService, cliente));

    }

    @Test
    void testUpdate() throws Exception {
        clienteController.save(clienteService, cliente);
        Cliente clienteToUpdate = new Cliente();
        clienteToUpdate.setDni(cliente.getDni());
        clienteToUpdate.setNombre("Marcos");

        Cliente cli = clienteController.findById(clienteService, clienteToUpdate);
        cli.setNombre(clienteToUpdate.getNombre());

        Cliente clienteUpdated = clienteController.update(clienteService, cli);

        assertEquals(clienteUpdated.getNombre(), clienteToUpdate.getNombre());
        assertEquals(clienteUpdated.getDni(), clienteToUpdate.getDni());
        assertNotEquals(clienteUpdated.getNombre(), cliente.getNombre());

    }

    @Test
    void testGetInspecciones() throws Exception {
        Cliente clienteInspecciones = clienteController.findById(clienteService, clienteExistente);

        List<Inspeccion> inspecciones = clienteController.getInspecciones(inspeccionService, clienteService,
                clienteInspecciones);
        assertNotNull(inspecciones);

        List<Inspeccion> inspecciones2 = clienteController.getInspecciones(inspeccionService, clienteService,
                cliente);
        assertNull(inspecciones2);

    }

    @Test
    void testFindAllbyCliente() throws Exception {

        List<Vehiculo> vehiculos = clienteController.findAllbyCliente(vehiculoService, clienteExistente);
        assertNotNull(vehiculos);

    }

}
