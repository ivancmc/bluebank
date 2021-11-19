package com.squad.dezktop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.squad.dezktop.model.TransacaoModel;

public interface TransacaoRepository extends JpaRepository<TransacaoModel, Long> {

	@Query(value = "SELECT * FROM transacao t"
			+ " WHERE t.categoria = 1 AND t.conta_origem LIKE :numero"
			+ " OR t.categoria = 2 AND t.conta_destino LIKE :numero",
			nativeQuery = true)
	public List<TransacaoModel> getByConta(String numero);
}
