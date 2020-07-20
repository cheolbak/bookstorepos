package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import kr.re.kitri.fiveminutes.bookstorepos.service.StockManagementService;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.StockBookInfo;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;

@Slf4j
public class StockPanel extends JPanel {

	private final ListPanel stockListPanel;

	private final StockManagementService stockService;

	public StockPanel(){
		setLayout(new BoxLayout(this ,BoxLayout.X_AXIS));
		stockListPanel = new ListPanel("입고", StockBookInfo.class);
		stockService = new StockManagementService();

		add(Box.createVerticalGlue());
		add(stockListPanel);
		add(createRightPanel());
	}

	private JPanel createRightPanel() {
		JPanel rightPanel = new JPanel(new BorderLayout());

		StockBookInfoViewPanel infoViewPanel = new StockBookInfoViewPanel();
		StockBookSearchPanel searchPanel = new StockBookSearchPanel(stockService);

		searchPanel.setBookInfoReceiver(bookInfo -> {
			if (bookInfo instanceof StockBookInfo) {
				stockListPanel.pushData(bookInfo);
				return;
			}
			StockBookInfo stockBookInfo = StockBookInfo.fromBookInfo(bookInfo);
			stockListPanel.pushData(stockBookInfo);
		});

		stockListPanel.setBookInfoViewPanelReceiver(infoViewPanel);
		stockListPanel.setAddButtonListener(infoList -> {
			infoList.stream().filter(bookInfo -> bookInfo instanceof StockBookInfo)
					.forEach(bookInfo -> stockService.pushStock((StockBookInfo) bookInfo));
		});

		rightPanel.add(searchPanel, BorderLayout.NORTH);
		rightPanel.add(infoViewPanel, BorderLayout.CENTER);

		return rightPanel;
	}
}
