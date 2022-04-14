/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.settings;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import personalfinance.model.Currency;
import personalfinance.model.Filter;

/**
 *
 * @author Леонид
 */
final public class Format {

    public static String amount(double amount) {
        return String.format(Settings.FORMAT_AMOUNT, amount);
    }

    public static String amount(double amount, Currency currency) {
        return amount(amount) + " " + currency.getCode();
    }

    public static String rate(double rate) {
        return String.format(Settings.FORMAT_RATE, rate);
    }

    public static String rate(double rate, Currency currency) {
        return rate(rate) + " " + currency.getCode();
    }

    public static String date(Date date) {
        return dateFormat(date, Settings.FORMAT_DATE);
    }

    public static String dateMonth(Date date) {
        return dateFormat(date, Settings.FORMAT_DATE_MONTH);
    }

    public static String dateYear(Date date) {
        return dateFormat(date, Settings.FORMAT_DATE_YEAR);
    }
// SimpleDateFormat встроенный класс java
    private static String dateFormat(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, new MainDateFormatSymbols());
        return sdf.format(date);
    }

    public static double fromAmountToNumber(String amount) throws NumberFormatException {
        amount = amount.replace(",", ".");
        return Double.parseDouble(amount);
    }

    public static String yesNo(boolean yes) {
        if (yes) {
            return Text.get("YES");
        }
        return Text.get("NO");
    }

    public static String getTitleFilter(Filter filter) {// заголовок времени(год, месяц, день)
        Date time = filter.getTo();
        switch (filter.getStep()) {
            case Filter.STEP_DAY:
                return date(time);
            case Filter.STEP_MONTH:
                return dateMonth(time);
            case Filter.STEP_YEAR:
                return dateYear(time);
        }
        return null;
    }

    private static class MainDateFormatSymbols extends DateFormatSymbols {
        //класс для преобразования марта в Март

        @Override
        public String[] getMonths(){
            return Text.getMonths();
        }
    }

}
