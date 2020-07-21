package kr.re.kitri.fiveminutes.bookstorepos.view;

import kr.re.kitri.fiveminutes.bookstorepos.view.component.CustomerManagementPanel;
import kr.re.kitri.fiveminutes.bookstorepos.view.component.RecordPanel;
import kr.re.kitri.fiveminutes.bookstorepos.view.component.SellPanel;
import kr.re.kitri.fiveminutes.bookstorepos.view.component.StockPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() throws HeadlessException {

        CustomerManagementPanel customerManagementPanel = new CustomerManagementPanel();
        RecordPanel recordPanel = new RecordPanel();
        SellPanel sellPanel = new SellPanel();
        StockPanel stockPanel = new StockPanel();

        JTabbedPane tabPanel = new JTabbedPane();

        tabPanel.add("판매", sellPanel);
        tabPanel.add("입고", stockPanel);
        tabPanel.add("회원관리", customerManagementPanel);
        tabPanel.add("판매 통계", recordPanel);

        add(tabPanel);

        setSize(1600,900);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
