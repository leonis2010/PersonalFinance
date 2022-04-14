/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.gui.table.model;

import personalfinance.model.Transaction;
import personalfinance.saveload.SaveData;
import personalfinance.settings.Format;

/**
 *
 * @author Леонид
 */
public class TransactionTableModel extends MainTableModel {

    private static final int DATE = 0;
    private static final int ACCOUNT = 1;
    private static final int ARTICLE = 2;
    private static final int AMOUNT = 3;
    private static final int NOTICE = 4;

    private int count = -1;

    public TransactionTableModel(String[] columns) {
        super(SaveData.getInstance().getFilterTransactions(), columns);//нужны транзакции по фильтру (дате)

    }

    public TransactionTableModel(String[] columns, int count) {
        super(SaveData.getInstance().getTransactionsOnCount(count), columns);// выводим последние транзакции

        this.count = count;
    }

    @Override
    protected void updateData() {

        if (count == -1) {
            data = SaveData.getInstance().getFilterTransactions();//если -1 тогда используем фильтр
        } else {
            data = SaveData.getInstance().getTransactionsOnCount(count);// если уже что-то есть, значит используем последние транзакции
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
        if (data.isEmpty()) {
            return null;
        }
        Transaction transaction = (Transaction) data.get(row);
        switch (column) {
            case DATE:
                return Format.date(transaction.getDate());
            case ACCOUNT:
                return transaction.getAccount().getTitle();
            case ARTICLE:
                return transaction.getArticle().getTitle();
            case AMOUNT:
                return Format.amount(transaction.getAmount(), transaction.getAccount().getCurrency());
            case NOTICE:
                return transaction.getNotice();

        }
        return null;

    }

}
