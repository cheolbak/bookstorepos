package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import kr.re.kitri.fiveminutes.bookstorepos.view.model.SellChartSection;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.SellDataSet;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;

public class SellChartPanel extends JPanel {

    private SellDataSet sellDataSet;
    private final ChartPanel chartPanel;

    public SellChartPanel() {
        this(SellDataSet.builder()
                .data(Collections.emptyList())
                .section(SellChartSection.SIX_WEEKS)
                .build());
    }

    public SellChartPanel(SellDataSet sellDataSet) {
        this.sellDataSet = sellDataSet;
        chartPanel = new ChartPanel(createChart());
        chartPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        chartPanel.setMinimumDrawWidth(500);
        chartPanel.setMinimumDrawHeight(500);
        chartPanel.setVisible(true);
        add(chartPanel);
        setPreferredSize(new Dimension(800, Short.MAX_VALUE));
    }

    private JFreeChart createChart() {
        JFreeChart chart = ChartFactory.createBarChart(
                "최근 " + sellDataSet.getSection() + "간 판매량",
                "최근 " + sellDataSet.getSection(),
                "판매량",
                createDataset(),
                PlotOrientation.VERTICAL,
                false, true, true
        );
        chart.setPadding(new RectangleInsets(20, 20, 20, 20));
        CategoryPlot p = chart.getCategoryPlot();
        p.getDomainAxis().setLabelFont(new Font(Font.DIALOG, Font.BOLD, 15));
        p.getDomainAxis().setTickLabelFont(new Font(Font.DIALOG, Font.BOLD, 15));
        p.getRangeAxis().setLabelFont(new Font(Font.DIALOG, Font.BOLD, 15));
        p.getRangeAxis().setTickLabelFont(new Font(Font.DIALOG, Font.BOLD, 15));
        chart.getTitle().setFont(new Font(Font.DIALOG, Font.BOLD, 30));
        return chart;
    }

    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (SellDataSet.Entry entry : sellDataSet.getData()) {
            dataset.setValue(entry.getSellCountData(), "판매량", entry.getColumnName());
        }
        return dataset;
    }

    public void updateChart(SellDataSet sellDataSet) {
        this.sellDataSet = sellDataSet;
        chartPanel.setChart(createChart());
        chartPanel.updateUI();
        updateUI();
    }
}
