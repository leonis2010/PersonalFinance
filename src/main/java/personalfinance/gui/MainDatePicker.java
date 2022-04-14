/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.gui;

import java.util.Date;
import java.util.Properties;
import javax.swing.JButton;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import personalfinance.settings.Style;
import personalfinance.settings.Text;

/**
 *
 * @author Леонид
 */
public class MainDatePicker {

    private final JDatePickerImpl datePicker;

    public MainDatePicker() {
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", Text.get("TODAY"));
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);

        datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
        model.setValue(new Date());
        
        JButton button = (JButton) datePicker.getComponent(1);//устанавливаем картинку у кнопки даты вместо точек
        button.setIcon(Style.ICON_DATE);
        button.setText("");
    }

    public JDatePickerImpl getDatePicker() {
        return datePicker;
    }

    public void setValue(Date date) {
        ((UtilDateModel) datePicker.getModel()).setValue(date);
// если нам нужно чтобы подставлялась измененная дата
    }

}
