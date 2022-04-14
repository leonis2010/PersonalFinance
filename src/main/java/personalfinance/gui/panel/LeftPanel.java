/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.gui.panel;

import java.awt.BorderLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import personalfinance.gui.MainFrame;
import personalfinance.model.Currency;
import personalfinance.model.Statistics;
import personalfinance.saveload.SaveData;
import personalfinance.settings.Format;
import personalfinance.settings.Style;
import personalfinance.settings.Text;

/**
 *
 * @author Леонид
 */
public final class LeftPanel extends AbstractPanel {

    public LeftPanel(MainFrame frame) {
        super(frame);
        init();
    }

    @Override
    protected void init() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));// выравниваем колонки по вертикали
        setBorder(Style.BORDER_LEFT_PANEL);//отступ от краёв программки
        JLabel headerBC = new JLabel(Text.get("BALANCE_CURRENCIES"));// ВЫВОДИМ заголовок
        headerBC.setFont(Style.FONT_LABEL_HEADER);// выставляем шрифт
        headerBC.setIcon(Style.ICON_LEFT_PANEL_BALANCE_CURRENCIES);// выставляем иконку
        headerBC.setAlignmentX(JComponent.CENTER_ALIGNMENT);// выравнивание заголовка по центру / по левому краю
        add(headerBC);// выводим на панель

        addBalanceCurrency();
        
        add(Box.createVerticalStrut(Style.PADDING_PANEL_BIG));
        
        JLabel headerB = new JLabel(Text.get("BALANCE"));//заголовок баланса
        headerB.setFont(Style.FONT_LABEL_HEADER);
        headerB.setIcon(Style.ICON_LEFT_PANEL_BALANCE);// выставляем иконку
        headerB.setAlignmentX(JComponent.CENTER_ALIGNMENT);// выравнивание заголовка по центру / по левому краю
        add(headerB);//добавляем
        addBalance();//добавляем
    }

    private void addBalanceCurrency() {
        for (Currency currency : SaveData.getInstance().getEnableCurrencies()) {
            add(Box.createVerticalStrut(Style.PADDING_BALANCE));
            add(new PanelBalanceCurrency(currency, Statistics.getBalanceCurrency(currency)));
        }
    }

    private void addBalance() {
        for (Currency currency : SaveData.getInstance().getEnableCurrencies()) {
            add(Box.createVerticalStrut(Style.PADDING_BALANCE));
            add(new PanelBalanceCurrency(currency, Statistics.getBalance(currency)));
        }
    }

    private class PanelBalanceCurrency extends JPanel {

        public PanelBalanceCurrency(Currency currency, double amount) {
            super();
            setLayout(new BorderLayout());//выровнять в одну строку
            setBackground(Style.COLOR_LEFTPANEL_BALANCE);
            setBorder(Style.BORDER_PANEL);//отступы между строками по высоте

            //ВЫВОДИМ метки с названием валюты + метка с её кодом
            JLabel currencyLabel = new JLabel(currency.getTitle());
            JLabel amountLabel = new JLabel(Format.amount(amount, currency));

            currencyLabel.setFont(Style.FONT_LABEL_LEFT_PANEL_CURRENCY);
            amountLabel.setFont(Style.FONT_LABEL_LEFT_PANEL_AMOUNT);

            add(currencyLabel, BorderLayout.WEST);// вставляем название слева
            add(Box.createRigidArea(Style.DIMENSION_PADDING_BALANCE));//вставляем пустой прямоугольник
            add(amountLabel, BorderLayout.EAST);// вставляем значения справа
        }

    }
}
