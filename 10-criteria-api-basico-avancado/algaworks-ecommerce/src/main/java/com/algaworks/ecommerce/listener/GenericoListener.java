package com.algaworks.ecommerce.listener;

import jakarta.persistence.PostLoad;

public class GenericoListener {

    @PostLoad
    public void logarCarregamento(Object obj) {
        System.out.println("Entidade " + obj.getClass().getSimpleName() + " sofreu evento.");
    }

}
