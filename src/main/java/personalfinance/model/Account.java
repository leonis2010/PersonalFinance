/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.model;

import java.util.List;
import java.util.Objects;
import personalfinance.exception.ModelException;
import personalfinance.saveload.SaveData;

/**
 *
 * @author Леонид
 */
public class Account extends Common {

    private String title;
    private Currency currency;
    private double startAmount;
    private double amount;

    public Account() {
    }

    public Account(String title, Currency currency, double startAmount) throws ModelException {
        if (title.length() == 0) {
            throw new ModelException(ModelException.TITLE_EMPTY);
        }
        if (currency == null) {
            throw new ModelException(ModelException.CURRENCY_EMPTY);
        }
        this.title = title;
        this.currency = currency;
        this.startAmount = startAmount;
    }

    public double getAmount() {
        return amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.title);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Account other = (Account) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        return true;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public double getStartAmount() {
        return startAmount;
    }

    public void setStartAmount(double startAmount) {
        this.startAmount = startAmount;
    }

    @Override
    public String getValueForComboBox() {
        return title;
    }

    public void setAmountFromTransactionsAndTransfers(List<Transaction> transactions, List<Transfer> transfers) {
        this.amount = startAmount;
        for (Transaction transaction : transactions) {// перебираем все транзакции и нужно выделить, которые относятся к данному счёту
            if (transaction.getAccount().equals(this)) {
                this.amount += transaction.getAmount();
            }
        }
        for (Transfer transfer : transfers) {
            if (transfer.getFromAccount().equals(this)) {
                this.amount -= transfer.getFromAmount();//вычитаем сумму снятого с этого же счета
            }
            if (transfer.getToAccount().equals(this)) {
                this.amount += transfer.getToAmount();
            }
        }
    }

    @Override
    public void postAdd(SaveData sd) {

        setAmountFromTransactionsAndTransfers(sd.getTransactions(), sd.getTransfers());
    }

    public void postEdit(SaveData sd) {

        for (Transaction t : sd.getFilterTransactions()) {
            if (t.getAccount().equals(sd.getoldCommon())) {
                t.setAccount(this);
            }
        }
        for (Transfer t : sd.getTransfers()) {
            if (t.getFromAccount().equals(sd.getoldCommon())) {
                t.setFromAccount(this);
            }
            if (t.getToAccount().equals(sd.getoldCommon())) {
                t.setToAccount(this);
            }
        }
        setAmountFromTransactionsAndTransfers(sd.getTransactions(), sd.getTransfers());
    }

    @Override
    public String toString() {
        return "Account{" + "title=" + title + ", currency=" + currency + ", startAmount=" + startAmount + ", amount=" + amount + '}';
    }

}
