package dev.brunoliveira.dsclients.service;

import dev.brunoliveira.dsclients.dto.ClientDto;
import dev.brunoliveira.dsclients.model.Client;
import dev.brunoliveira.dsclients.repositories.ClientRepository;
import dev.brunoliveira.dsclients.service.exceptions.ResourceNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

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

    @Transactional(readOnly = true)
    public ClientDto findById(Long id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        Client client = clientOptional.orElseThrow(() -> new ResourceNotFoundException("Client Id: " + id + " not found"));

        return new ClientDto(client);
    }

    @Transactional
    public ClientDto insert(ClientDto clientDto) {
        Client client = createClient(clientDto);
        client = clientRepository.save(client);

        return new ClientDto(client);
    }

    @Transactional
    public ClientDto update(ClientDto clientDto, Long id) {
        try {
            Client client = updateClient(clientDto, id);
            client = clientRepository.save(client);

            return new ClientDto(client);
        } catch (EntityNotFoundException exception) {
            throw new ResourceNotFoundException("ID: " + id + " not found!");
        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            clientRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new ResourceNotFoundException("ID: " + id + " not found!");
        }
    }

    private Client createClient(ClientDto dto){
        Client entity = new Client();
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());

        return entity;
    }

    private Client updateClient(ClientDto dto, Long id){
        Client client = clientRepository.getOne(id);
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        client.setIncome(dto.getIncome());
        client.setBirthDate(dto.getBirthDate());
        client.setChildren(dto.getChildren());

        return client;
    }

}
