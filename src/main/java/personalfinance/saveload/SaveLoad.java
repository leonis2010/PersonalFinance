/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.saveload;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import personalfinance.settings.Settings;

public class SaveLoad {

    public static void load(SaveData sd) {

        try {
            JAXBContext context = JAXBContext.newInstance(Wrapper.class);// класс для загрузки
            Unmarshaller um = context.createUnmarshaller();// демаршализация (строки преобразует в объекты)
            Wrapper wrapper = (Wrapper) um.unmarshal(Settings.getFileSave());

            sd.setAccounts(wrapper.getAccounts());
            sd.setArticles(wrapper.getArticles());
            sd.setTransactions(wrapper.getTransactions());
            sd.setTransfers(wrapper.getTransfers());
            sd.setCurrencies(wrapper.getCurrencies());
        } catch (JAXBException ex) {
            System.out.println("Файл не существует!");
        }

    }

    public static void save(SaveData sd) {// СОХРАНЕНИЕ. SaveData sd - уже всё содержится
        try {
            JAXBContext context = JAXBContext.newInstance(Wrapper.class);// класс для сохранения
            Marshaller m = context.createMarshaller();// маршализация (превращения объектов в строки для записи в файл)
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);//маршалер строки будет писать с отступом(для красоты чтения)

            Wrapper wrapper = new Wrapper();
            
            // записываем первоначально данные в класс оболочку Wrapper
            wrapper.setAccounts(sd.getAccounts());
            wrapper.setArticles(sd.getArticles());
            wrapper.setTransactions(sd.getTransactions());
            wrapper.setTransfers(sd.getTransfers());
            wrapper.setCurrencies(sd.getCurrencies());
            
            m.marshal(wrapper, Settings.getFileSave());// указываем оболочку и куда сохранять

        } catch (JAXBException ex) {
            Logger.getLogger(SaveLoad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
