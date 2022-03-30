package certantPrueba.vtv.api;

import java.util.List;

import javax.validation.Valid;

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

    @GetMapping("/{dni_cliente}")
    public ResponseEntity<?> getOne(@PathVariable String dni_cliente) {
        try {
            Cliente cliente = new Cliente();
            cliente.setDni(dni_cliente);
            return ResponseEntity.status(HttpStatus.OK).body(clienteController.findById(clienteService, cliente));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error\"}");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Cliente cliente) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(clienteController.save(clienteService, cliente));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error\"}");
        }
    }

    @PutMapping("/{dni_cliente}")
    public ResponseEntity<?> update(@PathVariable String dni_cliente, @RequestBody Cliente cliente) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(clienteController.update(clienteService, cliente));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error\"}");
        }
    }

    @DeleteMapping("/{dni_cliente}")
    public ResponseEntity<?> delete(@PathVariable String dni_cliente) {
        try {
            Cliente cliente = new Cliente();
            cliente.setDni(dni_cliente);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(clienteController.delete(clienteService, cliente));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error\"}");
        }
    }
}
