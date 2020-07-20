package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import kr.re.kitri.fiveminutes.bookstorepos.domain.Stock;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.BookInfo;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.StockBookInfo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;

@Slf4j
@Data
public class StockPanel extends JPanel {
	private LegacyListPanel p1;
	private Bookinfo_Stock p2;
	private ListPanel stockListPanel;

	public StockPanel(){
		setLayout(new BoxLayout(this ,BoxLayout.X_AXIS));
//		p1=new ListPanel(this);
		stockListPanel = new ListPanel("입고", StockBookInfo.class);
		p2=new Bookinfo_Stock(this);
		add(stockListPanel);
		add(Box.createVerticalGlue());
		add(p2);
	}
}
