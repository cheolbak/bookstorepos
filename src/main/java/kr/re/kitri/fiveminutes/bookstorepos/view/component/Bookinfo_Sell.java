package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Bookinfo_Sell extends JPanel{
	private JTextField addText;

	
	public Bookinfo_Sell() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(28, 33, 496, 71);
		add(panel);
		panel.setLayout(null);
		
		JLabel addIsbnLabel = new JLabel(" ISBN:");
		addIsbnLabel.setBounds(14, 29, 62, 18);
		panel.add(addIsbnLabel);
		
		addText = new JTextField();
		addText.setBounds(63, 26, 200, 24);
		panel.add(addText);
		addText.setColumns(10);
		
		JButton addBtn = new JButton("추가");

		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		addBtn.setBounds(266, 25, 105, 27);
		panel.add(addBtn);
		
		JButton picktureBtn = new JButton("사진인식");

		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		picktureBtn.setBounds(377, 25, 105, 27);
		panel.add(picktureBtn);
		
		JPanel panel_1 = new JPanel();
		TitledBorder tb=new TitledBorder(new LineBorder(Color.black),"책 정보");
		panel_1.setBounds(28, 116, 496, 419);
		panel_1.setBorder(tb);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel titleLabel = new JLabel("제목:");
		titleLabel.setBounds(20, 40, 62, 18);
		panel_1.add(titleLabel);
		
		JLabel authorLabel = new JLabel("저자:");
		authorLabel.setBounds(20, 80, 62, 18);
		panel_1.add(authorLabel);
		
		JLabel publicLabel = new JLabel("출판사:");
		publicLabel.setBounds(20, 120, 62, 18);
		panel_1.add(publicLabel);
		
		JLabel infoIsbnLabel = new JLabel("ISBN:");
		infoIsbnLabel.setBounds(20, 160, 62, 18);
		panel_1.add(infoIsbnLabel);
		
		JLabel priceLabel = new JLabel("정가:");
		priceLabel.setBounds(20, 200, 62, 18);
		panel_1.add(priceLabel);
		
		JLabel sellPriceLabel = new JLabel("판매가:");
		sellPriceLabel.setBounds(20, 240, 62, 18);
		panel_1.add(sellPriceLabel);
		
		JLabel saveMoneyLabel = new JLabel("적립금:");
		saveMoneyLabel.setBounds(20, 280, 62, 18);
		panel_1.add(saveMoneyLabel);
		
		JLabel presentStockLabel = new JLabel("현재 재고량:");
		presentStockLabel.setBounds(20, 320, 94, 18);
		panel_1.add(presentStockLabel);
		
		JLabel titleValue = new JLabel("제목값");
		titleValue.setBounds(140, 40, 160, 18);
		panel_1.add(titleValue);
		
		JLabel authorValue = new JLabel("저자 값");
		authorValue.setBounds(140, 80, 160, 18);
		panel_1.add(authorValue);
		
		JLabel publicValue = new JLabel("출판사 값");
		publicValue.setBounds(140, 120, 160, 18);
		panel_1.add(publicValue);
		
		JLabel infoIsbnValue = new JLabel("ISBN값");
		infoIsbnValue.setBounds(140, 160, 160, 18);
		panel_1.add(infoIsbnValue);
		
		JLabel priceValue = new JLabel("정가 값");
		priceValue.setBounds(140, 200, 160, 18);
		panel_1.add(priceValue);
		
		JLabel sellPriceValue = new JLabel("판매 값");
		sellPriceValue.setBounds(140, 240, 160, 18);
		panel_1.add(sellPriceValue);
		
		JLabel saveMoneyValue = new JLabel("적립금");
		saveMoneyValue.setBounds(140, 280, 160, 18);
		panel_1.add(saveMoneyValue);
		
		JLabel presentStockValue = new JLabel("현재 재고량");
		presentStockValue.setBounds(140, 320, 160, 18);
		panel_1.add(presentStockValue);
		
		JLabel picktureValue = new JLabel("그림값");
		picktureValue.setBounds(314, 40, 146, 178);
		panel_1.add(picktureValue);

	}
}
