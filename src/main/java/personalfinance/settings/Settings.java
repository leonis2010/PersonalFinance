/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.settings;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import org.ini4j.Ini;
import org.ini4j.IniPreferences;
import org.ini4j.Wini;

/**
 *
 * @author Леонид
 */
//нужно установить библиотеку ini4j-0.5.4.jar для работы с ini файлами
final public class Settings {

    public static final File FONT_ROBOTO_LIGHT = new File("fonts/Roboto-Light.ttf");
    public static final File SAVE_DIR = new File("saves/"); // где сохраняться файлам
    public static final String SAVE_FILE_EXT = "myrus"; //расширение сохраняемых файлов

    public static final String FORMAT_AMOUNT = "%.2f"; //формат чисел для денег
    public static final String FORMAT_RATE = "%.4f"; // формат числа для курса
    public static final String FORMAT_DATE = "dd.MM.yyyy";
    public static final String FORMAT_DATE_MONTH = "MMMM yyyy";
    public static final String FORMAT_DATE_YEAR = "yyyy";

    public static final int COUNT_OVERVIEW_ROWS = 10;//кол-во строк показ-ся на глав. экране

    public static final String[] CURRENCIES_CODES = new String[]{"RUB", "USD", "EUR", "BYN", "UAH"};// здесь добавляем валюты

    private static final File FILE_SETTINGS = new File("saves/settings.ini");//путь к последнем открытому файлу
    private static File FILE_SAVE = new File("saves/default.myrus");//путь к последнем открытому файлу, если его нет то ставим default

    public static void init() {
        try {
            Ini ini = new Ini(FILE_SETTINGS);// запускается этот файл (указывается путь файла Settings)
            Preferences prefs = new IniPreferences(ini); // удобный доступ к настройкам
            String file = prefs.node("Settings").get("FILE_SAVE", null);
            //получаем строку из Settings, null - зн. по умолчанию если нет такого файла
            if (file != null) {
                FILE_SAVE = new File(file);//сохраняет на его основе объект файла
            }
            setLocale();
        } catch (IOException ex) {
            save();
        }
    }

    public static File getFileSave() {
        return FILE_SAVE;
    }

    public static void setFileSave(File file) {
        Settings.FILE_SAVE = file;
        save();
    }

    private static void setLocale() {
        Locale.setDefault(new Locale("ru"));// по умолчанию язык
    }

    private static void save() {
        Wini ini;//чтобы записать файл (группа Settings, параметр FILE_SAVE, путь к файлу FILE_SAVE.getAbsolutePath().replace("\\", "\\\\")))
        try {
            ini = new Wini(FILE_SETTINGS);//эта система сохранения настроек
            if (FILE_SAVE != null) {
                ini.put("Settings", "FILE_SAVE", FILE_SAVE.getAbsolutePath().replace("\\", "\\\\"));// символ экранирования след. символа \
            }
            ini.store();//сохраняем в ini файле    
        } catch (IOException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
