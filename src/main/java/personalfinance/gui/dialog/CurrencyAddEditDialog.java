/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.gui.dialog;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import personalfinance.exception.ModelException;
import personalfinance.gui.MainFrame;
import personalfinance.model.Common;
import personalfinance.model.Currency;
import personalfinance.settings.Format;
import personalfinance.settings.Settings;
import personalfinance.settings.Style;
import personalfinance.settings.Text;

/**
 *
 * @author Леонид
 */
public class CurrencyAddEditDialog extends AddEditDialog {

    public CurrencyAddEditDialog(MainFrame frame) {
        super(frame);
    }

    @Override
    protected void init() {
        components.put("LABEL_TITLE", new JTextField());
        components.put("LABEL_CODE", new JComboBox(Settings.CURRENCIES_CODES));//из настроек берём массив кодов валют
        components.put("LABEL_RATE", new JTextField());
        components.put("LABEL_ON", new JComboBox(new String[]{Text.get("YES"), Text.get("NO")}));//всплывает ДА или НЕТ
        components.put("LABEL_BASE", new JComboBox(new String[]{Text.get("YES"), Text.get("NO")}));//всплывает ДА или НЕТ

        icons.put("LABEL_TITLE", Style.ICON_TITLE);
        icons.put("LABEL_CODE", Style.ICON_CODE);
        icons.put("LABEL_RATE", Style.ICON_RATE);
        icons.put("LABEL_ON", Style.ICON_ON);
        icons.put("LABEL_BASE", Style.ICON_BASE);

        values.put("LABEL_RATE", Format.amount(1));// ставим значение 1 по умолчанию и форматируем под нужный формат

    }

    @Override
    protected void setValues() { // устанавливаем значения
        Currency currency = (Currency) c;// приводим "Common c"  к Account
        values.put("LABEL_TITLE", currency.getTitle());
        values.put("LABEL_CODE", currency.getCode());
        values.put("LABEL_RATE", currency.getRate());
        if (currency.isOn()) {
            values.put("LABEL_ON", Text.get("YES"));
        } else {
            values.put("LABEL_ON", Text.get("NO"));
        }
        if (currency.isBase()) {
            values.put("LABEL_BASE", Text.get("YES"));
        } else {
            values.put("LABEL_BASE", Text.get("NO"));
        }
        if(currency.isBase()){// если валюта базовая то не показывать изменение с базовой на не базовую
            components.remove("LABEL_BASE");
            components.remove("LABEL_ON");
        }
    }

    @Override
    public Common getCommonFromForm() throws ModelException { // обрабатываем
        try {
            String title = ((JTextField) components.get("LABEL_TITLE")).getText();
            String code = (String) ((JComboBox) components.get("LABEL_CODE")).getSelectedItem();
            String rate = ((JTextField) components.get("LABEL_RATE")).getText();
            boolean isOn = false;
            if (((JComboBox) components.get("LABEL_ON")).getSelectedItem().equals(Text.get("YES"))) {
                isOn = true;
            }
            boolean isBase = false;
            if (((JComboBox) components.get("LABEL_BASE")).getSelectedItem().equals(Text.get("YES"))) {
                isBase = true;
            }
            if (!isBase && c != null && ((Currency) c).isBase()) {//если убрать с базовой валюты yes и поставить no, то вылетет исключение
                throw new ModelException(ModelException.NO_BASE_CURRENCY);
            }
            return new Currency(title, code, Format.fromAmountToNumber(rate), isOn, isBase);
        } catch (NumberFormatException ex) {
            throw new ModelException(ModelException.AMOUNT_FORMAT);
        }
    }

}
