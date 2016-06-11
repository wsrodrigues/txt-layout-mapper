/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mapper.txt.layout.mapper.principal;

import br.com.mapper.txt.layout.mapper.annotation.TxtLayoutArray;
import br.com.mapper.txt.layout.mapper.annotation.TxtLayoutColumn;
import br.com.mapper.txt.layout.mapper.annotation.TxtRootLayout;
import java.util.List;

/**
 *
 * @author wagner
 */
@TxtRootLayout(beginIndexColumnGroup = 0, endIndexColumnGroup = 3, restartGroupId = "001")
public class Fatura {
    @TxtLayoutArray(groupId = "001", txtLayoutItemClass = Fat001.class)
    private List<Fat001> fat001List;

    public List<Fat001> getFat001List() {
        return fat001List;
    }

    public void setFat001List(List<Fat001> fat001List) {
        this.fat001List = fat001List;
    }
    
    public static class Fat001 {
        @TxtLayoutColumn(beginIndex = 10, endIndex = 31)
        private String numero;

        public String getNumero() {
            return numero;
        }

        public void setNumero(String numero) {
            this.numero = numero;
        }
    }
}
