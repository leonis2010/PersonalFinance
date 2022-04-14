/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.gui.handler;

import java.awt.event.ActionEvent;
import personalfinance.gui.MainFrame;
import personalfinance.gui.panel.AccountPanel;
import personalfinance.gui.panel.ArticlePanel;
import personalfinance.gui.panel.CurrencyPanel;
import personalfinance.gui.panel.OverViewPanel;
import personalfinance.gui.panel.StatisticsPanel;
import personalfinance.gui.panel.TransactionPanel;
import personalfinance.gui.panel.TransferPanel;
import personalfinance.settings.HandlerCode;

/**
 *
 * @author Леонид
 */
public class MenuViewHandler extends Handler {//обработчик

    public MenuViewHandler(MainFrame frame) {
        super(frame);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case HandlerCode.MENU_VIEW_OVERVIEW: {
                showOverViewPanel();
                break;
            }
            case HandlerCode.MENU_VIEW_ACCOUNTS: {
                showAccountPanel();
                break;
            }
            case HandlerCode.MENU_VIEW_ARTICLES: {
                showArticlePanel();
                break;
            }
            case HandlerCode.MENU_VIEW_TRANSACTIONS: {
                showTransactionPanel();
                break;
            }
            case HandlerCode.MENU_VIEW_TRANSFERS: {
                showTransferPanel();
                break;
            }
            case HandlerCode.MENU_VIEW_CURRENCIES: {
                showCurrencyPanel();
                break;
            }
            case HandlerCode.MENU_VIEW_STATISTICS: {
                showStatisticsPanel();
                break;
            }
        }
        super.actionPerformed(ae);
    }

    protected void showOverViewPanel() {
        frame.setRightPanel(new OverViewPanel(frame));
    }

    protected void showAccountPanel() {
        frame.setRightPanel(new AccountPanel(frame));
    }

    protected void showArticlePanel() {
        frame.setRightPanel(new ArticlePanel(frame));
    }

    protected void showTransactionPanel() {
        frame.setRightPanel(new TransactionPanel(frame));
    }

    protected void showTransferPanel() {
        frame.setRightPanel(new TransferPanel(frame));
    }

    protected void showCurrencyPanel() {
        frame.setRightPanel(new CurrencyPanel(frame));
    }

    protected void showStatisticsPanel() {
        frame.setRightPanel(new StatisticsPanel(frame));
    }

}
