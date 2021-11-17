package com.squad.dezktop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.squad.dezktop.model.TransacaoModel;

public interface TransacaoRepository extends JpaRepository<TransacaoModel, Long> {

	@Query(value = "SELECT * FROM transacao t WHERE t.conta_origem LIKE :numero OR t.conta_destino LIKE :numero",
			nativeQuery = true)
	public List<TransacaoModel> getByConta(String numero);
}
