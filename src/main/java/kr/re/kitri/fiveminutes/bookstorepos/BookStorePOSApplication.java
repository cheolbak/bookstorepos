package kr.re.kitri.fiveminutes.bookstorepos;

import kr.re.kitri.fiveminutes.bookstorepos.view.component.MemberManagePanel;
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

        JTabbedPane tabPanel = new JTabbedPane();
        tabPanel.add("회원관리",memberManagePanel);


        frame.add(tabPanel);

        frame.setSize(1600,900);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
