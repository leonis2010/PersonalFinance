/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.gui.handler;

import java.awt.event.ActionEvent;
import personalfinance.gui.MainFrame;
import personalfinance.gui.dialog.AboutDialog;
import personalfinance.settings.HandlerCode;

/**
 *
 * @author Леонид
 */
public class MenuHelpHandler extends Handler {//обработчик

    public MenuHelpHandler(MainFrame frame) {
        super(frame);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case HandlerCode.MENU_HELP_ABOUT: {
                new AboutDialog().setVisible(true);
                break;
            }

        }
        super.actionPerformed(ae);
    }


}
