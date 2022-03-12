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

import certantPrueba.vtv.controller.ClienteController;
import certantPrueba.vtv.model.Cliente;
import certantPrueba.vtv.model.Inspeccion;
import certantPrueba.vtv.model.TipoCliente;
import certantPrueba.vtv.model.Vehiculo;
import certantPrueba.vtv.service.IClienteService;
import certantPrueba.vtv.service.IInspeccionService;
import certantPrueba.vtv.service.ITipoClienteService;
import certantPrueba.vtv.service.IVehiculoService;

@Controller
@RequestMapping(path = "/clientes")
public class Clientes {
    ClienteController clienteController = new ClienteController();

    @Autowired
    private ITipoClienteService tipoClienteService;

    @Autowired
    private IClienteService clienteService;

    @Autowired
    private IInspeccionService inspeccionService;

    @Autowired
    private IVehiculoService vehiculoService;

    @GetMapping("")
    public String getAll(Model model) throws Exception {
        List<Cliente> clientes = clienteController.findAll(clienteService);
        model.addAttribute("clientes", clientes);
        model.addAttribute("titulo", "Listado de clientes");
        return "show_clientes";
    }

    @GetMapping("/{id_cliente}")
    public String getOne(@PathVariable("id_cliente") int id_cliente, Model model) throws Exception {
        Cliente cli = new Cliente();
        cli.setId_cliente(id_cliente);
        Cliente cliente = clienteController.findById(clienteService, cli);
        List<Inspeccion> inspecciones = clienteController.getInspecciones(inspeccionService, clienteService, cliente);
        List<Vehiculo> vehiculos = clienteController.findAllbyCliente(vehiculoService, cliente);

        System.out.println("===================================");
        System.out.println(vehiculos);
        model.addAttribute("vehiculos", vehiculos);
        model.addAttribute("inspecciones", inspecciones);
        model.addAttribute("cliente", cliente);
        model.addAttribute("titulo", "Cliente");
        return "show_cliente";
    }

    @GetMapping("/nuevo_cliente")
    public String nuevo_cliente(Model model) throws Exception {
        Cliente cliente = new Cliente();
        List<TipoCliente> tipos = tipoClienteService.findAll();
        model.addAttribute("cliente", cliente);
        model.addAttribute("tipos", tipos);
        model.addAttribute("titulo", "Nuevo cliente");
        return "new_cliente";
    }

    @PostMapping("/create")
    public String createCliente(@Valid @ModelAttribute Cliente cliente, BindingResult result, Model model)
            throws Exception {

        if (result.hasErrors()) {
            List<TipoCliente> tipos = tipoClienteService.findAll();
            model.addAttribute("cliente", cliente);
            model.addAttribute("tipos", tipos);
            model.addAttribute("titulo", "Nuevo cliente");
            return "new_cliente";
        }

        clienteController.save(clienteService, cliente);
        return "redirect:/clientes/";
    }

    @GetMapping("/delete/{id_cliente}")
    public String borrarCliente(@PathVariable("id_cliente") int id_cliente, Model model) throws Exception {
        Cliente cliente = new Cliente();
        cliente.setId_cliente(id_cliente);
        clienteController.delete(clienteService, cliente);
        return "redirect:/clientes/";
    }

    @GetMapping("/update/{id_cliente}")
    public String editarCliente(@PathVariable("id_cliente") int id_cliente, Model model) throws Exception {
        Cliente cliente = new Cliente();
        cliente.setId_cliente(id_cliente);
        Cliente clienteActualizar = clienteController.findById(clienteService, cliente);
        List<TipoCliente> tipos = tipoClienteService.findAll();
        model.addAttribute("cliente", clienteActualizar);
        model.addAttribute("tipos", tipos);
        model.addAttribute("titulo", "Editar cliente");
        return "update_cliente";
    }

    @PostMapping("/update")
    public String actualizar(@ModelAttribute Cliente cliente) throws Exception {
        clienteController.update(clienteService, cliente);
        return "redirect:/clientes";
    }
}
