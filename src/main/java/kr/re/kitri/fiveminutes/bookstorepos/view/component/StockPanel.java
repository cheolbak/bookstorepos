package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.awt.GridLayout;

import javax.swing.JPanel;

@Slf4j
@Data
public class StockPanel extends JPanel {
	private ListPanel p1;
	private Bookinfo_Stock p2;

	public StockPanel(){
		p1=new ListPanel(this);
		p2=new Bookinfo_Stock(this);
		setLayout(new GridLayout());
		add(p1);
		add(p2);
	}
}
