package ua.external.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.external.spring.dto.ClientDTO;
import ua.external.spring.entity.Client;
import ua.external.spring.repository.ClientRepository;
import ua.external.spring.service.IClientService;

import java.util.Optional;

@Service
public class ClientService implements IClientService {
    @Autowired
    ClientRepository clientRepository;

    @Override
    @Transactional
    public Client createClient(ClientDTO client) {
        Client saveClient = clientRepository.saveAndFlush(Client.fromDTO(client));
        return saveClient;
    }

    @Override
    @Transactional
    public boolean updateClient(ClientDTO client) {
        clientRepository.save(Client.fromDTO(client));
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Client> findClientById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Client> findClientByName(String name) {
        return clientRepository.findByName(name);
    }
}
