/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.gui.dialog;

import javax.swing.JOptionPane;
import personalfinance.gui.MainFrame;
import personalfinance.settings.Text;

/**
 *
 * @author Леонид
 */
public class ConfirmDialog {

    public static int show(MainFrame frame, String text, String title) {
        String[] options = {Text.get("YES"), Text.get("NO")};
        int result = JOptionPane.showOptionDialog(
                frame,
                Text.get(text),
                Text.get(title),
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]
        );
        // option[1] выбирает выделение кнопки по умолчанию (NO)
        return result;
    }
}
