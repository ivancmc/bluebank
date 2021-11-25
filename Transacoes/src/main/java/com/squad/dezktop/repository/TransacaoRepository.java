package com.squad.dezktop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.squad.dezktop.model.TransacaoModel;

public interface TransacaoRepository extends JpaRepository<TransacaoModel, Long> {

	@Query(value = "SELECT * FROM transacao t"
			+ " WHERE (t.categoria = 1 AND t.conta_origem LIKE :numero"
			+ " OR t.categoria = 2 AND t.conta_destino LIKE :numero)"
			+ " AND MONTH(t.momento) = MONTH(CURRENT_DATE()) AND YEAR(t.momento) = YEAR(CURRENT_DATE())"
			+ "	ORDER BY t.momento ASC",
			nativeQuery = true)
	public List<TransacaoModel> getByConta(String numero);
	
	@Query(value = "SELECT * FROM transacao t"
			+ " WHERE (t.categoria = 1 AND t.conta_origem LIKE :numero"
			+ " OR t.categoria = 2 AND t.conta_destino LIKE :numero)"
			+ " AND MONTH(t.momento) = :mes AND YEAR(t.momento) = :ano"
			+ "	ORDER BY t.momento ASC",
			nativeQuery = true)
	public List<TransacaoModel> getByMes(String numero, String mes, String ano);
}
