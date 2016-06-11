/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mapper.txt.layout.mapper;

import br.com.mapper.txt.layout.mapper.annotation.TxtLayoutColumn;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import br.com.mapper.txt.layout.mapper.annotation.TxtRootLayout;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wagner
 */
public class TxtLayoutMapper<T> {

    private String txt;
    private String headGroup;

    public TxtLayoutMapper(String txt) {
        this.txt = txt;
    }

    public List<T> bind(Class<T> rootClass) throws TxtMapperException, InstantiationException, IllegalAccessException {

        TxtMapperFieldBuffer.doMapping(rootClass);
        List<T> rootList = new ArrayList<>();

        TxtMapperException exception = new TxtMapperException();

        List<String> lines = Arrays.asList(txt.split("\\n"));

        TxtRootLayout txtRootLayout = rootClass.getAnnotation(TxtRootLayout.class);

        T rootElement = rootClass.newInstance();
        for (int i = 0; i < lines.size(); i++) {
            
            String line = lines.get(i);
            
            Logger.getLogger(TxtLayoutMapper.class.getName()).log(Level.INFO, "Processando: " + line);

            String groupId = this.getLineGroupId(line, i, txtRootLayout, exception);
            this.addGroupElement(groupId, line, rootElement, exception);

            int nextIndex = i + 1;
            if (nextIndex < lines.size()) {
                String nextLineGroupId = this.getLineGroupId(lines.get(nextIndex), nextIndex, txtRootLayout, exception);
                
                if (nextLineGroupId.equals(txtRootLayout.restartGroupId())) {
                    rootList.add(rootElement);
                    rootElement = rootClass.newInstance();
                }
            } else {
                rootList.add(rootElement);
            }
        }
        
        if (exception.getErrorCount() > 0)
            throw exception;
        else {
            Logger.getLogger(TxtLayoutMapper.class.getName()).log(Level.WARNING, exception.toString());
        }
        
        return rootList;
    }

    private void addGroupElement(String groupId, String line, T obj, TxtMapperException exception) {
        
        for (TxtMapperFieldBuffer.Group group : TxtMapperFieldBuffer.groups) {
            if (group.groupId.equals(groupId)) {

                try {
                    Object itemInstance = group.itemClass.newInstance();
                    this.readLine(itemInstance, line);

                    Object list = obj.getClass().getMethod(this.getEncapsulateMethodName(group.field, "get"), null).invoke(obj, null);
                    if (list == null) {
                        ArrayList arrayList = new ArrayList();
                        obj.getClass().getMethod(this.getEncapsulateMethodName(group.field, "set"), List.class).invoke(obj, arrayList);
                    }

                    list  = (List) obj.getClass().getMethod(this.getEncapsulateMethodName(group.field, "get"), null).invoke(obj, null);
                    
                    ((List)list).add(itemInstance);
                } catch (Exception ex) {
                    exception.addNotification("Não é possível acessar a lista " + group.field.getName() + " na classe " + obj.getClass().getName() + ". Verifique o encapsulamento ou a estrutura do item " + group.itemClass.getName(), TxtMapperException.Notification.Type.ERROR);
                }
            }
        }
    }
    
    private void readLine(Object itemInstance, String line) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Field[] itemFields = itemInstance.getClass().getDeclaredFields();
        
        for (Field itemField : itemFields) {
            TxtLayoutColumn txtLayoutColumn = itemField.getAnnotation(TxtLayoutColumn.class);
            
            if (txtLayoutColumn != null) {
                itemInstance.getClass().getMethod(this.getEncapsulateMethodName(itemField, "set"), String.class).invoke(itemInstance, line.substring(txtLayoutColumn.beginIndex(), txtLayoutColumn.endIndex()));
            }
        }
    }
    
    private String getLineGroupId(String line, int lineIndex, TxtRootLayout txtLayoutType, TxtMapperException exception) {
        if (line.length() < txtLayoutType.endIndexColumnGroup()) {
            exception.addNotification("Índice do grupo excede o tamanho da linha " + lineIndex, TxtMapperException.Notification.Type.ERROR);
            return null;
        }
        
        return line.substring(txtLayoutType.beginIndexColumnGroup(), txtLayoutType.endIndexColumnGroup()).trim();
    }

    private String getEncapsulateMethodName(Field field, String prefix) {
        return prefix + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
    }
}
