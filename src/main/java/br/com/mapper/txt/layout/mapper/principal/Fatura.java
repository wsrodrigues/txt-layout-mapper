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
    @TxtLayoutArray(groupId = "02B", txtLayoutItemClass = Fat02B.class)
    private List<Fat02B> fat02BList;
    @TxtLayoutArray(groupId = "003", txtLayoutItemClass = Fat003.class)
    private List<Fat003> fat003List;
    @TxtLayoutArray(groupId = "015", txtLayoutItemClass = Fat003.class)
    private List<Fat003> fat015List;

    public List<Fat001> getFat001List() {
        return fat001List;
    }

    public void setFat001List(List<Fat001> fat001List) {
        this.fat001List = fat001List;
    }

    public List<Fat02B> getFat02BList() {
        return fat02BList;
    }

    public void setFat02BList(List<Fat02B> fat02BList) {
        this.fat02BList = fat02BList;
    }

    public List<Fat003> getFat003List() {
        return fat003List;
    }

    public void setFat003List(List<Fat003> fat003List) {
        this.fat003List = fat003List;
    }

    public List<Fat003> getFat015List() {
        return fat015List;
    }

    public void setFat015List(List<Fat003> fat015List) {
        this.fat015List = fat015List;
    }
}
