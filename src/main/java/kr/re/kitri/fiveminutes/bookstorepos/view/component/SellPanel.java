package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class SellPanel extends JPanel{
	
	private JPanel bookListPanel;
	private JPanel bookInfoPanel;
	private JPanel userInfoPanel;
	
	public SellPanel() {
		bookListPanel=new ListPanel();
		bookInfoPanel=new Bookinfo_Sell();
		userInfoPanel=new Person_Sell();
		setLayout(new GridLayout());
		add(bookListPanel);
		add(bookInfoPanel);
		add(userInfoPanel);
	}
}
