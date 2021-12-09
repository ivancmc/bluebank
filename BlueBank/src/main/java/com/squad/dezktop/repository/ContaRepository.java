package com.squad.dezktop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.squad.dezktop.model.ContaModel;

public interface ContaRepository extends JpaRepository<ContaModel, String>{

	@Query(value = "SELECT * FROM conta c WHERE c.cliente_cpf = :cpf",
			nativeQuery = true)
	public ContaModel procuraPorCpf(String cpf);
}
