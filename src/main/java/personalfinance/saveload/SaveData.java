/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.saveload;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import personalfinance.exception.ModelException;
import personalfinance.model.*;

public final class SaveData {// здесь хранятся все статьи, счета, переводы итп - всё

    private static SaveData instance;// исп. паттерн singleton, чтобы нельзя было создать больше одного экземпляра данного класса
    private List<Article> articles = new ArrayList();
    private List<Currency> currencies = new ArrayList();
    private List<Account> accounts = new ArrayList();
    private List<Transaction> transactions = new ArrayList();
    private List<Transfer> transfers = new ArrayList();

    private Common oldCommon; //временное хранилище данных
    private final Filter filter;
    private boolean saved = true;

    private SaveData() {
        load();
        this.filter = new Filter();
    }

    public void load() {
        SaveLoad.load(this);
        sort();
        for (Account a : accounts) {
            a.setAmountFromTransactionsAndTransfers(transactions, transfers);
        }
    }

    public void clear() {
        articles.clear();
        currencies.clear();
        accounts.clear();
        transactions.clear();
        transfers.clear();
    }

    private void sort() {
        //сортировка по алфавиту
        this.articles.sort((Article a, Article a1) -> a.getTitle().compareToIgnoreCase(a1.getTitle()));
        this.accounts.sort((Account a, Account a1) -> a.getTitle().compareToIgnoreCase(a1.getTitle()));
        this.transactions.sort((Transaction t, Transaction t1) -> (int) (t1.getDate().compareTo(t.getDate())));
        this.transfers.sort((Transfer t, Transfer t1) -> (int) (t1.getDate().compareTo(t.getDate())));
        this.currencies.sort(new Comparator<Currency>() {
            //базовая валюта всегда стоит выше всех
            @Override
            public int compare(Currency c, Currency c1) {
                if (c.isBase()) {
                    return -1;
                }
                if (c1.isBase()) {
                    return 1;
                }
                if (c.isOn() ^ c1.isOn()) {
                    if (c1.isOn()) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
                return c.getTitle().compareToIgnoreCase(c1.getTitle());
            }
        });
    }

    public void save() {
        SaveLoad.save(this);
        saved = true;
    }

    public boolean isSaved() {
        return saved;
    }

    public Filter getFilter() {
        return filter;
    }

    public static SaveData getInstance() {//будет создаваться только один объект данного класса
        if (instance == null) {
            instance = new SaveData();
        }
        return instance;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public List<Transfer> getTransfers() {
        return transfers;
    }

    public Currency getBaseCurrency() {
        for (Currency c : currencies) {
            if (c.isBase()) {
                return c;
            }
        }
        return new Currency();
    }

    public void setArticles(List<Article> articles) {
        if (articles != null) {
            this.articles = articles;
        }
    }

    public void setCurrencies(List<Currency> currencies) {
        if (currencies != null) {
            this.currencies = currencies;
        }
    }

    public void setAccounts(List<Account> accounts) {
        if (accounts != null) {
            this.accounts = accounts;
        }
    }

    public void setTransactions(List<Transaction> transactions) {
        if (transactions != null) {
            this.transactions = transactions;
        }
    }

    public void setTransfers(List<Transfer> transfers) {
        if (transfers != null) {
            this.transfers = transfers;
        }
    }

    public ArrayList<Currency> getEnableCurrencies() {// включена ли валюта( будет выпадать в списке в меню слева или нет)
        ArrayList<Currency> list = new ArrayList();
        for (Currency c : currencies) {
            if (c.isOn()) {
                list.add(c);
            }
        }
        return list;
    }

    public List<Transaction> getFilterTransactions() {
        ArrayList<Transaction> list = new ArrayList();
        for (Transaction t : transactions) {
            if (filter.check(t.getDate())) {
                list.add(t);
            }
        }
        return list;
    }

    public List<Transfer> getFilterTransfers() {
        ArrayList<Transfer> list = new ArrayList();
        for (Transfer t : transfers) {
            if (filter.check(t.getDate())) {
                list.add(t);
            }
        }
        return list;
    }

    public List<Transaction> getTransactionsOnCount(int count) {
        //выводим на экран последние 10 транзакций, если их меньше то выводим минимальное
        return new ArrayList(transactions.subList(0, Math.min(count, transactions.size())));
    }

    public Common getoldCommon() {
        return oldCommon;
    }

    private List getRef(Common c) {
        if (c instanceof Account) {
            return accounts;
        } else if (c instanceof Article) {
            return articles;
        } else if (c instanceof Currency) {
            return currencies;
        } else if (c instanceof Transaction) {
            return transactions;
        } else if (c instanceof Transfer) {
            return transfers;
        }
        return null;
    }

    public void add(Common c) throws ModelException {// добавляем любые данные
        List ref = getRef(c);
        if (ref.contains(c)) {//если уже списке запись существует, то выбрасывается исключение
            throw new ModelException(ModelException.IS_EXISTS);
        }
        ref.add(c);
        c.postAdd(this);
        sort();
        saved = false;
    }

    public void edit(Common oldC, Common newC) throws ModelException {
        //изменяя объект, запрещаем изменять объект на такой же уже имеющийся
        List ref = getRef(oldC);
        if (ref.contains(newC) && oldC != ref.get(ref.indexOf(newC))) {
            throw new ModelException(ModelException.IS_EXISTS);
        }
        ref.set(ref.indexOf(oldC), newC);//получаем индекс старого объекта и заменяем его новым объектом
        oldCommon = oldC;
        newC.postEdit(this);//в зависимости от дочернего класса будет переопределяться
        sort();
        saved = false;
    }

    public void remove(Common c) {
        getRef(c).remove(c);//получив из него объект его удаляем
        c.postRemove(this);
        saved = false;
    }

    @Override
    public String toString() {
        return "SaveData{" + "articles=" + articles + ", currencies=" + currencies + ", accounts=" + accounts + ", transactions=" + transactions + ", transfers=" + transfers + ", oldCommon=" + oldCommon + '}';
    }

    public void updateCurrencies() throws Exception {
        HashMap<String, Double> rates = RateCurrency.getRates(getBaseCurrency());//String - ключ валюты double - её курс
        for (Currency c : currencies) {//перебираем все валюты и заменяем их на нашу
            c.setRate(rates.get(c.getCode()));
        }
        for (Account a : accounts) {
            a.getCurrency().setRate(rates.get(a.getCurrency().getCode()));
        }
        saved = false;
    }

}
