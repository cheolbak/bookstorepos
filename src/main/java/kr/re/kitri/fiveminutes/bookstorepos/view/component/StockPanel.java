package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class StockPanel extends JPanel {
	private JPanel p1;
	private JPanel p2;
	
	public StockPanel(){
		p1=new ListPanel();
		p2=new Bookinfo_Stock();
		setLayout(new GridLayout());
		add(p1);
		add(p2);
	
	}
}
