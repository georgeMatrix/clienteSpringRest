package com.formatoweb.proyecto17072020.service.impl;

import com.formatoweb.proyecto17072020.entity.Cliente;
import com.formatoweb.proyecto17072020.repository.ClienteReposotory;
import com.formatoweb.proyecto17072020.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private ClienteReposotory clienteReposotory;
    @Override
    public List<Cliente> getClientes() {
        return clienteReposotory.findAll();
    }

    @Override
    public Cliente saveCliente(Cliente cliente) {
        return clienteReposotory.save(cliente);
    }

    @Override
    public Cliente getClienteById(Long id) {
        return clienteReposotory.findById(id).orElse(null);
    }

    @Override
    public void ClienteDelete(Long id) {
        clienteReposotory.deleteById(id);
    }
}
