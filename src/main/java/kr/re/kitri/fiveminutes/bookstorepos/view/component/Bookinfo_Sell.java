package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Bookinfo_Sell extends JPanel{
	private JTextField textField;

	
	public Bookinfo_Sell() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(28, 33, 496, 71);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(" ISBN:");
		lblNewLabel.setBounds(14, 29, 62, 18);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(63, 26, 200, 24);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("추가");
		btnNewButton.setBounds(266, 25, 105, 27);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("사진인식");
		btnNewButton_1.setBounds(377, 25, 105, 27);
		panel.add(btnNewButton_1);
		
		JPanel panel_1 = new JPanel();
		TitledBorder tb=new TitledBorder(new LineBorder(Color.black),"책 정보");
		panel_1.setBounds(28, 116, 496, 419);
		panel_1.setBorder(tb);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("제목:");
		lblNewLabel_1.setBounds(20, 40, 62, 18);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("저자:");
		lblNewLabel_1_1.setBounds(20, 80, 62, 18);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("출판사:");
		lblNewLabel_1_2.setBounds(20, 120, 62, 18);
		panel_1.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("ISBN:");
		lblNewLabel_1_3.setBounds(20, 160, 62, 18);
		panel_1.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("정가:");
		lblNewLabel_1_4.setBounds(20, 200, 62, 18);
		panel_1.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("판매가:");
		lblNewLabel_1_5.setBounds(20, 240, 62, 18);
		panel_1.add(lblNewLabel_1_5);
		
		JLabel lblNewLabel_1_5_1 = new JLabel("적립금:");
		lblNewLabel_1_5_1.setBounds(20, 280, 62, 18);
		panel_1.add(lblNewLabel_1_5_1);
		
		JLabel lblNewLabel_1_5_2 = new JLabel("현재 재고량:");
		lblNewLabel_1_5_2.setBounds(20, 320, 94, 18);
		panel_1.add(lblNewLabel_1_5_2);
		
		JLabel lblNewLabel_1_5_3 = new JLabel("제목값");
		lblNewLabel_1_5_3.setBounds(140, 40, 160, 18);
		panel_1.add(lblNewLabel_1_5_3);
		
		JLabel lblNewLabel_1_5_4 = new JLabel("저자 값");
		lblNewLabel_1_5_4.setBounds(140, 80, 160, 18);
		panel_1.add(lblNewLabel_1_5_4);
		
		JLabel lblNewLabel_1_5_5 = new JLabel("출판사 값");
		lblNewLabel_1_5_5.setBounds(140, 120, 160, 18);
		panel_1.add(lblNewLabel_1_5_5);
		
		JLabel lblNewLabel_1_5_6 = new JLabel("ISBN값");
		lblNewLabel_1_5_6.setBounds(140, 160, 160, 18);
		panel_1.add(lblNewLabel_1_5_6);
		
		JLabel lblNewLabel_1_5_7 = new JLabel("정가 값");
		lblNewLabel_1_5_7.setBounds(140, 200, 160, 18);
		panel_1.add(lblNewLabel_1_5_7);
		
		JLabel lblNewLabel_1_5_8 = new JLabel("판매 값");
		lblNewLabel_1_5_8.setBounds(140, 240, 160, 18);
		panel_1.add(lblNewLabel_1_5_8);
		
		JLabel lblNewLabel_1_5_9 = new JLabel("적립금");
		lblNewLabel_1_5_9.setBounds(140, 280, 160, 18);
		panel_1.add(lblNewLabel_1_5_9);
		
		JLabel lblNewLabel_1_5_10 = new JLabel("현재 재고량");
		lblNewLabel_1_5_10.setBounds(140, 320, 160, 18);
		panel_1.add(lblNewLabel_1_5_10);
		
		JLabel lblNewLabel_1_5_11 = new JLabel("그림값");
		lblNewLabel_1_5_11.setBounds(314, 40, 146, 178);
		panel_1.add(lblNewLabel_1_5_11);

	}
}
