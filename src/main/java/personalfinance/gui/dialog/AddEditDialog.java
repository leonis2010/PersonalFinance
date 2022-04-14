/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.gui.dialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import personalfinance.exception.ModelException;
import personalfinance.gui.MainButton;
import personalfinance.gui.MainFrame;
import personalfinance.gui.handler.AddEditDialogHandler;
import personalfinance.model.Common;
import personalfinance.settings.HandlerCode;
import personalfinance.settings.Style;
import personalfinance.settings.Text;

/**
 *
 * @author Леонид
 */
public abstract class AddEditDialog extends JDialog {

    private final MainFrame frame;
    protected LinkedHashMap<String, JComponent> components = new LinkedHashMap<>();
    protected LinkedHashMap<String, ImageIcon> icons = new LinkedHashMap<>();
    protected LinkedHashMap<String, Object> values = new LinkedHashMap<>();
    protected Common c;

    public AddEditDialog(MainFrame frame) {
        super(frame, Text.get("ADD"), true);// пока активно окно, нельзя ткнуть мышкой в предыдущее окно
        this.frame = frame;
        addWindowListener(new AddEditDialogHandler(frame,this));
        setResizable(false);//нельзя растягивать окно

    }

    public Common getCommon() {
        return c;
    }

    public void setCommon(Common c) {
        this.c = c;
    }

    public final void showDialog() {
        setDialog();
        setVisible(true);//сделать окно видимым
    }

    public final void closeDialog() {
        setVisible(false);// окно не видимо
        this.c = null;
        components.clear();
        icons.clear();
        values.clear();
        dispose();//очищает оперативную память
    }

    public boolean isAdd() {//если null то есть true значит мы создаем, false значит изменяем
        return c == null;
    }

    //эти три абстрактных класса обязаны заполнять дочерние классы:
    abstract protected void init();

    abstract protected void setValues();

    public abstract Common getCommonFromForm() throws ModelException;//для сохранения всех введённых полей окна

    private void setDialog() {// создание интерфейса самого окно с кнопками итп
        init();
        if (isAdd()) {
            setTitle(Text.get("ADD"));// название взяли из файла Text
            setIconImage(Style.ICON_ADD.getImage());// поставили иконку рядом с названием окна
        } else {
            setValues();
            setTitle(Text.get("EDIT"));
            setIconImage(Style.ICON_EDIT.getImage());
        }
        getContentPane().removeAll();//всё стирает из полей окна
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));//выравнивание по оси Y всех панелей
        ((JPanel) getContentPane()).setBorder(Style.BORDER_DIALOG);//устанавливаем рамку
        for (Map.Entry<String, JComponent> entry : components.entrySet()) {
            String key = entry.getKey();
            JLabel label = new JLabel(Text.get(key));
            label.setIcon(icons.get(key));//иконки тоже ищем по ключу
            label.setFont(Style.FONT_DIALOG_LABEL);

            JComponent component = entry.getValue();
            if (component instanceof JTextField) {// для текстового поля
                component.setPreferredSize(Style.DIMENSION_DIALOG_TEXTFIELD_SIZE);//указали размер ввиде прямоугольника
                if (values.containsKey(key)) {
                    ((JTextField) component).setText("" + values.get(key));
                }
            } else if (component instanceof JComboBox) {
                if (values.containsKey(key)) {
                    ((JComboBox) component).setSelectedItem("" + values.get(key));
                }

            } else if (component instanceof JDatePickerImpl) {
                if (values.containsKey(key)) {
                    ((UtilDateModel) ((JDatePickerImpl) component).getModel()).setValue((Date) values.get(key));
                }
            }
            component.addKeyListener(new AddEditDialogHandler(frame,this));
            
            // надо всё прижать к левому краю
            component.setAlignmentX(JComponent.LEFT_ALIGNMENT);// равняем
            add(label);// добавляем метку
            add(Box.createVerticalStrut(Style.PADDING_DIALOG));//делаем отступ сверху
            add(component);
            add(Box.createVerticalStrut(Style.PADDING_DIALOG));//делаем отступ сверху
        }
        MainButton ok = new MainButton(Text.get("ADD"), Style.ICON_OK, new AddEditDialogHandler(frame,this), HandlerCode.ADD);
        if (!isAdd()) {
            ok.setActionCommand(HandlerCode.EDIT);
            ok.setText(Text.get("EDIT"));
        }
        MainButton cancel = new MainButton(Text.get("CANCEL"), Style.ICON_CANCEL, new AddEditDialogHandler(frame,this), HandlerCode.CANCEL);

        JPanel panelButtons = new JPanel();// создаем отдельную панель для кнопок
        panelButtons.setLayout(new BorderLayout());
        
        panelButtons.setAlignmentX(JPanel.LEFT_ALIGNMENT);// панель кнопок выравниваем по левому краю
        panelButtons.add(ok, BorderLayout.WEST);//одна кнопка на западе
        panelButtons.add(Box.createRigidArea(Style.DIMENSION_DIALOG_PADDING_BUTTON), BorderLayout.CENTER);//одна кнопка на западе
        panelButtons.add(cancel, BorderLayout.EAST);//другая на востоке

        add(panelButtons);
        pack();// размер окна подогнан под элементы в него входящие
        setLocationRelativeTo(null);//по центру экрана

    }
    // создаем внутренник класс который будет отвечать за комбобоксы

    protected class CommonComboBox extends JComboBox {

        public CommonComboBox(Object[] objs) {
            super(objs);

            setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    DefaultListCellRenderer renderer = (DefaultListCellRenderer) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    Common c = (Common) value;
                    if (c != null) {
                        renderer.setText(c.getValueForComboBox());//хотя это весь объект, но пользователь видит лишь title от этого объекта
                    }
                    return renderer;
                }
            });

        }

    }
}
