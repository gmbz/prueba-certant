package certantPrueba.vtv.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import certantPrueba.vtv.controller.ClienteController;
import certantPrueba.vtv.model.Cliente;
import certantPrueba.vtv.service.ClienteServiceImpl;

@RestController
@RequestMapping(path = "api/clientes")
public class ClienteApi {
    ClienteController clienteController = new ClienteController();

    @Autowired
    private ClienteServiceImpl clienteService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(clienteController.findAll(clienteService));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error\"}");
        }
    }

    @GetMapping("/{id_cliente}")
    public ResponseEntity<?> getOne(@PathVariable int id_cliente) {
        try {
            Cliente cliente = new Cliente();
            cliente.setId_cliente(id_cliente);
            return ResponseEntity.status(HttpStatus.OK).body(clienteController.findById(clienteService, cliente));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error\"}");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Cliente cliente) {
        try {
            System.out.println(cliente.getId_cliente());
            return ResponseEntity.status(HttpStatus.OK).body(clienteController.save(clienteService, cliente));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error\"}");
        }
    }

    @PutMapping("/{id_cliente}")
    public ResponseEntity<?> update(@PathVariable int id_cliente, @RequestBody Cliente cliente) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(clienteController.update(clienteService, cliente));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error\"}");
        }
    }

    @DeleteMapping("/{id_cliente}")
    public ResponseEntity<?> delete(@PathVariable int id_cliente) {
        try {
            Cliente cliente = new Cliente();
            cliente.setId_cliente(id_cliente);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(clienteController.delete(clienteService, cliente));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error\"}");
        }
    }
}
