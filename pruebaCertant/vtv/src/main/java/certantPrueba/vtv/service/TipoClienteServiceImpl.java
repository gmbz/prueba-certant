package certantPrueba.vtv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import certantPrueba.vtv.model.TipoCliente;
import certantPrueba.vtv.repository.TipoClienteRepository;

@Service
public class TipoClienteServiceImpl implements ITipoClienteService {

    @Autowired
    private TipoClienteRepository tipoClienteRepository;

    @Override
    public List<TipoCliente> findAll() throws Exception {
        try {
            List<TipoCliente> tipos = tipoClienteRepository.findAll();
            return tipos;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
