package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import kr.re.kitri.fiveminutes.bookstorepos.view.model.StockBookInfo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;

@Slf4j
@Data
public class StockPanel extends JPanel {
	private ListPanel stockListPanel;

	public StockPanel(){
		setLayout(new BoxLayout(this ,BoxLayout.X_AXIS));
		stockListPanel = new ListPanel("입고", StockBookInfo.class);

		add(Box.createVerticalGlue());
		add(stockListPanel);
		add(createRightPanel());
	}

	private JPanel createRightPanel() {
		JPanel rightPanel = new JPanel(new BorderLayout());
		StockBookInfoViewPanel infoViewPanel = new StockBookInfoViewPanel();
		StockBookSearchPanel searchPanel = new StockBookSearchPanel();
		searchPanel.setBookInfoReceiver(bookInfo -> {
			if (bookInfo instanceof StockBookInfo) {
				stockListPanel.pushData(bookInfo);
				return;
			}
			StockBookInfo stockBookInfo = StockBookInfo.fromBookInfo(bookInfo);
			stockListPanel.pushData(stockBookInfo);
		});
		stockListPanel.setBookInfoViewPanelReceiver(infoViewPanel);
		rightPanel.add(searchPanel, BorderLayout.NORTH);
		rightPanel.add(infoViewPanel, BorderLayout.CENTER);
		return rightPanel;
	}
}
