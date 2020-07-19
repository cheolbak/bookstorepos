package kr.re.kitri.fiveminutes.bookstorepos;

import kr.re.kitri.fiveminutes.bookstorepos.view.MainFrame;
import kr.re.kitri.fiveminutes.bookstorepos.view.component.MemberManagePanel;
import kr.re.kitri.fiveminutes.bookstorepos.view.component.RecordPanel;
import kr.re.kitri.fiveminutes.bookstorepos.view.component.SellPanel;
import kr.re.kitri.fiveminutes.bookstorepos.view.component.StockPanel;

import javax.swing.*;

public class BookStorePOSApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
