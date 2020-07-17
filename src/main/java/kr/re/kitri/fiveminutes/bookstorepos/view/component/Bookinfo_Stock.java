package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import java.awt.Color;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Bookinfo_Stock extends JPanel {
	private JTextField addText;
	private JTextField discountText;
	private JTextField savePointText;

	public Bookinfo_Stock() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(42, 55, 724, 37);
		add(panel);
		panel.setLayout(null);
		
		JLabel addIsbnLabel = new JLabel("ISBN");
		addIsbnLabel.setBounds(14, 9, 32, 18);
		panel.add(addIsbnLabel);
		
		addText = new JTextField();
		addText.setBounds(60, 6, 200, 24);
		panel.add(addText);
		addText.setColumns(10);
		
		JButton addBtn = new JButton("등록");
		addBtn.setBounds(274, 5, 61, 27);
		panel.add(addBtn);
		
		JButton pictureBtn = new JButton("사진인식");
		pictureBtn.setBounds(340, 5, 89, 27);
		panel.add(pictureBtn);
		
	
		
		JButton bookSerchBtn = new JButton("책 검색");
		bookSerchBtn.setBounds(528, 5, 79, 27);
		panel.add(bookSerchBtn);
		
		JButton newbookBtn = new JButton("신간목록");
		newbookBtn.setBounds(621, 5, 89, 27);
		panel.add(newbookBtn);
		
		JPanel panel_1 = new JPanel();
		TitledBorder tb=new TitledBorder(new LineBorder(Color.black),"책 정보");
		panel_1.setBounds(42, 110, 724, 419);
		panel_1.setBorder(tb);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel titleLabel = new JLabel("제목:");
		titleLabel.setBounds(35, 40, 62, 18);
		panel_1.add(titleLabel);
		
		JLabel authorLabel = new JLabel("저자:");
		authorLabel.setBounds(35, 80, 62, 18);
		panel_1.add(authorLabel);
		
		JLabel publicLabel = new JLabel("출판사:");
		publicLabel.setBounds(35, 120, 62, 18);
		panel_1.add(publicLabel);
		
		JLabel infoIsbnLabel = new JLabel("ISBN:");
		infoIsbnLabel.setBounds(35, 160, 62, 18);
		panel_1.add(infoIsbnLabel);
		
		JLabel priceLabel = new JLabel("정가:");
		priceLabel.setBounds(35, 200, 62, 18);
		panel_1.add(priceLabel);
		
		JLabel sellPriceLabel = new JLabel("판매가:");
		sellPriceLabel.setBounds(35, 240, 62, 18);
		panel_1.add(sellPriceLabel);
		
		JLabel saveMoneyLabel = new JLabel("적립금:");
		saveMoneyLabel.setBounds(35, 280, 62, 18);
		panel_1.add(saveMoneyLabel);
		
		JLabel presentstock_Label = new JLabel("현재 재고량:");
		presentstock_Label.setBounds(35, 320, 86, 18);
		panel_1.add(presentstock_Label);
		
		JLabel addStockLabel = new JLabel("추가 재고:");
		addStockLabel.setBounds(35, 360, 76, 18);
		panel_1.add(addStockLabel);
				
		JLabel titleValue = new JLabel("제목 값");
		titleValue.setBounds(130, 40, 164, 18);
		panel_1.add(titleValue);
		
		JLabel authorValue = new JLabel("저자 값");
		authorValue.setBounds(130, 80, 164, 18);
		panel_1.add(authorValue);
		
		JLabel publicValue = new JLabel("출판사 값");
		publicValue.setBounds(130, 120, 164, 18);
		panel_1.add(publicValue);
		
		JLabel isbnValue = new JLabel("isbn값");
		isbnValue.setBounds(130, 160, 164, 18);
		panel_1.add(isbnValue);
		
		JLabel priceValue = new JLabel("정가 값");
		priceValue.setBounds(130, 200, 164, 18);
		panel_1.add(priceValue);
		
		JLabel sellPriceValue = new JLabel("판매가 값");
		sellPriceValue.setBounds(130, 240, 164, 18);
		panel_1.add(sellPriceValue);
		
		JLabel saveMoneyValue = new JLabel("적립금 값");
		saveMoneyValue.setBounds(130, 280, 164, 18);
		panel_1.add(saveMoneyValue);
		
		JLabel presentStockValue = new JLabel("현재 재고량 값");
		presentStockValue.setBounds(130, 320, 154, 18);
		panel_1.add(presentStockValue);
				
		JLabel addStockValue = new JLabel("추가 재고 값");
		addStockValue.setBounds(130, 360, 154, 18);
		panel_1.add(addStockValue);
		
		JLabel pictureLabel = new JLabel("그림값");
		pictureLabel.setBounds(495, 40, 164, 178);
		panel_1.add(pictureLabel);
		
		JLabel discountLabel = new JLabel("% 할인");
		discountLabel.setBounds(440, 240, 62, 18);
		panel_1.add(discountLabel);
		
		JLabel savePointLabel = new JLabel("% 적립");
		savePointLabel.setBounds(440, 280, 62, 18);
		panel_1.add(savePointLabel);
		
		
		
		discountText = new JTextField();
		discountText.setBounds(310, 240, 116, 24);
		panel_1.add(discountText);
		discountText.setColumns(10);
		
		savePointText = new JTextField();
		savePointText.setColumns(10);
		savePointText.setBounds(310, 280, 116, 24);
		panel_1.add(savePointText);
		
	}
}
