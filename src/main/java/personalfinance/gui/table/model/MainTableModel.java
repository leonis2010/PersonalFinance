/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.gui.table.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import personalfinance.gui.Refresh;
import personalfinance.model.Common;
import personalfinance.settings.Text;

/**
 *
 * @author Леонид
 */
public abstract class MainTableModel extends AbstractTableModel implements Refresh {

    protected List<? extends Common> data;
    protected List<String> columns = new ArrayList();

    public MainTableModel(List data, String[] columns) {
        this.data = data;
        this.columns = new ArrayList(Arrays.asList(columns));//превращаем массив String в ArrayList 
    }

    @Override
    public int getRowCount() {// кол-во строк в таблице
        return data.size();
    }

    @Override
    public int getColumnCount() {// кол-во столбцов в таблице
        return columns.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return Text.get(columns.get(columnIndex));// исп. текстовые константы Text() по индексу вытаскиваем имя колонки
    }

    //метод который получает класс по данному столбцу
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Object obj = getValueAt(0, columnIndex);
        if (obj == null) {
            return Object.class;
        }
        return obj.getClass();
    }

    @Override
    public void refresh() {
        updateData();
        fireTableStructureChanged();//перерисовываем таблицу
        fireTableDataChanged();//перерисовываем таблицу
    }

    public Common getCommonByRow(int row) {
        return data.get(row);
    }

    //получить значение в соотв. строке и столбце будем исп. дочерний класс
    abstract protected void updateData();//будут реализовывать дочерние классы
}
