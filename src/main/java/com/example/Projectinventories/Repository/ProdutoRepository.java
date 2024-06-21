package com.example.Projectinventories.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.example.Projectinventories.Model.Produto;
   
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    

    @Query(
        value = "SELECT DISTINCT p.id AS produtoId, p.nome AS nomeProduto, c.nome AS categoriaNome " +
                "FROM Produto p JOIN Categoria c ON p.categoria_id = c.id " +
                "WHERE c.nome = :nomeCategoria", 
        nativeQuery = true
    )
    List<Produto> findByCategoriaNome(String nomeCategoria);

}
