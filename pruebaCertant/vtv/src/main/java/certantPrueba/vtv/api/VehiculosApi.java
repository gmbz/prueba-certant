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

import certantPrueba.vtv.controller.VehiculoController;
import certantPrueba.vtv.model.Vehiculo;
import certantPrueba.vtv.service.VehiculoServiceImpl;

@RestController
@RequestMapping(path = "api/vehiculos")
public class VehiculosApi {
    VehiculoController vehiculoController = new VehiculoController();

    @Autowired
    private VehiculoServiceImpl vehiculoService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(vehiculoController.findAll(vehiculoService));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error\"}");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Vehiculo vehiculo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(vehiculoController.save(vehiculoService, vehiculo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body("{\"error\":\"Error\"}");
        }
    }

    @GetMapping("/{patente}")
    public ResponseEntity<?> getOne(@PathVariable String patente) {
        try {
            Vehiculo vehiculo = new Vehiculo();
            vehiculo.setPatente(patente);
            return ResponseEntity.status(HttpStatus.OK).body(vehiculoController.findById(vehiculoService, vehiculo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error\"}");
        }
    }

    @DeleteMapping("/{patente}")
    public ResponseEntity<?> delete(@PathVariable String patente) {
        try {
            Vehiculo vehiculo = new Vehiculo();
            vehiculo.setPatente(patente);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(vehiculoController.delete(vehiculoService, vehiculo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error\"}");
        }
    }

    @PutMapping("/{patente}")
    public ResponseEntity<?> update(@PathVariable String patente, @RequestBody Vehiculo vehiculo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(vehiculoController.update(vehiculoService, vehiculo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error\"}");
        }
    }

}