package ua.external.spring.service;

import ua.external.spring.dto.ClientDTO;
import ua.external.spring.entity.Client;

import java.util.Optional;

public interface IClientService {
    Client createClient(ClientDTO client);

    boolean updateClient(ClientDTO client);

    Optional<Client> findClientById(Long id);

    Optional<Client> findClientByName(String name);
}
