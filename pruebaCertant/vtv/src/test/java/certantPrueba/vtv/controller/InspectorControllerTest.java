package certantPrueba.vtv.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

import certantPrueba.vtv.model.Inspeccion;
import certantPrueba.vtv.model.Inspector;
import certantPrueba.vtv.repository.InspeccionRepository;
import certantPrueba.vtv.repository.InspectorRepository;
import certantPrueba.vtv.service.InspeccionServiceImpl;
import certantPrueba.vtv.service.InspectorServiceImpl;

@SpringBootTest
public class InspectorControllerTest {

    @InjectMocks
    private InspectorServiceImpl inspectorService;

    @InjectMocks
    private InspeccionServiceImpl inspeccionService;

    @Autowired
    private InspectorController inspectorController;

    @Mock
    private InspectorRepository inspectorRepository;

    @Mock
    private InspeccionRepository inspeccionRepository;

    private Inspector inspector;

    @BeforeEach
    void setUp() {
        inspector = new Inspector();
        inspector.setLegajo(4321);
        inspector.setNombre("Ramon");
        inspector.setApellido("Ramirez");
        inspector.setTelefono("432412465");
        inspector.setEmail("rr@gmail.com");

        when(inspectorRepository.save(any(Inspector.class))).thenReturn(inspector);
    }

    @Test
    void testDelete() throws Exception {
        Inspector i = inspectorController.save(inspectorService, inspector);
        assertTrue(inspectorController.delete(inspectorService, i));
    }

    @Test
    void testFindAll() throws Exception {
        when(inspectorRepository.findAll()).thenReturn(Arrays.asList(inspector));
        assertNotNull(inspectorController.findAll(inspectorService));
    }

    @Test
    void testFindById() throws Exception {
        Optional<Inspector> iOptional = Optional.of(inspector);
        when(inspectorRepository.findById(any(Integer.class))).thenReturn(iOptional);
        Inspector i = inspectorController.findById(inspectorService, inspector);
        assertEquals(i, inspector);

    }

    @Test
    void testInspeccionesUltimosTresDias() throws Exception {
        Inspeccion inspeccion = new Inspeccion();
        when(inspeccionRepository.findInspectorFechaBetween(LocalDateTime.now().minusDays(3), LocalDateTime.now(),
                inspector.getLegajo())).thenReturn(Arrays.asList(inspeccion));
        assertNotNull(inspectorController.inspeccionesUltimosTresDias(inspeccionService, inspector));
    }

    @Test
    void testSave() throws Exception {
        assertNotNull(inspectorController.save(inspectorService, inspector));
    }

    @Test
    void testUpdate() throws Exception {
        Inspector oldInspector = new Inspector();
        oldInspector.setLegajo(inspector.getLegajo());
        oldInspector.setNombre(inspector.getNombre());

        Optional<Inspector> iOptional = Optional.of(inspector);
        when(inspectorRepository.findById(any(Integer.class))).thenReturn(iOptional);

        Inspector inspectorToUpdate = new Inspector();
        inspectorToUpdate.setLegajo(inspector.getLegajo());
        inspectorToUpdate.setNombre("Roberto");

        when(inspectorRepository.save(inspectorToUpdate)).thenReturn(inspectorToUpdate);

        Inspector inspectorUpdated = inspectorController.update(inspectorService, inspectorToUpdate);

        assertEquals(inspectorUpdated.getLegajo(), oldInspector.getLegajo());
        assertEquals(inspectorUpdated.getNombre(), inspectorToUpdate.getNombre());
        assertNotEquals(inspectorUpdated.getNombre(), oldInspector.getNombre());

    }
}
