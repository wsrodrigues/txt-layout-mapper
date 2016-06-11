/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mapper.txt.layout.mapper;

import br.com.mapper.txt.layout.mapper.annotation.TxtLayoutArray;
import br.com.mapper.txt.layout.mapper.annotation.TxtRootLayout;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wagner
 */
public class TxtMapperFieldBuffer {
    
    public static List<Group> groups;
    
    static {
        groups = new ArrayList<>();
    }
    
    public static void doMapping(Class c) throws TxtMapperException {
        if (c.getAnnotation(TxtRootLayout.class) == null) {
            throw new TxtMapperException("A Classe "+c.getName()+" não está anotada com "+TxtRootLayout.class.getName(), TxtMapperException.Notification.Type.ERROR);
        }
        
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            TxtLayoutArray arrAnnotation = field.getAnnotation(TxtLayoutArray.class);
            
            if (arrAnnotation == null)
                throw new TxtMapperException("O atributo " + field.getName() + " na classe " + c.getName() + " precisa estar anotado com " + TxtLayoutArray.class.getName() + " e ser do tipo " + List.class.getName(), TxtMapperException.Notification.Type.ERROR);
            
            if (!field.getType().isAssignableFrom(List.class))
                throw new TxtMapperException("O atributo "+field.getName()+" da classe "+c.getName()+" deve ser do tipo List", TxtMapperException.Notification.Type.ERROR);
            
            groups.add(new Group(arrAnnotation.groupId(), field, arrAnnotation.txtLayoutItemClass()));
        }
    }
    
    public static class Group {

        public Group(String groupId, Field field, Class itemClass) {
            this.groupId = groupId;
            this.field = field;
            this.itemClass = itemClass;
        }
        
        public String groupId;
        public Field field;
        public Class itemClass;
    }
}
