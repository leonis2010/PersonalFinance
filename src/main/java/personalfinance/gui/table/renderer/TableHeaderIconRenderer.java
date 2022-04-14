/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.gui.table.renderer;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Леонид
 */
public class TableHeaderIconRenderer extends DefaultTableCellRenderer{
    private final JLabel label;

    public TableHeaderIconRenderer(ImageIcon icon) {
        super();
        label = new JLabel(icon);
    }
    @Override
    public Component getTableCellRendererComponent//метод возвращет тот компонент, который используется для заголовков таблицы
        (JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column ){
        TableCellRenderer tcr = table.getTableHeader().getDefaultRenderer();
        Component renderer = tcr.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        label.setFont(renderer.getFont());
        label.setForeground(renderer.getForeground());
        label.setBorder(((JComponent) renderer).getBorder());
        label.setText(""+value);
        return label;
    }
}
