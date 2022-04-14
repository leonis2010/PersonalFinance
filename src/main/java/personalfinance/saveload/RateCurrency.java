/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.saveload;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import personalfinance.model.Currency;

/**
 *
 * @author Леонид
 */
public class RateCurrency {

    public static HashMap<String, Double> getRates(Currency base) throws Exception {
        //NodeList - в XML документе параграф (абзац)
        HashMap<String, NodeList> result = new HashMap();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String url = "https://www.cbr.ru/scripts/XML_daily.asp?date_req=" + dateFormat.format(new Date());
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document doc = factory.newDocumentBuilder().parse(new URL(url).openStream());
        NodeList nl = doc.getElementsByTagName("Valute");
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            NodeList nlChilds = node.getChildNodes();
            for (int j = 0; j < nlChilds.getLength(); j++) {
                if (nlChilds.item(j).getNodeName().equals("CharCode")) {
                    result.put(nlChilds.item(j).getTextContent(), nlChilds);
                }
            }
        }
        HashMap<String, Double> rates = new HashMap();

        //стандартный перебор HashMap и получаем NodeList из 5 элементов
        for (Map.Entry<String, NodeList> entry : result.entrySet()) {
            NodeList temp = entry.getValue();
            double value = 0;
            int nominal = 0;
            for (int i = 0; i < temp.getLength(); i++) {
                if (temp.item(i).getNodeName().equals("Value")) {
                    value = Double.parseDouble(temp.item(i).getTextContent().replace(",", "."));
                } else if (temp.item(i).getNodeName().equals("Nominal")) {
                    nominal = Integer.parseInt(temp.item(i).getTextContent());
                }
            }
            double amount = value / nominal;
            rates.put(entry.getKey(), ((double) Math.round(amount * 10000) / 10000));
        }
        rates.put("RUB", 1.0);// rates - ХэшМэп, просто устанавливаем рубли
        
        double div = rates.get(base.getCode());//в массиве rates ищем базовую валюту base.getCode
        for (Map.Entry<String, Double> entry : rates.entrySet()) {
            entry.setValue(entry.getValue() / div);
            // здесь мы поделили рубли на базовую валюту
        }
        return rates;
    }

}
