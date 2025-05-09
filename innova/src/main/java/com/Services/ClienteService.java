package Services;
//
import Models.Entities.Cliente;
import Repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> getClienteById(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente createCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> updateCliente(Long id, Cliente detalles) {
        return clienteRepository.findById(id).map(cliente -> {
            cliente.setNombre(detalles.getNombre());
            cliente.setIndustria(detalles.getIndustria());
            cliente.setEmailContacto(detalles.getEmailContacto());
            return clienteRepository.save(cliente);
        });
    }

    public boolean deleteCliente(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}