/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import personalfinance.gui.handler.MainToolBarHandler;
import personalfinance.gui.handler.MainWindowHandler;
import personalfinance.gui.menu.MainMenu;
import personalfinance.gui.panel.AccountPanel;
import personalfinance.gui.panel.ArticlePanel;
import personalfinance.gui.panel.CurrencyPanel;
import personalfinance.gui.panel.LeftPanel;
import personalfinance.gui.panel.OverViewPanel;
import personalfinance.gui.panel.RightPanel;
import personalfinance.gui.panel.StatisticsPanel;
import personalfinance.gui.panel.TransactionPanel;
import personalfinance.gui.panel.TransferPanel;
import personalfinance.gui.toolbar.MainToolBar;
import personalfinance.settings.Style;
import personalfinance.settings.Text;

/**
 *
 * @author Леонид
 */
public class MainFrame extends JFrame implements Refresh {

    private final GridBagConstraints constraints;
    private final MainMenu mb;
    private final LeftPanel leftPanel;
    private RightPanel rightPanel;
    private final MainToolBar tb;

    public MainFrame() {
        super(Text.get("PROGRAMM_NAME"));

        setResizable(false);//запрет изменения формы окна
        setIconImage(Style.ICON_MAIN.getImage());
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//при закрытии окна, закрывается сама программа
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);//при закрытии окна, ничего не делать

        mb = new MainMenu(this);
        setJMenuBar(mb);

        setLayout(new GridBagLayout());//размещает все компоненты ввиде гибкой таблицы

        // add toolbar
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;// растянуто на 2 столбца

        tb = new MainToolBar(new MainToolBarHandler(this));
        add(tb, constraints);

        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.NORTH;// привязка к верху

        leftPanel = new LeftPanel(this);
        add(leftPanel, constraints);// добавляем левую панель
/////////////////////////////////////////////////////////////////////////////////////////////
        setRightPanel(new TransactionPanel(this));

        pack();
        setLocationRelativeTo(null);// окно появляется по центру экрана.
        addWindowListener(new MainWindowHandler());
    }

    @Override
    public void refresh() {
        SwingUtilities.updateComponentTreeUI(this);// перерисовать заново frame
        mb.refresh();
        leftPanel.refresh();
        rightPanel.refresh();
        pack();
    }

    public MainMenu getMenu() {
        return mb;
    }

    public void setRightPanel(RightPanel panel) {
        if (rightPanel != null) {
            remove(rightPanel);
        }
        constraints.gridy = 1;//на 0 значении находится левая панель
        constraints.gridx = 1;
        rightPanel = panel;
        panel.setBorder(Style.BORDER_PANEL);
        add(rightPanel, constraints);
        pack();
        
    }

    public RightPanel getRightPanel() {
        return rightPanel;
    }

}
