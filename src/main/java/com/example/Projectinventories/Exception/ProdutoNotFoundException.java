package com.example.Projectinventories.Exception;

public class ProdutoNotFoundException extends RuntimeException {
    public ProdutoNotFoundException(String messagem) {
        super(messagem);
    }
    
}
