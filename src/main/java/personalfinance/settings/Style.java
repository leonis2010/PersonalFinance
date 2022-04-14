/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.settings;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Леонид
 */
final public class Style {

    public static final Color COLOR_BUTTON_BG_NORMAL = new Color(240, 240, 240);// задний фон, по умолчанию
    public static final Color COLOR_BUTTON_BG_HOVER = Color.YELLOW;// задний фон, если навестикурсор мыши
    public static final Color COLOR_LEFTPANEL_BALANCE = Color.WHITE;
    public static final Color COLOR_EXP = new Color(200, 0, 0);
    public static final Color COLOR_INCOME = new Color(0, 100, 0);
    public static final Color COLOR_ON = Color.BLACK;
    public static final Color COLOR_OFF = new Color(170, 170, 170);

    public static final Font FONT_BUTTON_TOOLBAR = new Font("Roboto-Light", Font.BOLD, 14);// для кнопок в тулбаре
    public static final Font FONT_MAIN_BUTTON = new Font("Roboto-Light", Font.BOLD, 14);//текст кнопок
    public static final Font FONT_DIALOG_LABEL = new Font("Roboto-Light", Font.BOLD, 12);// подписи
    public static final Font FONT_LABEL_HEADER = new Font("Roboto-Light", Font.BOLD, 16);// левая панель шрифт
    public static final Font FONT_LABEL_LEFT_PANEL_CURRENCY = new Font("Roboto-Light", Font.BOLD, 14);// ЖИРНЫЙ левая панель шрифт
    public static final Font FONT_LABEL_LEFT_PANEL_AMOUNT = new Font("Roboto-Light", Font.PLAIN, 14);// ОБЫЧНЫЙ левая панель шрифт
    public static final Font FONT_TABLE_HEADER = new Font("Roboto-Light", Font.BOLD, 16);// ОБЫЧНЫЙ левая панель шрифт
    public static final Font FONT_TABLE = new Font("Roboto-Light", Font.PLAIN, 14);// ОБЫЧНЫЙ левая панель шрифт
    public static final Font FONT_BUTTON_FILTER = new Font("Roboto-Light", Font.BOLD, 12);// ОБЫЧНЫЙ левая панель шрифт

    public static final EmptyBorder BORDER_PANEL = new EmptyBorder(10, 10, 10, 10);//пустая рамка обрамления
    public static final EmptyBorder BORDER_LEFT_PANEL = new EmptyBorder(0, 10, 10, 10);//пустая рамка обрамления
    public static final EmptyBorder BORDER_MAIN_TOOLBAR = new EmptyBorder(10, 10, 10, 10);//пустая рамка обрамления ВОКРУГ КНОПОК СВЕРХУ И СНИЗУ;
    public static final EmptyBorder BORDER_FUNCTIONS_TOOLBAR = new EmptyBorder(5, 5, 5, 5);//пустая рамка обрамления ВОКРУГ КНОПОК СВЕРХУ И СНИЗУ;
    public static final EmptyBorder BORDER_DIALOG = new EmptyBorder(10, 10, 10, 10);//пустая рамка обрамления ВОКРУГ КНОПОК по кругу;
    public static final EmptyBorder BORDER_FILTER_PANEL = new EmptyBorder(5, 0, 7, 0);//пустая рамка обрамления ВОКРУГ КНОПОК по кругу;
    
    public static final Dimension DIMENSION_DIALOG_TEXTFIELD_SIZE = new Dimension(200, 25);//рисуем прямоугольник окна
    public static final Dimension DIMENSION_DIALOG_PADDING_BUTTON = new Dimension(10, 0);//рисуем прямоугольник пустой между кнопками высота не важна, главно раздвинуть их
    public static final Dimension DIMENSION_PADDING_BALANCE = new Dimension(10, 0);//рисуем прямоугольник пустой между КОЛОНКАМИ высота не важна, главно раздвинуть их
    public static final Dimension DIMENSION_TABLE_SHOW_SIZE = new Dimension(850, 450);//таблица со скроллом
    public static final Dimension DIMENSION_CHART = new Dimension(868, 550);//таблица под надпись: нет данных

    public static final int PADDING_DIALOG = 10;// по вертикали расстояние между кнопками итп.
    public static final int PADDING_BALANCE = 3;// по вертикали расстояние между кнопками итп.
    public static final int PADDING_PANEL = 3;// по вертикали расстояние между кнопками итп.
    public static final int PADDING_PANEL_BIG = 20;// по вертикали расстояние между кнопками итп.
    public static final int PADDING_PANEL_EMPTY = 5;// по вертикали расстояние между кнопками итп.
    public static final int TABLE_ADD_ROW_HEIGHT = 18;// по вертикали расстояние между кнопками итп.
    public static final int WIDTH_FILTER_BUTTON = 200;// по вертикали расстояние между кнопками итп.

    public static final ImageIcon ICON_MENU_FILE = new ImageIcon("images/menu_file.png");
    public static final ImageIcon ICON_MENU_EDIT = new ImageIcon("images/menu_edit.png");
    public static final ImageIcon ICON_MENU_VIEW = new ImageIcon("images/menu_view.png");
    public static final ImageIcon ICON_MENU_HELP = new ImageIcon("images/menu_help.png");

    public static final ImageIcon ICON_MENU_FILE_NEW = new ImageIcon("images/menu_file_new.png");
    public static final ImageIcon ICON_MENU_FILE_OPEN = new ImageIcon("images/menu_file_open.png");
    public static final ImageIcon ICON_MENU_FILE_SAVE = new ImageIcon("images/menu_file_save.png");
    public static final ImageIcon ICON_MENU_FILE_UPDATE_CURRENCIES = new ImageIcon("images/menu_file_update_currencies.png");
    public static final ImageIcon ICON_MENU_FILE_EXIT = new ImageIcon("images/menu_file_exit.png");

    public static final ImageIcon ICON_MENU_EDIT_ADD = new ImageIcon("images/menu_edit_add.png");
    public static final ImageIcon ICON_MENU_EDIT_EDIT = new ImageIcon("images/menu_edit_edit.png");
    public static final ImageIcon ICON_MENU_EDIT_DELETE = new ImageIcon("images/menu_edit_delete.png");

    public static final ImageIcon ICON_MENU_VIEW_OVERVIEW = new ImageIcon("images/menu_view_overview.png");
    public static final ImageIcon ICON_MENU_VIEW_ACCOUNTS = new ImageIcon("images/menu_view_accounts.png");
    public static final ImageIcon ICON_MENU_VIEW_ARTICLES = new ImageIcon("images/menu_view_articles.png");
    public static final ImageIcon ICON_MENU_VIEW_TRANSACTIONS = new ImageIcon("images/menu_view_transactions.png");
    public static final ImageIcon ICON_MENU_VIEW_TRANSFERS = new ImageIcon("images/menu_view_transfers.png");
    public static final ImageIcon ICON_MENU_VIEW_CURRENCIES = new ImageIcon("images/menu_view_currencies.png");
    public static final ImageIcon ICON_MENU_VIEW_STATISTICS = new ImageIcon("images/menu_view_statistics.png");

    public static final ImageIcon ICON_MENU_HELP_ABOUT = new ImageIcon("images/menu_help_about.png");
    public static final ImageIcon ICON_ABOUT = new ImageIcon("images/menu_help_about.png");
    
    public static final ImageIcon ICON_MENU_POPUP_EDIT = new ImageIcon("images/menu_popup_edit.png");
    public static final ImageIcon ICON_MENU_POPUP_DELETE = new ImageIcon("images/menu_popup_delete.png");

    public static final ImageIcon ICON_MAIN = new ImageIcon("images/main.png");// маленькая иконка (сверху слева) во главе всей программы
    public static final ImageIcon ICON_TOOLBAR_OVERVIEW = new ImageIcon("images/overview.png");
    public static final ImageIcon ICON_TOOLBAR_ACCOUNTS = new ImageIcon("images/accounts.png");
    public static final ImageIcon ICON_TOOLBAR_ARTICLES = new ImageIcon("images/articles.png");
    public static final ImageIcon ICON_TOOLBAR_TRANSACTIONS = new ImageIcon("images/transactions.png");
    public static final ImageIcon ICON_TOOLBAR_TRANSFERS = new ImageIcon("images/transfers.png");
    public static final ImageIcon ICON_TOOLBAR_CURRENCIES = new ImageIcon("images/currencies.png");
    public static final ImageIcon ICON_TOOLBAR_STATISTICS = new ImageIcon("images/statistics.png");
    

    public static final ImageIcon ICON_ADD = new ImageIcon("images/add.png");
    public static final ImageIcon ICON_EDIT = new ImageIcon("images/edit.png");
    public static final ImageIcon ICON_DELETE = new ImageIcon("images/delete.png");

    public static final ImageIcon ICON_DATE = new ImageIcon("images/date.png");

    public static final ImageIcon ICON_OK = new ImageIcon("images/ok.png");
    public static final ImageIcon ICON_CANCEL = new ImageIcon("images/cancel.png");

    public static final ImageIcon ICON_TITLE = new ImageIcon("Images/title.png");
    public static final ImageIcon ICON_CURRENCY = new ImageIcon("Images/currency.png");
    public static final ImageIcon ICON_AMOUNT = new ImageIcon("Images/amount.png");
    public static final ImageIcon ICON_ACCOUNT = new ImageIcon("Images/account.png");
    public static final ImageIcon ICON_ARTICLE = new ImageIcon("Images/article.png");
    public static final ImageIcon ICON_NOTICE = new ImageIcon("Images/notice.png");

    public static final ImageIcon ICON_CODE = new ImageIcon("Images/code.png");
    public static final ImageIcon ICON_RATE = new ImageIcon("Images/rate.png");
    public static final ImageIcon ICON_ON = new ImageIcon("Images/on.png");
    public static final ImageIcon ICON_BASE = new ImageIcon("Images/base.png");

    public static final ImageIcon ICON_LEFT_PANEL_BALANCE_CURRENCIES = new ImageIcon("Images/balance_currencies.png");
    public static final ImageIcon ICON_LEFT_PANEL_BALANCE = new ImageIcon("Images/balance.png");
    
    public static final ImageIcon ICON_LEFT = new ImageIcon("Images/left.png");
    public static final ImageIcon ICON_RIGHT = new ImageIcon("Images/right.png");
    
    public static final ImageIcon ICON_PANEL_OVERVIEW = new ImageIcon("Images/overview_panel.png");
    public static final ImageIcon ICON_PANEL_TRANSACTIONS = new ImageIcon("Images/transactions_panel.png");
    public static final ImageIcon ICON_PANEL_ACCOUNTS = new ImageIcon("Images/accounts_panel.png");
    public static final ImageIcon ICON_PANEL_ARTICLES = new ImageIcon("Images/articles_panel.png");
    public static final ImageIcon ICON_PANEL_TRANSFERS = new ImageIcon("Images/transfers_panel.png");
    public static final ImageIcon ICON_PANEL_CURRENCIES = new ImageIcon("Images/currencies_panel.png");
    public static final ImageIcon ICON_PANEL_STATISTICS = new ImageIcon("Images/statistics_panel.png");

}
