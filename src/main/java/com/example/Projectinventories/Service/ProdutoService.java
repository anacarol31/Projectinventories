package com.example.Projectinventories.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Projectinventories.DTO.ProdutoDTO;
import com.example.Projectinventories.Model.Categoria;
import com.example.Projectinventories.Model.Produto;
import com.example.Projectinventories.Repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    
    public Produto createProduto(ProdutoDTO produtoDTO) {
        Produto produto = new Produto();
        produto.setNome(produtoDTO.getNome());
        produto.setPreco(produtoDTO.getPreco());
        produto.setCategoria(new Categoria(produtoDTO.getCategoriaId()));
        
        return produtoRepository.save(produto);
    }
 
    public Produto updateProduto(Long id, ProdutoDTO produtoDTO) {
        Produto produto = produtoRepository.findById(id).orElse(null);
        produto.setNome(produtoDTO.getNome());
        produto.setPreco(produtoDTO.getPreco());
        produto.setCategoria(new Categoria(produtoDTO.getCategoriaId()));
        
        return produtoRepository.save(produto);
    }

    public Produto getProdutoById(Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

    public List<Produto> getAllProdutos() {
        return produtoRepository.findAll();
    }

    public void deleteProduto(Long id) {
        Produto produto = produtoRepository.findById(id).orElse(null);
        produtoRepository.delete(produto);
    }

    public List<Produto> listarPorProduto(String nomeCategoria) {
        return produtoRepository.findByCategoriaNome(nomeCategoria);
    }
}