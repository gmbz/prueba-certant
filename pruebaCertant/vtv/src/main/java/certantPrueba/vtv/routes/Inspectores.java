package certantPrueba.vtv.routes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import certantPrueba.vtv.controller.InspectorController;
import certantPrueba.vtv.model.Inspeccion;
import certantPrueba.vtv.model.Inspector;
import certantPrueba.vtv.service.IInspeccionService;
import certantPrueba.vtv.service.IInspectorService;

@Controller
@RequestMapping("/inspectores")
public class Inspectores {
    InspectorController inspectorController = new InspectorController();

    @Autowired
    private IInspectorService inspectorService;

    @Autowired
    private IInspeccionService inspeccionService;

    @GetMapping("")
    public String getAll(Model model) throws Exception {
        List<Inspector> inspectores = inspectorController.findAll(inspectorService);
        model.addAttribute("inspectores", inspectores);
        model.addAttribute("titulo", "Listado de inspectores");
        return "show_inspectores";
    }

    @GetMapping("/nuevo_inspector")
    public String nuevoInspector(Model model) throws Exception {
        Inspector inspector = new Inspector();
        model.addAttribute("inspector", inspector);
        model.addAttribute("titulo", "Nuevo inspector");
        return "new_inspector";
    }

    @PostMapping("/create")
    public String createInspector(@Valid @ModelAttribute Inspector inspector, BindingResult result, Model model)
            throws Exception {

        if (result.hasErrors()) {
            model.addAttribute("inspector", inspector);
            model.addAttribute("titulo", "Nuevo inspector");
            return "new_inspector";
        }

        inspectorController.save(inspectorService, inspector);
        return "redirect:/inspectores";
    }

    @GetMapping("/delete/{dni_inspector}")
    public String borrarInspector(@PathVariable("dni_inspector") String dni_inspector, Model model) throws Exception {
        Inspector inspector = new Inspector();
        inspector.setDni(dni_inspector);
        inspectorController.delete(inspectorService, inspector);
        return "redirect:/inspectores/";
    }

    @GetMapping("/update/{dni_inspector}")
    public String editarInspector(@PathVariable("dni_inspector") String dni_inspector, Model model) throws Exception {
        Inspector inspector = new Inspector();
        inspector.setDni(dni_inspector);
        Inspector inspectorActualizar = inspectorController.findById(inspectorService, inspector);
        model.addAttribute("inspector", inspectorActualizar);
        model.addAttribute("titulo", "Editar inspector");
        return "update_inspector";
    }

    @PostMapping("/update")
    public String actualizarInspector(@ModelAttribute Inspector inspector) throws Exception {
        inspectorController.update(inspectorService, inspector);
        return "redirect:/inspectores";
    }

    @GetMapping("/{dni_inspector}")
    public String getLastThreeDays(@PathVariable("dni_inspector") String dni_inspector, Model model) throws Exception {
        Inspector ins = new Inspector();
        ins.setDni(dni_inspector);
        Inspector inspector = inspectorController.findById(inspectorService, ins);
        List<Inspeccion> inspecciones = inspectorController.inspeccionesUltimosTresDias(inspeccionService, inspector);
        model.addAttribute("inspector", inspector);
        model.addAttribute("inspecciones", inspecciones);
        model.addAttribute("titulo", "Inspecciones ultimos tres dias");
        return "show_tres_dias";
    }

}
