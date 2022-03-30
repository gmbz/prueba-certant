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
import org.springframework.web.bind.annotation.RequestParam;

import certantPrueba.vtv.controller.ClienteController;
import certantPrueba.vtv.controller.VehiculoController;
import certantPrueba.vtv.model.Auto;
import certantPrueba.vtv.model.Camioneta;
import certantPrueba.vtv.model.Cliente;
import certantPrueba.vtv.model.Inspeccion;
import certantPrueba.vtv.model.Marca;
import certantPrueba.vtv.model.Moto;
import certantPrueba.vtv.model.TipoVehiculo;
import certantPrueba.vtv.model.Vehiculo;
import certantPrueba.vtv.service.IClienteService;
import certantPrueba.vtv.service.IInspeccionService;
import certantPrueba.vtv.service.IVehiculoService;

@Controller
@RequestMapping(path = "/vehiculos")
public class Vehiculos {
    VehiculoController vehiculoController = new VehiculoController();
    ClienteController clienteController = new ClienteController();

    @Autowired
    private IVehiculoService vehiculoService;

    @Autowired
    private IClienteService clienteService;

    @Autowired
    private IInspeccionService inspeccionService;

    @GetMapping("")
    public String getAll(Model model) throws Exception {
        List<Vehiculo> vehiculos = vehiculoController.findAll(vehiculoService);

        model.addAttribute("vehiculos", vehiculos);
        model.addAttribute("titulo", "Listado de vehiculos");
        return "show_vehiculos";
    }

    @GetMapping("/nuevo_vehiculo")
    public String nuevo_vehiculo(Model model) throws Exception {
        List<Marca> marcasAuto = vehiculoController.findAllMarcasAuto(vehiculoService);
        List<Marca> marcasMoto = vehiculoController.findAllMarcasMoto(vehiculoService);
        List<Marca> marcasCamioneta = vehiculoController.findAllMarcasCamioneta(vehiculoService);
        List<TipoVehiculo> tipos_vehiculo = vehiculoController.findAllTipoVehiculo(vehiculoService);

        model.addAttribute("marcasMoto", marcasMoto);
        model.addAttribute("marcasCamioneta", marcasCamioneta);
        model.addAttribute("marcasAuto", marcasAuto);
        model.addAttribute("tipos_vehiculo", tipos_vehiculo);
        model.addAttribute("titulo", "Seleccionar marca");
        return "new_vehiculo_select_marca";
    }

    @PostMapping("/seleccionar_marca")
    public String seleccionar_marca(@RequestParam("marcaVehiculo") int marca_vehiculo, Model model) throws Exception {
        Marca marca_request = new Marca();
        marca_request.setId_marca(marca_vehiculo);
        Marca marca = vehiculoController.findMarcaById(vehiculoService, marca_request);
        Vehiculo vehiculo = null;
        if (marca.getTipo_vehiculo().getTipo_vehiculo().equalsIgnoreCase("auto")) {
            vehiculo = new Auto();
        } else if (marca.getTipo_vehiculo().getTipo_vehiculo().equalsIgnoreCase("moto")) {
            vehiculo = new Moto();
        } else {
            vehiculo = new Camioneta();
        }

        List<Cliente> clientes = clienteController.findAll(clienteService);

        model.addAttribute("marca", marca);
        model.addAttribute("clientes", clientes);
        model.addAttribute("vehiculo", vehiculo);
        model.addAttribute("titulo", "Nuevo vehiculo");

        return "new_vehiculo";
    }

    @PostMapping("/create")
    public String createVehiculo(@Valid @ModelAttribute Vehiculo vehiculo,
            BindingResult result, Model model,
            @RequestParam("marcaVehiculo") int marca_vehiculo)
            throws Exception {

        if (result.hasErrors()) {

            Marca marca_request = new Marca();
            marca_request.setId_marca(marca_vehiculo);
            Marca marca = vehiculoController.findMarcaById(vehiculoService, marca_request);
            List<Cliente> clientes = clienteController.findAll(clienteService);

            model.addAttribute("marca", marca);
            model.addAttribute("clientes", clientes);
            model.addAttribute("vehiculo", vehiculo);
            model.addAttribute("titulo", "Nuevo vehiculo");

            return "new_vehiculo";
        }
        vehiculoController.save(vehiculoService, vehiculo);
        return "redirect:/vehiculos";
    }

    @GetMapping("/delete/{patente}")
    public String borrarVehiculo(@PathVariable("patente") String patente, Model model) throws Exception {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setPatente(patente);
        vehiculoController.delete(vehiculoService, vehiculo);
        return "redirect:/vehiculos/";
    }

    @GetMapping("/update/{patente}")
    public String editarVehiculo(@PathVariable("patente") String patente, Model model) throws Exception {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setPatente(patente);
        Vehiculo vehiculoActualizar = vehiculoController.findById(vehiculoService, vehiculo);
        List<Cliente> clientes = clienteController.findAll(clienteService);
        Marca marca = vehiculoController.findMarcaById(vehiculoService, vehiculoActualizar.getModelo().getMarca());
        model.addAttribute("vehiculo", vehiculoActualizar);
        model.addAttribute("clientes", clientes);
        model.addAttribute("marca", marca);
        model.addAttribute("titulo", "Editar vehiculo");
        return "update_vehiculo";
    }

    @PostMapping("/update")
    public String actualizarVehiculo(@ModelAttribute Vehiculo vehiculo) throws Exception {
        vehiculoController.update(vehiculoService, vehiculo);
        return "redirect:/vehiculos";
    }

    @GetMapping("/vehiculos_estado")
    public String getVehiculosEstado(Model model) throws Exception {
        List<Inspeccion> inspecciones = vehiculoController.findAllEstados(inspeccionService);

        model.addAttribute("inspecciones", inspecciones);
        model.addAttribute("titulo", "Listado de estados de vehiculos");
        return "show_vehiculos_estados";
    }

    @GetMapping("/ultima_semana")
    public String getVehiculosUltimaSemana(Model model) throws Exception {
        List<Inspeccion> inspecciones = vehiculoController.findUltimaSemana(inspeccionService);

        model.addAttribute("inspecciones", inspecciones);
        model.addAttribute("titulo", "Listado de estados de vehiculos");
        return "show_ultima_semana";
    }
}
