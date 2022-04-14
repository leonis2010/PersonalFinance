/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.model;

import java.util.Objects;
import personalfinance.exception.ModelException;
import personalfinance.saveload.SaveData;

/**
 *
 * @author Леонид
 */
public class Article extends Common {
    public Article (){}
    private String title;
    public Article (String title)throws ModelException{
        this.title=title;
        if (title.length()==0) throw new ModelException (ModelException.TITLE_EMPTY);
        
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Article{" + "title=" + title + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.title);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Article other = (Article) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        return true;
    }
    @Override
    public String getValueForComboBox(){
        return title;
    }
    
    @Override
    public void postEdit(SaveData sd){
        for (Transaction t: sd.getTransactions()){
            if (t.getArticle().equals(sd.getoldCommon())){
                t.setArticle(this);
            }
        }
    }
}
