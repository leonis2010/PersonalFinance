/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.model;

import personalfinance.saveload.SaveData;

/**
 *
 * @author Леонид
 */
abstract public class Common {
    public Common(){}
    
    public String getValueForComboBox(){// выпадающий список, что выводиться зависит от объекта
        return null;
    }
    
    public void postAdd(SaveData sd){}//что делать после добавления объекта
    public void postEdit(SaveData sd){}//что делать после редактирования объекта
    public void postRemove(SaveData sd){}//что делать после удаления объекта
}
