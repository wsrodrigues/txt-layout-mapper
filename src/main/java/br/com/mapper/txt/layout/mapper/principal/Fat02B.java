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
public class Fat02B {
    @TxtLayoutColumn(beginIndex = 79, endIndex = 90)
    private String quantidade;

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }
}
