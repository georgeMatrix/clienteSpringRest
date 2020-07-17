package com.formatoweb.proyecto17072020.service;

import com.formatoweb.proyecto17072020.entity.Cliente;

import java.util.List;

public interface ClienteService {
    List<Cliente> getClientes();
    Cliente saveCliente(Cliente cliente);
    Cliente getClienteById(Long id);
    void ClienteDelete(Long id);
}
