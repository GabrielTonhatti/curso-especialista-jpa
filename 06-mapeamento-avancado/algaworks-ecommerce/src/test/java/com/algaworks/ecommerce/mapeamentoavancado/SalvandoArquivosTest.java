package com.algaworks.ecommerce.mapeamentoavancado;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.NotaFiscal;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SalvandoArquivosTest extends EntityManagerTest {

    @Test
    void salvarXmlNota() {
        Pedido pedido = entityManager.find(Pedido.class, 1);

        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setPedido(pedido);
        notaFiscal.setDataEmissao(new Date());
        notaFiscal.setXml(carregarNotaFiscal());

        entityManager.getTransaction().begin();
        entityManager.persist(notaFiscal);
        entityManager.getTransaction().commit();

        entityManager.clear();

        NotaFiscal notaFiscalVerificacao = entityManager.find(NotaFiscal.class, notaFiscal.getId());
        assertNotNull(notaFiscalVerificacao.getXml());
        assertTrue(notaFiscalVerificacao.getXml().length > 0);

//        try {
//            OutputStream out = new FileOutputStream(
//                    Files.createFile(Paths.get(System.getProperty("user.home") + "/xml.xml")).toFile()
//            );
//            out.write(notaFiscalVerificacao.getXml());
//        } catch (Exception ex) {
//            throw new RuntimeException(ex);
//        }
    }

    @Test
    void salvarFotoProduto() {
        Produto produto = entityManager.find(Produto.class, 1);
        produto.setFoto(carregarFotoProduto());

        entityManager.getTransaction().begin();
        entityManager.persist(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        assertNotNull(produtoVerificacao.getFoto());
        assertTrue(produtoVerificacao.getFoto().length > 0);

//        try {
//            OutputStream out = new FileOutputStream(
//                    Files.createFile(Paths.get(System.getProperty("user.home") + "/kindle.jpg")).toFile()
//            );
//            out.write(produtoVerificacao.getFoto());
//        } catch (Exception ex) {
//            throw new RuntimeException(ex);
//        }
    }

    private static byte[] carregarNotaFiscal() {
        try {
            return SalvandoArquivosTest.class.getResourceAsStream("/nota-fiscal.xml").readAllBytes();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static byte[] carregarFotoProduto() {
        try {
            return SalvandoArquivosTest.class.getResourceAsStream("/kindle.jpg").readAllBytes();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
