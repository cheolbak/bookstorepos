package kr.re.kitri.fiveminutes.bookstorepos;

import kr.re.kitri.fiveminutes.bookstorepos.view.component.MemberManagePanel;
import kr.re.kitri.fiveminutes.bookstorepos.view.component.RecordPanel;
import kr.re.kitri.fiveminutes.bookstorepos.view.component.SellPanel;
import kr.re.kitri.fiveminutes.bookstorepos.view.component.StockPanel;
import kr.re.kitri.fiveminutes.bookstorepos.view.module.AuthDialogFrame;
import kr.re.kitri.fiveminutes.bookstorepos.view.module.BarcodeImageReadDialogFrame;
import kr.re.kitri.fiveminutes.bookstorepos.view.module.BookSearchDialogFrame;
import kr.re.kitri.fiveminutes.bookstorepos.view.module.NewBookListDialogFrame;

import javax.swing.*;

/**
 * Hello world!
 *
 */
public class BookStorePOSApplication {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pos System");
        MemberManagePanel memberManagePanel = new MemberManagePanel();
        RecordPanel recordPanel = new RecordPanel();
        SellPanel sellPanel = new SellPanel();
        StockPanel stockPanel = new StockPanel();

        JTabbedPane tabPanel = new JTabbedPane();

        tabPanel.add("판매",sellPanel);
        tabPanel.add("입고",stockPanel);
        tabPanel.add("회원관리",memberManagePanel);
        tabPanel.add("판매 통계",recordPanel);

        frame.add(tabPanel);

        frame.setSize(1600,900);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
