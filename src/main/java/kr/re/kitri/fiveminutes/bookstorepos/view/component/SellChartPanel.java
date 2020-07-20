package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import kr.re.kitri.fiveminutes.bookstorepos.view.model.SellChartSection;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.SellDataSet;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class SellChartPanel extends JPanel {

    private final SellDataSet sellDataSet;

    public SellChartPanel(SellDataSet sellDataSet) {
        this.sellDataSet = sellDataSet;

        ChartPanel chartPanel = new ChartPanel(createChart());
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.WHITE);
        chartPanel.setMaximumDrawWidth(500);
        chartPanel.setMaximumDrawHeight(500);

        add(chartPanel);
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
        CategoryPlot p = chart.getCategoryPlot();
        p.getDomainAxis().setLabelFont(new Font("돋움", Font.BOLD, 13));
        p.getDomainAxis().setTickLabelFont(new Font("돋움", Font.BOLD, 8));
        p.getRangeAxis().setLabelFont(new Font("돋움", Font.BOLD, 8));
        p.getRangeAxis().setTickLabelFont(new Font("돋움", Font.BOLD, 8));
        chart.getTitle().setFont(new Font("돋움", Font.BOLD, 15));
        return chart;
    }

    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Integer> entry : sellDataSet.getDataEntrySet()) {
            dataset.setValue(entry.getValue(), "판매량", entry.getKey());
        }
        return dataset;
    }

}
