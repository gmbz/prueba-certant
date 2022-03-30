package certantPrueba.vtv.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import certantPrueba.vtv.exception.NotFoundException;
import certantPrueba.vtv.model.Cliente;
import certantPrueba.vtv.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> findAll() throws Exception {
        try {
            List<Cliente> clientes = clienteRepository.findAll();
            return clientes;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Cliente findById(Cliente cliente) throws Exception {
        try {
            Optional<Cliente> entity = clienteRepository.findById(cliente.getDni());
            return entity.get();
        } catch (Exception e) {
            throw new NotFoundException("No se encuentra el cliente");
        }
    }

    @Override
    public Cliente save(Cliente cliente) throws Exception {
        try {
            return clienteRepository.save(cliente);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Cliente update(Cliente cliente) throws Exception {
        try {
            return clienteRepository.save(cliente);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean delete(Cliente cliente) throws Exception {
        try {
            clienteRepository.deleteById(cliente.getDni());
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Integer countVehiculos(Cliente cliente) throws Exception {
        try {
            return clienteRepository.countVehiculos(cliente.getDni());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
