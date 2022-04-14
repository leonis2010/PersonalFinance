/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.model;

import java.util.HashMap;
import java.util.List;
import personalfinance.saveload.SaveData;

/**
 *
 * @author Леонид
 */
public class Statistics {

    public static double getBalanceCurrency(Currency currency) {//суммируются все валюты
        SaveData sd = SaveData.getInstance();
        double amount = 0;
        for (Account account : sd.getAccounts()) {
            if (currency.equals(account.getCurrency())) {//если валюта счёта совпадает с передаваемой валютой
                amount += account.getAmount();
            }
        }
        return amount;
    }

    public static double getBalance(Currency currency) {//Общая сумма всех валют: если валюта счёта не будет совпадать с данной валютой, то будет конвертироваться
        SaveData sd = SaveData.getInstance();
        double amount = 0;
        for (Account account : sd.getAccounts()) {
            amount += account.getAmount() * account.getCurrency().getRateByCurrency(currency);//сумма * на коэф. конвертации
        }
        return amount;
    }

    public static HashMap<String, Double> getDataForChartOnIncomeArticles() {
        return getDataForChartOnArticles(true);
    }

    public static HashMap<String, Double> getDataForChartOnExpArticles() {
        return getDataForChartOnArticles(false);
    }

    private static HashMap<String, Double> getDataForChartOnArticles(boolean income) {//вывод на гистаграмму
        List<Transaction> transactions = SaveData.getInstance().getFilterTransactions();
        HashMap<String, Double> data = new HashMap();
        for (Transaction t : transactions) {
            if ((income && t.getAmount() > 0) || (!income && t.getAmount() < 0)) {//чтобы не суммировать доходы и расходы, а считать их раздельно
                String key = t.getArticle().getTitle();
                double summa = 0;
                double amount = t.getAmount();
                if (!income) {
                    amount *= -1;//чтобы расходы не были отрицательными в гистаграмме
                }
                if (data.containsKey(key)) {
                    summa = data.get(key);
                }
                //summa += кол-во * коэф от валюты (берётся из SaveData)
                summa += amount * t.getAccount().getCurrency().getRateByCurrency(SaveData.getInstance().getBaseCurrency());
                data.put(key, round(summa));
            }

        }
        return data;
    }
// классический метод для округления (напр. до 2 знаков)
    private static double round(double value) {
        return (double) Math.round(value * 100) / 100;
    }
}
