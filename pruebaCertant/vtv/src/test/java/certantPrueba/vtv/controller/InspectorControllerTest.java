package certantPrueba.vtv.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import certantPrueba.vtv.model.Inspeccion;
import certantPrueba.vtv.model.Inspector;
import certantPrueba.vtv.repository.InspeccionRepository;
import certantPrueba.vtv.repository.InspectorRepository;
import certantPrueba.vtv.service.InspeccionServiceImpl;
import certantPrueba.vtv.service.InspectorServiceImpl;

@SpringBootTest
public class InspectorControllerTest {

    @Autowired
    @InjectMocks
    private InspectorServiceImpl inspectorService;

    @Autowired
    @InjectMocks
    private InspeccionServiceImpl inspeccionService;

    @Autowired
    private InspectorController inspectorController;

    @Spy
    private InspectorRepository inspectorRepository;

    @Spy
    private InspeccionRepository inspeccionRepository;

    private Inspector inspector;

    @BeforeEach
    void setUp() {
        inspector = new Inspector();
        inspector.setLegajo(4321);
        inspector.setDni("55999555");
        inspector.setNombre("Ramon");
        inspector.setApellido("Ramirez");
        inspector.setTelefono("432412465");
        inspector.setEmail("rr@gmail.com");

    }

    @Test
    void testSave() throws Exception {
        Inspector inspectorSaved = inspectorController.save(inspectorService, inspector);
        assertNotNull(inspectorSaved);
        assertEquals(inspectorSaved.getDni(), inspector.getDni());
        assertEquals(inspectorSaved.getNombre(), inspector.getNombre());
    }

    @Test
    void testDelete() throws Exception {
        assertTrue(inspectorController.delete(inspectorService, inspector));
    }

    @Test
    void testFindAll() throws Exception {
        List<Inspector> inspectores = inspectorController.findAll(inspectorService);
        assertNotNull(inspectores);
    }

    @Test
    void testFindById() throws Exception {
        Inspector inspectorToFind = new Inspector();
        inspectorToFind.setDni("55999555");
        Inspector inspectorFInded = inspectorController.findById(inspectorService, inspectorToFind);
        assertEquals(inspectorFInded.getDni(), inspector.getDni());

    }

    @Test
    void testInspeccionesUltimosTresDias() throws Exception {
        List<Inspeccion> inspecciones = inspectorController.inspeccionesUltimosTresDias(inspeccionService, inspector);
        assertNotNull(inspecciones);

    }

    @Test
    void testUpdate() throws Exception {
        inspectorController.save(inspectorService, inspector);
        Inspector inspectorToUpdate = new Inspector();
        inspectorToUpdate.setDni(inspector.getDni());
        inspectorToUpdate.setNombre("Mariano");

        Inspector inspectorFinded = inspectorController.findById(inspectorService, inspectorToUpdate);
        inspectorFinded.setNombre(inspectorToUpdate.getNombre());

        Inspector inspectorUpdated = inspectorController.update(inspectorService, inspectorFinded);

        assertEquals(inspectorUpdated.getDni(), inspectorToUpdate.getDni());
        assertEquals(inspectorUpdated.getNombre(), inspectorToUpdate.getNombre());
        assertNotEquals(inspectorUpdated.getNombre(), inspector.getNombre());

    }
}
