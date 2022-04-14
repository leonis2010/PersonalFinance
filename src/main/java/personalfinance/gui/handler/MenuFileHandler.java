/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.gui.handler;

import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import personalfinance.gui.MainFileChooser;
import personalfinance.gui.MainFrame;
import personalfinance.gui.dialog.ConfirmDialog;
import personalfinance.gui.dialog.ErrorDialog;
import personalfinance.saveload.SaveData;
import personalfinance.settings.HandlerCode;
import personalfinance.settings.Settings;

/**
 *
 * @author Леонид
 */
public class MenuFileHandler extends Handler {

    private final MainFileChooser fc;

    public MenuFileHandler(MainFrame frame) {
        super(frame);
        fc = new MainFileChooser(frame);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case HandlerCode.MENU_FILE_NEW: {
                Settings.setFileSave(null);
                SaveData.getInstance().clear();
                break;
            }
            case HandlerCode.MENU_FILE_OPEN: {
                int result = fc.open();
                if (result == JFileChooser.APPROVE_OPTION) {
                    Settings.setFileSave(fc.getSelectedFile());
                    SaveData.getInstance().clear();
                    SaveData.getInstance().load();
                    break;
                }
            }
            case HandlerCode.MENU_FILE_SAVE: {
                if (Settings.getFileSave() == null) {//проверям, пустой ли изначальный файл с сохранениями
                    int result = fc.save();
                    if (result == JFileChooser.APPROVE_OPTION) {// подтвердил, пользователь выбрал файл
                        String path = fc.getSelectedFile().getAbsolutePath();// путь к файлу
                        String ext = path.substring(path.lastIndexOf(".") + 1);//выделили string всё после последней "."
                        if (ext.equals(Settings.SAVE_FILE_EXT)) {//сравнили это с нашим расширением файла
                            Settings.setFileSave(new File(path));//если да, то просто сохранили файл с этим расширением
                        } else {// если нет
                            Settings.setFileSave(new File(path + "." + Settings.SAVE_FILE_EXT));//добавляем расширения файла
                        }
                    }
                }
                if (Settings.getFileSave() != null) {
                    SaveData.getInstance().save();
                }
                break;
            }
            case HandlerCode.MENU_FILE_UPDATE_CURRENCIES: {
                try {
                    SaveData.getInstance().updateCurrencies();
                } catch (Exception ex) {
                    ErrorDialog.show(frame, "ERROR_UPDATE_CURRENCIES");
                }
                break;
            }
            case HandlerCode.MENU_FILE_EXIT: {
                if (SaveData.getInstance().isSaved()) {
                    System.exit(0);
                } else {
                    int result = ConfirmDialog.show(frame, "CONFIRM_EXIT_TEXT", "CONFIRM_EXIT_TITLE");
                    if (result == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                }
                break;
            }

        }
        super.actionPerformed(ae);
    }
}
