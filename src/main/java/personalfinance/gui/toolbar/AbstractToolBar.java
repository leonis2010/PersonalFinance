/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.gui.toolbar;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import personalfinance.gui.MainButton;
import personalfinance.gui.Refresh;
import personalfinance.gui.handler.Handler;

/**
 *
 * @author Леонид
 */
abstract public class AbstractToolBar extends JPanel implements Refresh {

    private final Handler handler;

    public AbstractToolBar(EmptyBorder border, Handler handler) {
        super();
        this.handler = handler;
        setBorder(border);
    }

    abstract protected void init();

    protected MainButton addButton(String title, ImageIcon icon, String action, boolean topIcon) {
        MainButton button = new MainButton(title, icon, handler, action);
        if (topIcon) {//если иконка сверху
            button.setHorizontalTextPosition(SwingConstants.CENTER);//то текст выровнять по горизонтали по центру
            button.setVerticalTextPosition(SwingConstants.BOTTOM);// и выровнять по вертикали снизу
        } else {
            button.setHorizontalTextPosition(SwingConstants.RIGHT);//то текст выровнять по горизонтали справа
            button.setVerticalTextPosition(SwingConstants.CENTER);// и выровнять по вертикали по центру
        }
        add(button);
        return button;
    }

    @Override
    public void refresh() {
        removeAll();
        init();
    }
}
