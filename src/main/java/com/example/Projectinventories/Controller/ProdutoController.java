package com.example.Projectinventories.Controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.Projectinventories.DTO.ProdutoDTO;
import com.example.Projectinventories.Model.Produto;
import com.example.Projectinventories.Service.ProdutoService;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/produtos")
@Tag(name = "Produto Controller", description = "Operações relacionadas a produtos")
public class ProdutoController {
    @Autowired
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/get-all") //acessa em http://localhost:8080/api/produtos/get-all
    @Operation(summary = "Listar todos os produtos", description = "Lista todos os produtos cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de produtos encontrada"),
            @ApiResponse(responseCode = "404", description = "Nenhum produto encontrado")
    })

    public ResponseEntity<List<Produto>> listProdutos() { 
        List<Produto> produtos = produtoService.getAllProdutos();

        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}") //acessa em http://localhost:8080/api/produtos/{id}
    @Operation(summary = "Buscar um produto pelo ID", description = "Retorna um produto pelo ID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    }) 
    public ResponseEntity<Produto> searchProduto(@PathVariable Long id) {
        Produto produto = produtoService.getProdutoById(id);

        if(produto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(produto);
    }

    @PostMapping //acessa em http://localhost:8080/api/produtos 
                // com POST
    @Operation(summary = "Criar um novo produto", description = "Cria um novo produto com os dados informados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    public ResponseEntity<Produto> createProduto(@RequestBody ProdutoDTO produtoDTO) {
        Produto produtoCriado = produtoService.createProduto(produtoDTO);

         URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(produtoCriado.getId())
                        .toUri();

        return ResponseEntity.created(location).body(produtoCriado);
    }

    @PutMapping("/{id}") //acessa em http://localhost:8080/api/produtos/{id} //atualiza
                        //no body passar o JSON com os dados do produto
                        // com PUT
    @Operation(summary = "Atualizar um produto pelo ID", description = "Atualiza um produto existente pelo ID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })

    public ResponseEntity<Produto> updateProduto(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO) {
        Produto produtoAtualizado = produtoService.updateProduto(id, produtoDTO);

        if(produtoAtualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(produtoAtualizado);
    }

    // acesssa em http://localhost:8080/api/produtos/{id}
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um produto pelo ID", description = "Remove um produto pelo ID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public ResponseEntity<?> deleteProduto(@PathVariable Long id) {
        produtoService.deleteProduto(id);

        return ResponseEntity.noContent().build();
    }
}
