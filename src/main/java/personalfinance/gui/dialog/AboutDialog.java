/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.gui.dialog;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import personalfinance.settings.Style;
import personalfinance.settings.Text;

/**
 *
 * @author Леонид
 */
public class AboutDialog extends JDialog {         // о программе

    public AboutDialog() {
        super();
        init();
        setTitle(Text.get("DIALOG_ABOUT_TITLE"));
        setIconImage(Style.ICON_ABOUT.getImage());
        setResizable(false);
    }

    private void init() {
        JEditorPane pane = new JEditorPane("text/html", Text.get("ABOUT")); //ABOUT - языковая константа для html кода
        pane.setEditable(false);
        pane.setOpaque(false);//отключаем фон

        pane.addHyperlinkListener(new HyperlinkListener() {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent he) {
                if (HyperlinkEvent.EventType.ACTIVATED.equals(he.getEventType())) {
                    try {
                        //проверяем действительно ли пользователь кликнул по ссылке
                        Desktop.getDesktop().browse(he.getURL().toURI());
                    } catch (URISyntaxException | IOException ex) {}
                    //правильность написания адреса URISyntaxException
                }
            }
        });

        add(pane);
        pack();// размер диалогового окна в котором бы уместились все компоненты не больше и не меньше
        setLocationRelativeTo(null);//расположение окна по центру экрана
    }

}
