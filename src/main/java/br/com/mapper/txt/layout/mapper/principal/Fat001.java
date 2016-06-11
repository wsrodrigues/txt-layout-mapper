/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mapper.txt.layout.mapper.principal;

import br.com.mapper.txt.layout.mapper.annotation.TxtLayoutColumn;

/**
 *
 * @author wagner
 */
public class Fat001 {

    @TxtLayoutColumn(beginIndex = 10, endIndex = 31)
    private String numero;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
