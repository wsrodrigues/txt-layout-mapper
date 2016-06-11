/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mapper.txt.layout.mapper.principal;

import br.com.mapper.txt.layout.mapper.TxtLayoutMapper;
import br.com.mapper.txt.layout.mapper.TxtMapperException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wagner
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(new File("/home/wagner/Downloads/arquivo.001"));
        byte[] bytes = new byte[fis.available()];
        fis.read(bytes);
        
        String conteudo = new String(bytes);
        TxtLayoutMapper<Fatura> txtLayoutMapper = new TxtLayoutMapper<>(conteudo);
        try {
            List<Fatura> bind = txtLayoutMapper.bind(Fatura.class);
            
            if (!bind.isEmpty()) {
                Logger.getLogger(Main.class.getName()).log(Level.INFO, "Número de Faturas = " + bind.size());
                Fatura fatura1 = bind.get(0);
                Logger.getLogger(Main.class.getName()).log(Level.INFO, "Itens Fat001 = " + fatura1.getFat001List().size() + " / Itens Fat02B = " + fatura1.getFat02BList().size() + " / Itens Fat003 = " + fatura1.getFat003List().size() );
                Logger.getLogger(Main.class.getName()).log(Level.INFO, "Dados da Primeira Fatura = " + fatura1.getFat001List().get(0).getNumero() + " / " + fatura1.getFat02BList().get(0).getQuantidade() + " / " + fatura1.getFat003List().get(0).getApuracao() );
                Fatura fatura25 = bind.get(25);
                Logger.getLogger(Main.class.getName()).log(Level.INFO, "Itens Fat001 = " + fatura25.getFat001List().size() + " / Itens Fat02B = " + fatura25.getFat02BList().size() + " / Itens Fat003 = " + fatura25.getFat003List().size() );
                Logger.getLogger(Main.class.getName()).log(Level.INFO, "Dados da Fatura 25 = " + fatura25.getFat001List().get(0).getNumero() + " / " + fatura25.getFat02BList().get(0).getQuantidade() + " / " + fatura25.getFat003List().get(0).getApuracao() );
            }
            
        } catch (TxtMapperException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        fis.close();
    }
}
