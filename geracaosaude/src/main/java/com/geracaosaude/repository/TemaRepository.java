package com.geracaosaude.repository;

import com.geracaosaude.model.Tema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemaRepository extends JpaRepository<Tema, Long> {
    List<Tema> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);
    List<Tema> findAllByDescricaoContainingIgnoreCase(@Param("descricao") String descricao);
}
