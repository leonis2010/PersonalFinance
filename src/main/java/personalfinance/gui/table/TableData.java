/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.gui.table;

import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import personalfinance.gui.Refresh;
import personalfinance.gui.handler.FunctionsHandler;
import personalfinance.gui.menu.TablePopupMenu;
import personalfinance.gui.table.model.MainTableModel;
import personalfinance.gui.table.renderer.MainTableCellRenderer;
import personalfinance.gui.table.renderer.TableHeaderIconRenderer;
import personalfinance.settings.Style;
import personalfinance.settings.Text;

/**
 *
 * @author Леонид
 */
abstract public class TableData extends JTable implements Refresh {

    private final TablePopupMenu popup;
    private final String[] columns;
    private final ImageIcon[] icons;
    private final FunctionsHandler handler;

    public TableData(MainTableModel model, FunctionsHandler handler, String[] columns, ImageIcon[] icons) {
        super(model);
        this.handler = handler;
        this.popup = new TablePopupMenu(handler);
        this.columns = columns;
        this.icons = icons;

        getTableHeader().setFont(Style.FONT_TABLE_HEADER);
        setFont(Style.FONT_TABLE);
        setRowHeight(getRowHeight() + Style.TABLE_ADD_ROW_HEIGHT);//содержимое строки увеличиваю с помощью дополнительной константы TABLE_ADD_ROW_HEIGHT

        setAutoCreateRowSorter(true);//данная таблица позволяла делать сортировку по столбцам по двойному щелчку мыши
        setPreferredScrollableViewportSize(Style.DIMENSION_TABLE_SHOW_SIZE);// таблица будет строго одного размера вне зависимости от количества данных в таблице
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // можно выбрать только лишь одну строку для удаления или редактирования

        addMouseListener(handler);//подключили обработчик
        addKeyListener(handler);//подключили обработчик

        for (int i = 0; i < columns.length; i++) {
            getColumn(Text.get(columns[i])).setHeaderRenderer(new TableHeaderIconRenderer(icons[i]));//чтобы обратитьсья к конкретному столбцу нам надо указать конкретное слово
            //напр. "сумма",поэтому мы языковую константу преобразовываем в данное слово и от него уже исп. .setHeaderRenderer()
            // добавляются к каждой иконки
        }
        MainTableCellRenderer renderer = new MainTableCellRenderer();
        setDefaultRenderer(String.class, renderer);// устанавливаем по умолчанию рендеринг строк
        setComponentPopupMenu(popup);
    }

    //только при выделенной строке ЛКМ мышкой, будет всплывать меню, при нажатии правой кнопкой на строке
//    @Override// переписали встроенный метод
//    public JPopupMenu getComponentPopupMenu() {
//        Point p = getMousePosition();
//        //rowAtPoint метод определяет по координатам на какой строке находится курсор
//        if (p != null && rowAtPoint(p) != -1) {
//            if (isRowSelected(rowAtPoint(p))) {
//                return super.getComponentPopupMenu();
//            } else {
//                return null;
//            }
//        }
//        return super.getComponentPopupMenu();
//    }
    //правая кнопка мышки выделяет строку и инициирует всплывание меню
    @Override// переписали встроенный метод
    public JPopupMenu getComponentPopupMenu() {
        Point p = getMousePosition();
        if (p != null) {
            int row = rowAtPoint(p);//rowAtPoint метод определяет по координатам на какой строке находится курсор
            if (isRowSelected(row)) {//если выделена строка, тогда правой кнопкой будет показывать меню
                return super.getComponentPopupMenu();

            } else {
                return null;
            }

        }
        return super.getComponentPopupMenu();
    }

    @Override
    public void refresh() {
        int selectedRow = getSelectedRow(); //при обновлении таблицы выделенная строка должна сохраняться
        ((MainTableModel) getModel()).refresh();
        for (int i = 0; i < columns.length; i++) {//цикл нужен для обновления всех картинок(рендерер)
            getColumn(Text.get(columns[i])).setHeaderRenderer(new TableHeaderIconRenderer(icons[i]));
        }
        //Если строка не выделена (-1) и кол-во строк меньше, чем всего строк, тогда устанавливаем выделение на строку напр, 1 и 1, то есть на одну строку
        if (selectedRow != -1 && selectedRow < getRowCount()) {
            setRowSelectionInterval(selectedRow, selectedRow);
            requestFocus();//возвращает фокус обратно на таблицу
        }
        init();
    }

    protected void init() {
    }

    public FunctionsHandler getFunctionHandler() {
        return handler;
    }

}
