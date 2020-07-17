package com.formatoweb.proyecto17072020.controllers;

import com.formatoweb.proyecto17072020.entity.Cliente;
import com.formatoweb.proyecto17072020.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clientes")
    public List<Cliente> getClientes(){
        return clienteService.getClientes();
    }

    @PostMapping("/clientes")
    public ResponseEntity<?> saveCliente(@RequestBody Cliente cliente){
        Map<String, Object> response = new HashMap<>();
        Cliente nuevoCliente = null;
        try{
            nuevoCliente = clienteService.saveCliente(cliente);
        }catch(DataAccessException e){
            response.put("mensaje", "Error al intentar guardar en la base de datos");
            response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Dato guardado con exito");
        response.put("Nuevo registro", nuevoCliente);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<?> getClienteById(@PathVariable Long id){
        Cliente cliente = null;
        Map<String, Object> response = new HashMap<>();
        try{
            cliente = clienteService.getClienteById(id);
        }catch (DataAccessException e){
            response.put("mensaje", "Error en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (cliente == null){
            response.put("mensaje", "El id: ".concat(id.toString()).concat(" no fue encontrado"));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        response.put("mensaje", "Registro encontrado!!");
        response.put("cliente", cliente);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<?> updateCliente(@RequestBody Cliente clienteNew, @PathVariable Long id){
        Cliente clienteOld = null;
        Cliente clienteActualizado = null;
        Map<String, Object> response = new HashMap<>();
        clienteOld = clienteService.getClienteById(id);
        if (clienteOld == null){
            response.put("mensaje", "cliente con id: ".concat(id.toString()).concat(" no encontrado"));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try{
            clienteOld.setNombre(clienteNew.getNombre());
            clienteOld.setApellido(clienteNew.getApellido());
            clienteOld.setEdad(clienteNew.getEdad());
            clienteOld.setEmail(clienteNew.getEmail());
            clienteActualizado = clienteService.saveCliente(clienteOld);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al actualizar en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Dato actualizado con exito");
        response.put("Cliente actulizado", clienteActualizado);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Map<String, Object>> deleteCliente(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        try{
            clienteService.ClienteDelete(id);
        }catch(DataAccessException e){
            response.put("mensaje", "No se pudo eliminar el registro");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Dato eliminado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
}
