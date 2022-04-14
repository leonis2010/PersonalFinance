/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.gui.handler;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import personalfinance.gui.MainFrame;
import personalfinance.gui.dialog.ConfirmDialog;
import personalfinance.saveload.SaveData;

/**
 *
 * @author Леонид
 */
public class MainWindowHandler extends WindowAdapter {// работа крестика окна программы

    //WindowAdapter реализовал пустыми все методы виндовс листенера
    @Override
    public void windowClosing(WindowEvent we) {
        if (SaveData.getInstance().isSaved()) {
            System.exit(0);
        } else {
            int result = ConfirmDialog.show(
                    (MainFrame) we.getWindow(),
                    "CONFIRM_EXIT_TEXT",
                    "CONFIRM_EXIT_TITLE");
            if (result == JOptionPane.YES_OPTION) {
                System.exit(0);// если ДА, то закрыть программу
            }
        }
    }

}
