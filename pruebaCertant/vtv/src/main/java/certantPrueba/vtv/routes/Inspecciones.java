package certantPrueba.vtv.routes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import certantPrueba.vtv.controller.InspeccionController;
import certantPrueba.vtv.model.Inspeccion;
import certantPrueba.vtv.model.Inspector;
import certantPrueba.vtv.model.Medicion;
import certantPrueba.vtv.model.Observacion;
import certantPrueba.vtv.model.Vehiculo;
import certantPrueba.vtv.service.IEstadoInspeccionService;
import certantPrueba.vtv.service.IInspeccionService;
import certantPrueba.vtv.service.IInspectorService;
import certantPrueba.vtv.service.IVehiculoService;

@Controller
@RequestMapping("/inspecciones")
public class Inspecciones {
    InspeccionController inspeccionController = new InspeccionController();

    @Autowired
    private IInspeccionService inspeccionService;

    @Autowired
    private IVehiculoService vehiculoService;

    @Autowired
    private IInspectorService inspectorService;

    @Autowired
    private IEstadoInspeccionService estadoInspeccionService;

    @GetMapping("")
    public String getAll(Model model) throws Exception {
        List<Inspeccion> inspecciones = inspeccionController.findAll(inspeccionService);

        model.addAttribute("inspecciones", inspecciones);
        model.addAttribute("titulo", "Listado de inspecciones");
        return "show_inspecciones";
    }

    @GetMapping("/nueva_inspeccion")
    public String nueva_inspeccion(Model model) throws Exception {
        Inspeccion inspeccion = new Inspeccion();
        List<Vehiculo> vehiculos = vehiculoService.findAll();
        List<Inspector> inspectores = inspectorService.findAll();
        model.addAttribute("inspeccion", inspeccion);
        model.addAttribute("inspectores", inspectores);
        model.addAttribute("vehiculos", vehiculos);
        model.addAttribute("titulo", "Nueva inspeccion");
        return "new_inspeccion";
    }

    @PostMapping("/create")
    public String createInspeccion(@ModelAttribute Inspeccion inspeccion, Model model) throws Exception {
        Inspeccion ins = inspeccionController.save(inspeccionService, inspeccion);

        Observacion observacion = new Observacion();
        ins.setObservacion(observacion);

        List<String> opciones = inspeccionController.getOpciones();

        model.addAttribute("opciones", opciones);
        model.addAttribute("inspeccion", ins);
        model.addAttribute("titulo", "Nueva observacion");
        return "new_observacion";
    }

    @PostMapping("/create_observacion")
    public String createObservacion(@ModelAttribute Inspeccion inspeccion, Model model) throws Exception {
        Inspeccion ins = inspeccionController.updateObservacion(inspeccionService, inspeccion);
        Medicion medicion = new Medicion();
        ins.setMedicion(medicion);

        List<String> opciones = inspeccionController.getOpciones();

        model.addAttribute("opciones", opciones);
        model.addAttribute("inspeccion", ins);
        model.addAttribute("titulo", "Nueva medicion");
        return "new_medicion";
    }

    @PostMapping("/create_medicion")
    public String createMedicion(@ModelAttribute Inspeccion inspeccion, Model model) throws Exception {
        Inspeccion ins = inspeccionController.updateMedicion(inspeccionService, inspeccion);
        inspeccionController.finalizarInspeccion(inspeccionService, estadoInspeccionService, ins);

        return "redirect:/inspecciones";
    }

    @GetMapping("/delete/{nro_inspeccion}")
    public String borrarInspeccion(@PathVariable("nro_inspeccion") int nro_inspeccion, Model model) throws Exception {
        Inspeccion inspeccion = new Inspeccion();
        inspeccion.setNro_inspeccion(nro_inspeccion);
        inspeccionController.delete(inspeccionService, inspeccion);
        return "redirect:/inspecciones";
    }

    @GetMapping("/update/{nro_inspeccion}")
    public String editarInspeccion(@PathVariable("nro_inspeccion") int nro_inspeccion, Model model) throws Exception {
        Inspeccion inspeccion = new Inspeccion();
        inspeccion.setNro_inspeccion(nro_inspeccion);
        Inspeccion inspeccionActualizar = inspeccionController.findById(inspeccionService, inspeccion);
        List<Inspector> inspectores = inspectorService.findAll();
        List<Vehiculo> vehiculos = vehiculoService.findAll();
        model.addAttribute("inspectores", inspectores);
        model.addAttribute("vehiculos", vehiculos);
        model.addAttribute("inspeccion", inspeccionActualizar);
        model.addAttribute("titulo", "Editar inspeccion");
        return "update_inspeccion";
    }

    @PostMapping("/update")
    public String actualizarInspeccion(@ModelAttribute Inspeccion inspeccion, Model model) throws Exception {

        Inspeccion ins = inspeccionController.update(inspeccionService, inspeccion);

        Observacion observacion = new Observacion();
        ins.setObservacion(observacion);

        List<String> opciones = inspeccionController.getOpciones();

        model.addAttribute("opciones", opciones);
        model.addAttribute("inspeccion", ins);
        model.addAttribute("titulo", "Editar observacion");
        return "update_observacion";
    }

    @PostMapping("/update/observacion")
    public String actualizarObservacion(@ModelAttribute Inspeccion inspeccion, Model model) throws Exception {
        Inspeccion ins = inspeccionController.updateObservacion(inspeccionService, inspeccion);
        Medicion medicion = new Medicion();
        ins.setMedicion(medicion);

        List<String> opciones = inspeccionController.getOpciones();

        model.addAttribute("opciones", opciones);
        model.addAttribute("inspeccion", ins);
        model.addAttribute("titulo", "Editar medicion");
        return "new_medicion";
    }

    @PostMapping("/update/medicion")
    public String actualizarMedicion(@ModelAttribute Inspeccion inspeccion, Model model) throws Exception {
        Inspeccion ins = inspeccionController.updateMedicion(inspeccionService, inspeccion);
        inspeccionController.finalizarInspeccion(inspeccionService, estadoInspeccionService, ins);

        return "redirect:/inspecciones";
    }

}
