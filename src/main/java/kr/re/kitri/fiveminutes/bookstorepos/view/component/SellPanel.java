package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class SellPanel extends JPanel{

	private JPanel p1;
	private JPanel p2;
	private JPanel p3;

	public SellPanel() {
		p1=new LegacyListPanel();
		p2=new Bookinfo_Sell();
		p3=new Person_Sell();
		setLayout(new GridLayout());
		add(p1);
		add(p2);
		add(p3);

	}
}
