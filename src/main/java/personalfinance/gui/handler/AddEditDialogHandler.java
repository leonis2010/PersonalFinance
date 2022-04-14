/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.gui.handler;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import personalfinance.exception.ModelException;
import personalfinance.gui.MainFrame;
import personalfinance.gui.dialog.AddEditDialog;
import personalfinance.gui.dialog.ErrorDialog;
import personalfinance.saveload.SaveData;
import personalfinance.settings.HandlerCode;

/**
 *
 * @author Леонид
 */
//обработчик диалоговых окон
//KeyListener -реагировал, при нажатии на Энтер, WindowListener - чтобы диалоговое окно правильно закрывалось, при нажатии на крестик
public class AddEditDialogHandler extends Handler implements WindowListener, KeyListener {

    private final AddEditDialog dialog;

    public AddEditDialogHandler(MainFrame frame, AddEditDialog dialog) {
        super(frame);
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {    //анализируем какая нажата кнопка
            case HandlerCode.ADD:
                addEdit(true);
                break;
            case HandlerCode.EDIT:
                addEdit(false);
                break;
            case HandlerCode.CANCEL:
                closeDialog();
                break;
        }
        super.actionPerformed(ae);
    }

    private void addEdit(boolean add) {
        try {
            if (add) {
                SaveData.getInstance().add(dialog.getCommonFromForm());
            } else {
                SaveData.getInstance().edit(dialog.getCommon(), dialog.getCommonFromForm());
            }
            closeDialog();//закроется окно
        } catch (ModelException ex) {
            ErrorDialog.show(frame, ex.getMessage());
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            addEdit(dialog.isAdd());
            
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        closeDialog();
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    private void closeDialog() {
        dialog.closeDialog();
    }

}
