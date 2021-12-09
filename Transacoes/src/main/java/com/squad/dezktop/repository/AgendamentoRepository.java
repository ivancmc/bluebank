package com.squad.dezktop.repository;

//import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.squad.dezktop.model.AgendamentoModel;

public interface AgendamentoRepository extends JpaRepository <AgendamentoModel,Long> {
 

	@Query(value = "SELECT * FROM agendamento a"
			+ " WHERE a.data LIKE :hoje",
			nativeQuery = true)
	public List<AgendamentoModel> getByAgendamento(String hoje);
	
	
	@Query(value = "DELETE  FROM agendamento a"
			+ " WHERE a.data LIKE :hoje",
						nativeQuery = true)
	public void deleteByAgendamento(String hoje);
	
}
