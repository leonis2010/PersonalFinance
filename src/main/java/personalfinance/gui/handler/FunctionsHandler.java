/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.gui.handler;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import personalfinance.gui.MainFrame;
import personalfinance.gui.dialog.AddEditDialog;
import personalfinance.gui.dialog.ConfirmDialog;
import personalfinance.gui.table.TableData;
import personalfinance.gui.table.model.MainTableModel;
import personalfinance.model.Common;
import personalfinance.saveload.SaveData;
import personalfinance.settings.HandlerCode;

/**
 *
 * @author Леонид
 */
//задача: удалять строки, выводить диалговые окна на добавления и редактирования
public class FunctionsHandler extends Handler implements MouseListener, KeyListener {

    private final AddEditDialog dialog;

    public FunctionsHandler(MainFrame frame, AddEditDialog dialog) {
        super(frame);
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {    //анауем какая нажата кнопка
            case HandlerCode.ADD:
                add();
                break;
            case HandlerCode.EDIT:
                edit();
                break;
            case HandlerCode.DELETE:
                delete();
                break;
        }
        super.actionPerformed(ae);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() instanceof TableData) {//если кликнули по таблице
            if (me.getClickCount() == 2 && me.getButton() == MouseEvent.BUTTON1) {//BOTTON1 - левая кнопка мышки
                showAddEditDialog(getSelectedCommon());
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if (me.getSource() instanceof TableData) {//если кликнули по таблице
            if (me.getButton() == MouseEvent.BUTTON3) {//BOTTON3 - правая кнопка мышки
                TableData td = frame.getRightPanel().getTableData();
                int row = td.rowAtPoint(me.getPoint());
                td.setRowSelectionInterval(row, row);//выделяет строку
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {

    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {

    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_DELETE) {//если пользователь нажимает клавишу DELETE, то вызывается метод удаления
            delete();
            frame.refresh();//возможно нужно будет передвинуть вниз
        }

    }

    public void add() {
        showAddEditDialog(null);
    }

    public void edit() {
        showAddEditDialog(getSelectedCommon());
    }

    public void delete() {
        Common c = getSelectedCommon();
        if (c != null) {
            int result = ConfirmDialog.show(frame, "CONFIRM_DELETE_TEXT", "CONFIRM_DELETE_TITLE");
            if (result == JOptionPane.YES_OPTION) {
                SaveData.getInstance().remove(c);
            }
        }
    }

    private Common getSelectedCommon() {//получить выделенную сущность
        TableData td = frame.getRightPanel().getTableData();
        Common c = ((MainTableModel) td.getModel()).getCommonByRow(td.getSelectedRow()); // получаем строку, на его основе получаем объект и записываем его в с
        return c;
    }

    private void showAddEditDialog(Common c) {
        dialog.setCommon(c);
        dialog.showDialog();//открывает диалоговое окно и добавляем запись в зависимости от того что попадет в конструктор
    }

}
