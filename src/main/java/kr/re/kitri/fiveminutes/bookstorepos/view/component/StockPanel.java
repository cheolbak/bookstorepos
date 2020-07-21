package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import kr.re.kitri.fiveminutes.bookstorepos.service.StockManagementService;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.BookInfo;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.StockBookInfo;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.List;

@Slf4j
public class StockPanel extends JPanel implements ChangeBookInfoListener {

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

		StockBookInfoViewPanel infoViewPanel = new StockBookInfoViewPanel(this);
		StockBookSearchPanel searchPanel = new StockBookSearchPanel(stockService);

		searchPanel.setBookInfoReceiver(isbn -> {
			StockBookInfo stockBookInfo = stockService.ifSelectElseThenSearchBook(isbn);
			stockListPanel.pushData(stockBookInfo);
		});

		stockListPanel.setTotalFieldChangeListener(this::totalFieldChange);
		stockListPanel.setBookInfoViewPanelReceiver(infoViewPanel);

		stockListPanel.setAddButtonListener(infoList -> {
			infoList.stream().filter(bookInfo -> bookInfo instanceof StockBookInfo)
					.forEach(bookInfo -> stockService.pushStock((StockBookInfo) bookInfo));
		});

		rightPanel.add(searchPanel, BorderLayout.NORTH);
		rightPanel.add(infoViewPanel, BorderLayout.CENTER);

		return rightPanel;
	}

	@Override
	public void changedBookInfo() {
		stockListPanel.changeTotalField(this::totalFieldChange);
	}

	private String totalFieldChange(List<? extends BookInfo> infoList) {
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.KOREA);
		int sum = infoList.stream().map(bookInfo -> (StockBookInfo) bookInfo)
				.mapToInt(value -> value.getPrice() * value.getInsertStock())
				.sum();
		return currencyFormat.format(sum);
	}

}
