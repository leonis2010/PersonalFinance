/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalfinance.gui;

import java.awt.GridBagLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import personalfinance.settings.Style;
import personalfinance.settings.Text;

/**
 *
 * @author Леонид
 */
public final class Chart {
    //для использования DefaultPieDataset необходимо установить две библиотеки: jcommon и jfreechart

    private DefaultPieDataset dataset;
    private final String title;
    private final String currencyTitle;

    public Chart(HashMap<String, Double> data, String title, String currencyTitle) {
        setData(data);
        this.title = Text.get(title);
        this.currencyTitle = currencyTitle;
    }

    private void setData(HashMap<String, Double> data) {
        dataset = new DefaultPieDataset();
        for (Map.Entry<String, Double> entry : data.entrySet()) {
            dataset.setValue(entry.getKey(), entry.getValue());

        }
    }

    public JPanel getPanel() {
        JFreeChart chart = ChartFactory.createPieChart3D(title, dataset, true, true, false);//false у нас нет url ссылок
        PiePlot plot = (PiePlot) chart.getPlot();
        //настроим вид всплывающих подсказок
        plot.setToolTipGenerator(new StandardPieToolTipGenerator("{0}:   {1} " + currencyTitle + "   ({2})"));
                                          //{0}- продукты,  {1} - величина, currencyTitle- ед. изм.я, {2} - проценты
        JPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(Style.DIMENSION_CHART);
        if (dataset.getItemCount() == 0) {
            System.out.println(dataset.getItemCount());
            //ЕСЛИ НЕТ ДАННЫХ, то будет выводиться панель с надписью
            panel.setLayout(new GridBagLayout());//GridBagLayout() - размещает строго по центру
            JLabel label = new JLabel(Text.get("CHART_NO_DATA"));
            label.setFont(Style.FONT_LABEL_HEADER);
            panel.add(label);
        }
        return panel;
    }
}
