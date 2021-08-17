package dev.brunoliveira.dsclients.service;

import dev.brunoliveira.dsclients.dto.ClientDto;
import dev.brunoliveira.dsclients.model.Client;
import dev.brunoliveira.dsclients.repositories.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional(readOnly = true)
    public Page<ClientDto> findAllPaged(PageRequest pageRequest) {
        Page<Client> clients = clientRepository.findAll(pageRequest);
        return clients.map(ClientDto::new);
    }
}
