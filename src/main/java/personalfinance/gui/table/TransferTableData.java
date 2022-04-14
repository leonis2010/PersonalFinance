/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.gui.table;

import java.awt.Color;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import personalfinance.gui.handler.FunctionsHandler;
import personalfinance.gui.table.model.TransferTableModel;
import personalfinance.gui.table.renderer.MainTableCellRenderer;
import personalfinance.settings.Style;
import personalfinance.settings.Text;

/**
 *
 * @author Леонид
 */
public class TransferTableData extends TableData {

    //указываем настройки с языковыми константами
    private static final String[] columns
            = new String[]{
                "DATE",
                "FROM_ACCOUNT",
                "TO_ACCOUNT",
                "FROM_AMOUNT",
                "TO_AMOUNT",
                "NOTICE"};

    private static final ImageIcon[] icons
            = new ImageIcon[]{
                Style.ICON_DATE,
                Style.ICON_ACCOUNT,
                Style.ICON_ACCOUNT,
                Style.ICON_AMOUNT,
                Style.ICON_AMOUNT,
                Style.ICON_NOTICE};

    public TransferTableData(FunctionsHandler handler) {
        super(new TransferTableModel(columns),
                handler,
                columns,
                icons);
        init();
    }

    @Override
    protected void init() {//снято - цвет расхода, зачислено - цвет дохода.
        getColumn(Text.get("FROM_AMOUNT")).setCellRenderer(new TableCellAmountRenderer(Style.COLOR_EXP));
        getColumn(Text.get("TO_AMOUNT")).setCellRenderer(new TableCellAmountRenderer(Style.COLOR_INCOME));
    }

    //нужно цветом зеленым показать доход, и красным цветом показать расход
    private class TableCellAmountRenderer extends MainTableCellRenderer {

        private final Color color;

        public TableCellAmountRenderer(Color color) {
            this.color = color;
        }

        @Override
        public Component getTableCellRendererComponent(
                JTable table,
                Object value,
                boolean isSelected,
                boolean hasFocus,
                int row,
                int column) {
            Component renderer = super.getTableCellRendererComponent(
                    table,
                    value,
                    isSelected,
                    hasFocus,
                    row,
                    column);
            renderer.setForeground(color);
            return renderer;
        }
    }

}
