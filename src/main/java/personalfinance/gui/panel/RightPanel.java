/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.gui.panel;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import personalfinance.gui.EnableEditDelete;
import personalfinance.gui.MainFrame;
import personalfinance.gui.Refresh;
import personalfinance.gui.table.TableData;
import personalfinance.gui.toolbar.AbstractToolBar;
import personalfinance.settings.Style;
import personalfinance.settings.Text;

/**
 *
 * @author Леонид
 */
abstract public class RightPanel extends AbstractPanel {

    public RightPanel(MainFrame frame, TableData td, String title, ImageIcon icon, JPanel[] panels) {
        super(frame);
        this.td = td;
        this.title = title;
        this.icon = icon;
        this.panels = panels;
        init();
    }

    public RightPanel(MainFrame frame, TableData td, String title, ImageIcon icon, AbstractToolBar tb) {
        this(frame, td, title, icon, new JPanel[]{tb});
    }

    public RightPanel(MainFrame frame, TableData td, String title, ImageIcon icon) {
        this(frame, td, title, icon, new JPanel[]{});
    }

    protected TableData td;//Таблица

    private String title;
    private ImageIcon icon;
    private JPanel[] panels;//различные панели с фильтрами, кнопками итп

    protected void setPanels(JPanel[] panels) {
        this.panels = panels;
    }

    @Override
    public void refresh() {
        super.refresh();
        if (td != null) {
            td.refresh();
        }
        for (JPanel panel : panels) {//проверяем для панелей поддерживают ли они интерфейс refresh, если да то исп.
            if (panel instanceof Refresh) {
                ((Refresh) panel).refresh();
            }
        }
    }

    private void enableEditDelete() {// отвечает за доступность пунктов в меню(откл./ вкл)
        for (JPanel panel : panels) {//проверяем для панелей поддерживают ли они интерфейс EnableEditDelete, если да то исп.
            if (panel instanceof EnableEditDelete) {
                ((EnableEditDelete) panel).setEnableEditDelete(false);
            }
        }
        frame.getMenu().setEnableEditDelete(false);

        if (td != null) {

            if (td.getSelectedRow() != -1)// он показыавет какую строку выбрал пользователь, возвращает число, если -1, то ничего не выбрано
            {
                for (JPanel panel : panels) {//проверяем для панелей поддерживают ли они интерфейс EnableEditDelete, если да то исп.
                    if (panel instanceof EnableEditDelete) {
                        ((EnableEditDelete) panel).setEnableEditDelete(true);
                    }
                }
                frame.getMenu().setEnableEditDelete(true);//тогда включаем видимость кнопок изменить и удалить
            }
        }
    }

    @Override
    protected final void init() {
        enableEditDelete();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel header = new JLabel(Text.get(title));// заголовок
        header.setFont(Style.FONT_LABEL_HEADER);
        header.setIcon(icon);
        header.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        add(header);

        if (panels.length == 0) {
            add(Box.createVerticalStrut(Style.PADDING_PANEL_EMPTY));// если пустая панель, чтобы был отступ
        }
        for (JPanel panel : panels) {
            add(panel);
            add(Box.createVerticalStrut(Style.PADDING_PANEL));
        }
        if (td != null) {
            JScrollPane scroll = new JScrollPane(td);
            add(scroll);
            scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//всегда показывать скроллбар
            ListSelectionModel selectionModel = td.getSelectionModel();//вешаем обработку чтобы запускал enableEditDelete, если выделение установлено или снято
            selectionModel.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent lse) {
                    enableEditDelete();// вот он запускается в зависимости от выделения мышкой 
                }
            });

        }
    }
    public TableData getTableData(){
        return td;
    }

}
