package certantPrueba.vtv.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import certantPrueba.vtv.exception.BadRequestException;
import certantPrueba.vtv.model.Auto;
import certantPrueba.vtv.model.Cliente;
import certantPrueba.vtv.model.Inspeccion;
import certantPrueba.vtv.model.Modelo;
import certantPrueba.vtv.model.Vehiculo;
import certantPrueba.vtv.repository.InspeccionRepository;
import certantPrueba.vtv.repository.ModeloRepository;
import certantPrueba.vtv.repository.VehiculoRepository;
import certantPrueba.vtv.service.ClienteServiceImpl;
import certantPrueba.vtv.service.InspeccionServiceImpl;
import certantPrueba.vtv.service.VehiculoServiceImpl;

@SpringBootTest
public class VehiculoControllerTest {

    @Autowired
    @InjectMocks
    private VehiculoServiceImpl vehiculoService;

    @Autowired
    @InjectMocks
    private InspeccionServiceImpl inspeccionService;

    @Autowired
    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Autowired
    private ClienteController clienteController;

    @Autowired
    private VehiculoController vehiculoController;

    @Spy
    private VehiculoRepository vehiculoRepository;

    @Spy
    private InspeccionRepository inspeccionRepository;

    @Spy
    private ModeloRepository modeloRepository;

    private Vehiculo auto;
    private Cliente cliente;
    private Modelo modelo;

    @BeforeEach
    void setUp() throws Exception {
        Cliente clienteToFind = new Cliente();
        clienteToFind.setDni("10111000");

        cliente = clienteController.findById(clienteService, clienteToFind);

        Modelo modeloToFind = new Modelo();
        modeloToFind.setId_modelo(1);
        modelo = vehiculoController.findModeloById(vehiculoService, modeloToFind);

        auto = new Auto();
        auto.setPatente("AEW 21 DA");
        auto.setColor("Blanco");
        auto.setYear("2021");
        auto.setCliente(cliente);
        auto.setModelo(modelo);

    }

    @Test
    void testFindById() throws Exception {
        Vehiculo autoToFind = new Auto();
        autoToFind.setPatente("PSG 30 FR");

        Vehiculo autoFinded = vehiculoController.findById(vehiculoService, autoToFind);
        assertEquals(autoFinded.getPatente(), autoToFind.getPatente());
    }

    @Test
    void testDelete() throws Exception {
        assertTrue(vehiculoController.delete(vehiculoService, auto));
    }

    @Test
    void testFindAll() throws Exception {
        List<Vehiculo> vehiculos = vehiculoController.findAll(vehiculoService);
        assertNotNull(vehiculos);

    }

    @Test
    void testFindAllEstados() throws Exception {
        List<Inspeccion> inspeciones = vehiculoController.findAllEstados(inspeccionService);
        assertNotNull(inspeciones);
    }

    @Test
    void testSave() throws Exception {
        Vehiculo vehiculoSaved = vehiculoController.save(vehiculoService, auto);
        assertNotNull(vehiculoSaved);
        assertEquals(vehiculoSaved.getCliente().getDni(), auto.getCliente().getDni());
        assertEquals(vehiculoSaved.getPatente(), auto.getPatente());

        Vehiculo autoMalPatente = new Auto();
        autoMalPatente.setPatente("Q 21 DA");
        autoMalPatente.setColor("Blanco");
        autoMalPatente.setCliente(cliente);

        assertThrows(BadRequestException.class, () -> vehiculoController.save(vehiculoService, autoMalPatente));

    }

    @Test
    void testUpdate() throws Exception {

        vehiculoController.save(vehiculoService, auto);
        Vehiculo autoToUpdate = new Auto();
        autoToUpdate.setPatente(auto.getPatente());
        autoToUpdate.setColor("Rojo");

        Vehiculo autoFinded = vehiculoController.findById(vehiculoService, auto);
        autoFinded.setColor(autoToUpdate.getColor());

        Vehiculo autoUpdated = vehiculoController.update(vehiculoService, autoFinded);

        assertEquals(autoUpdated.getPatente(), autoToUpdate.getPatente());
        assertEquals(autoUpdated.getColor(), autoToUpdate.getColor());
        assertNotEquals(autoUpdated.getColor(), auto.getColor());
    }
}
