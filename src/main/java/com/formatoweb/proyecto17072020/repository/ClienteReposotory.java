package com.formatoweb.proyecto17072020.repository;

import com.formatoweb.proyecto17072020.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface ClienteReposotory extends JpaRepository<Cliente, Serializable> {
}
