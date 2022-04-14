/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.gui.table;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import personalfinance.gui.handler.FunctionsHandler;
import personalfinance.gui.table.model.CurrencyTableModel;
import personalfinance.gui.table.renderer.MainTableCellRenderer;
import personalfinance.model.Currency;
import personalfinance.settings.Style;
import personalfinance.settings.Text;

/**
 *
 * @author Леонид
 */
public class CurrencyTableData extends TableData {

    //указываем настройки с языковыми константами
    private static final String[] columns = new String[]{
        "TITLE",
        "CODE",
        "RATE",
        "ON",
        "BASE"};
    private static final ImageIcon[] icons = new ImageIcon[]{
        Style.ICON_TITLE,
        Style.ICON_CODE,
        Style.ICON_RATE,
        Style.ICON_ON,
        Style.ICON_BASE};

    public CurrencyTableData(FunctionsHandler handler) {
        super(new CurrencyTableModel(columns), handler, columns, icons);
        init();
    }
    @Override
    protected void init(){
        for(String column: columns){
            getColumn(Text.get(column)).setCellRenderer(new TableCellOnOffRederer());//для каждого столбца делаю рендерер
        }
    }
    //активные валют пусть будут черного цвета, неактивные - серого цвета.
    private class TableCellOnOffRederer extends MainTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component renderer = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if(((Currency)((CurrencyTableModel)table.getModel()).getCommonByRow(row)).isOn()){
                renderer.setForeground(Style.COLOR_ON);
            }else{
                renderer.setForeground(Style.COLOR_OFF);
            }
            return renderer;
        }
    }

}

//            if ((value.toString()).contains("-")) {
//                renderer.setForeground(Style.COLOR_EXP);
//            } else {
//                renderer.setForeground(Style.COLOR_INCOME);
//            }