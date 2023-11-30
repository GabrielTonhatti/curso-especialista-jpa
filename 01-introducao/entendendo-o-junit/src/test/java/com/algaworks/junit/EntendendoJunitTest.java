package com.algaworks.junit;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EntendendoJunitTest {

    @BeforeAll
    public static void iniciarTestes() {
        System.out.println(">>> public static void iniciarTestes() <<<");
    }

    @BeforeEach
    public void iniciarTeste() {
        System.out.println(">>> public void iniciarTeste() <<<");
    }

    @Test
    void testandoAlgo() {
        var nome = String.format("%s", "Gabriel");

        assertEquals("Gabriel", nome);
    }

    @Test
    void testandoOutroCoisa() {
        var str = String.format("%s", "");

        assertTrue(str.isEmpty());
    }

    @AfterEach
    public void encerrarTeste() {
        System.out.println(">>> public void encerrarTeste() <<<");
    }

    @AfterAll
    public static void encerrarTestes() {
        System.out.println(">>> public static void encerrarTestes() <<<");
    }
}

