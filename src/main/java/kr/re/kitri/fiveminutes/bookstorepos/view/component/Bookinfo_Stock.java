package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import kr.re.kitri.fiveminutes.bookstorepos.domain.Book;
import kr.re.kitri.fiveminutes.bookstorepos.service.StockAdd;
import kr.re.kitri.fiveminutes.bookstorepos.view.module.BarcodeImageReadDialogFrame;
import kr.re.kitri.fiveminutes.bookstorepos.view.module.BookSearchDialogFrame;
import kr.re.kitri.fiveminutes.bookstorepos.view.module.NewBookListDialogFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Bookinfo_Stock extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private StockPanel stockPanel;

	public Bookinfo_Stock(StockPanel stockPanel) {
		this.stockPanel = stockPanel;
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(42, 55, 724, 37);
		add(panel);
		panel.setLayout(null);
		
		JLabel isbn_Label = new JLabel("ISBN");
		isbn_Label.setBounds(14, 9, 32, 18);
		panel.add(isbn_Label);
		
		textField = new JTextField();
		textField.setBounds(60, 6, 200, 24);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton add_Button = new JButton("등록");
		add_Button.setBounds(274, 5, 61, 27);
		panel.add(add_Button);

		
		JButton picture_Button = new JButton("사진인식");
		picture_Button.setBounds(340, 5, 89, 27);
		panel.add(picture_Button);
		picture_Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BarcodeImageReadDialogFrame f = new BarcodeImageReadDialogFrame();
				f.setVisible(true);
			}
		});

		JButton bookserch_Button = new JButton("책 검색");
		bookserch_Button.setBounds(528, 5, 79, 27);
		panel.add(bookserch_Button);
		bookserch_Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BookSearchDialogFrame f = new BookSearchDialogFrame();
				f.setVisible(true);
			}
		});
		
		JButton newbook_Button = new JButton("신간목록");
		newbook_Button.setBounds(621, 5, 89, 27);
		panel.add(newbook_Button);
		newbook_Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				NewBookListDialogFrame f = new NewBookListDialogFrame();
				f.setVisible(true);
			}
		});
		
		JPanel panel_1 = new JPanel();
		TitledBorder tb=new TitledBorder(new LineBorder(Color.black),"책 정보");
		panel_1.setBounds(42, 110, 724, 419);
		panel_1.setBorder(tb);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel subject_Label = new JLabel("제목:");
		subject_Label.setBounds(35, 40, 62, 18);
		panel_1.add(subject_Label);
		
		JLabel author_Label = new JLabel("저자:");
		author_Label.setBounds(35, 80, 62, 18);
		panel_1.add(author_Label);
		
		JLabel public_Label = new JLabel("출판사:");
		public_Label.setBounds(35, 120, 62, 18);
		panel_1.add(public_Label);
		
		JLabel isbn_Label2 = new JLabel("ISBN:");
		isbn_Label2.setBounds(35, 160, 62, 18);
		panel_1.add(isbn_Label2);
		
		JLabel price_Label = new JLabel("정가:");
		price_Label.setBounds(35, 200, 62, 18);
		panel_1.add(price_Label);
		
		JLabel sellprice_Label = new JLabel("판매가:");
		sellprice_Label.setBounds(35, 240, 62, 18);
		panel_1.add(sellprice_Label);
		
		JLabel savemoney_Label = new JLabel("적립금:");
		savemoney_Label.setBounds(35, 280, 62, 18);
		panel_1.add(savemoney_Label);
		
		JLabel presentstock_Label = new JLabel("현재 재고량:");
		presentstock_Label.setBounds(35, 320, 86, 18);
		panel_1.add(presentstock_Label);
		
		JLabel addStock_Label = new JLabel("추가 재고:");
		addStock_Label.setBounds(35, 360, 62, 18);
		panel_1.add(addStock_Label);
				
		JLabel subjectV_Label = new JLabel("제목 값");
		subjectV_Label.setBounds(130, 40, 164, 18);
		panel_1.add(subjectV_Label);
		
		JLabel lblNewLabel_2_1 = new JLabel("저자 값");
		lblNewLabel_2_1.setBounds(130, 80, 164, 18);
		panel_1.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("출판사 값");
		lblNewLabel_2_2.setBounds(130, 120, 164, 18);
		panel_1.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("isbn값");
		lblNewLabel_2_3.setBounds(130, 160, 164, 18);
		panel_1.add(lblNewLabel_2_3);
		
		JLabel lblNewLabel_2_4 = new JLabel("정가 값");
		lblNewLabel_2_4.setBounds(130, 200, 164, 18);
		panel_1.add(lblNewLabel_2_4);
		
		JLabel lblNewLabel_2_5 = new JLabel("판매가 값");
		lblNewLabel_2_5.setBounds(130, 240, 164, 18);
		panel_1.add(lblNewLabel_2_5);
		
		JLabel lblNewLabel_2_6 = new JLabel("적립금 값");
		lblNewLabel_2_6.setBounds(130, 280, 164, 18);
		panel_1.add(lblNewLabel_2_6);
		
		JLabel lblNewLabel_2_7 = new JLabel("현재 재고량 값");
		lblNewLabel_2_7.setBounds(130, 320, 154, 18);
		panel_1.add(lblNewLabel_2_7);
				
		JLabel lblNewLabel_2_9 = new JLabel("추가 재고 값");
		lblNewLabel_2_9.setBounds(130, 360, 154, 18);
		panel_1.add(lblNewLabel_2_9);
		
		JLabel lblNewLabel_1_8 = new JLabel("그림값");
		lblNewLabel_1_8.setBounds(495, 40, 164, 178);
		panel_1.add(lblNewLabel_1_8);
		
		JLabel lblNewLabel_3 = new JLabel("% 할인");
		lblNewLabel_3.setBounds(440, 240, 62, 18);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("% 적립");
		lblNewLabel_4.setBounds(440, 280, 62, 18);
		panel_1.add(lblNewLabel_4);

		add_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String isbn = textField.getText();
				if(isbn.equals("")){
					return;
				}

				StockAdd stockAdd = new StockAdd(isbn);
				Book book = stockAdd.searchBook();

				returnToListPanel(book);
			}

			public void returnToListPanel(Book book){
				ListPanel p = stockPanel.getP1();
				DefaultListModel<String> m = p.getM();
				JList<String>list = p.getList();

				m.addElement(book.getBookTitle() + "  " + book.getBookAuthor() +
						"  " + book.getBookPublisher() + "  " + book.getBookMSRP() +
						"  " + book.getBookDiscountRate() + "  " + book.getBookPointRate() +
						"  " + book.getBookISBN());

				JTextField totalPrice = p.getTotalprice_Text();
				int total = 0;
				for(int i=0;i<m.size();i++){
					total += ( 100 - book.getBookDiscountRate()) * 0.01 * book.getBookMSRP();
				}
				totalPrice.setText(total+"");
				p.setTotalprice_Text(totalPrice);
			}
		});
	}
}
