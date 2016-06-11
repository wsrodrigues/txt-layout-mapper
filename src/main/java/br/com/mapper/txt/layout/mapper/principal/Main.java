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
        FileInputStream fis = new FileInputStream(new File("/home/wagner/Downloads/senai.001"));
        byte[] bytes = new byte[fis.available()];
        fis.read(bytes);
        
        String conteudo = new String(bytes);
        TxtLayoutMapper<Fatura> txtLayoutMapper = new TxtLayoutMapper<>(conteudo);
        try {
            List<Fatura> bind = txtLayoutMapper.bind(Fatura.class);
            
            if (!bind.isEmpty()) {
                Logger.getLogger(Main.class.getName()).log(Level.INFO, "Número de Faturas = " + bind.size());
                Logger.getLogger(Main.class.getName()).log(Level.INFO, "Dado da Primeira Fatura = " + bind.get(0).getFat001List().get(0).getNumero());
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
