package com.squad.dezktop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.squad.dezktop.model.ClienteModel;

public interface ClienteRepository extends JpaRepository<ClienteModel, String>{

}
