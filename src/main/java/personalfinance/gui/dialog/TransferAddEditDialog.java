/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.gui.dialog;

import java.util.Date;
import javax.swing.JTextField;
import org.jdatepicker.impl.JDatePickerImpl;
import personalfinance.exception.ModelException;
import personalfinance.gui.MainDatePicker;
import personalfinance.gui.MainFrame;
import personalfinance.model.Account;
import personalfinance.model.Common;
import personalfinance.model.Transfer;
import personalfinance.saveload.SaveData;
import personalfinance.settings.Format;
import personalfinance.settings.Style;

/**
 *
 * @author Леонид
 */
public class TransferAddEditDialog extends AddEditDialog {

    public TransferAddEditDialog(MainFrame frame) {
        super(frame);
    }

    @Override
    protected void init() {
        components.put("LABEL_DATE", new MainDatePicker().getDatePicker());
        components.put("LABEL_FROM_ACCOUNT", new CommonComboBox(SaveData.getInstance().getAccounts().toArray()));
        components.put("LABEL_TO_ACCOUNT", new CommonComboBox(SaveData.getInstance().getAccounts().toArray()));
        components.put("LABEL_FROM_AMOUNT", new JTextField());
        components.put("LABEL_TO_AMOUNT", new JTextField());
        components.put("LABEL_NOTICE", new JTextField());

        icons.put("LABEL_DATE", Style.ICON_DATE);
        icons.put("LABEL_FROM_ACCOUNT", Style.ICON_ACCOUNT);
        icons.put("LABEL_TO_ACCOUNT", Style.ICON_ACCOUNT);
        icons.put("LABEL_FROM_AMOUNT", Style.ICON_AMOUNT);
        icons.put("LABEL_TO_AMOUNT", Style.ICON_AMOUNT);
        icons.put("LABEL_NOTICE", Style.ICON_NOTICE);

        values.put("LABEL_DATE", new Date());//ставим дополнительно по умолчанию дату, хотя в DatePicker уже ставится дата по умолчанию
        values.put("LABEL_FROM_AMOUNT", Format.amount(0));//формат по умолчанию 0
        values.put("LABEL_TO_AMOUNT", Format.amount(0));//формат по умолчанию 0
    }

    @Override
    protected void setValues() { // устанавливаем значения
        Transfer transfer = (Transfer) c;// приводим "Common c"  к transfer
        values.put("LABEL_DATE", transfer.getDate());
        values.put("LABEL_FROM_ACCOUNT", transfer.getFromAccount());
        values.put("LABEL_TO_ACCOUNT", transfer.getToAccount());
        values.put("LABEL_FROM_AMOUNT", transfer.getFromAmount());
        values.put("LABEL_TO_AMOUNT", transfer.getToAmount());
        values.put("LABEL_NOTICE", transfer.getNotice());
    }

    @Override
    public Common getCommonFromForm() throws ModelException { // обрабатываем
        try {
            Account fromAccount = (Account) ((CommonComboBox) components.get("LABEL_FROM_ACCOUNT")).getSelectedItem();
            Account toAccount = (Account) ((CommonComboBox) components.get("LABEL_TO_ACCOUNT")).getSelectedItem();
            String fromAmount = ((JTextField) components.get("LABEL_FROM_AMOUNT")).getText();
            String toAmount = ((JTextField) components.get("LABEL_TO_AMOUNT")).getText();
            String notice = ((JTextField) components.get("LABEL_NOTICE")).getText();
            Date date = (Date) ((JDatePickerImpl) components.get("LABEL_DATE")).getModel().getValue();
            return new Transfer(fromAccount, toAccount, Format.fromAmountToNumber(fromAmount), Format.fromAmountToNumber(toAmount), notice, date);
        } catch (NumberFormatException ex) {
            throw new ModelException(ModelException.AMOUNT_FORMAT);
        }
    }

}
