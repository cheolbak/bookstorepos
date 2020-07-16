package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import java.awt.Color;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Person_Sell extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	public Person_Sell() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		TitledBorder serchTb=new TitledBorder(new LineBorder(Color.black),"회원검색");
		panel.setBorder(serchTb);
		panel.setBounds(35, 51, 371, 64);
		add(panel);
		panel.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(17, 25, 116, 24);
		panel.add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(147, 25, 210, 24);
		panel.add(textField);
		textField.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(35, 137, 371, 55);
		add(panel_1);
		panel_1.setLayout(null);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("비회원 구매");
		chckbxNewCheckBox.setBounds(10, 15, 131, 30);
		panel_1.add(chckbxNewCheckBox);
		
		JPanel panel_2 = new JPanel();
		TitledBorder personinfo=new TitledBorder(new LineBorder(Color.black),"회원정보");
		panel_2.setBorder(personinfo);
		panel_2.setBounds(35, 214, 375, 328);
		add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("회원번호:");
		lblNewLabel.setBounds(20, 40, 62, 18);
		panel_2.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("이름:");
		lblNewLabel_1.setBounds(20, 80, 62, 18);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("전화번호:");
		lblNewLabel_2.setBounds(20, 120, 62, 18);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("현재 적립금:");
		lblNewLabel_3.setBounds(20, 160, 80, 18);
		panel_2.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("적립예정 적립금:");
		lblNewLabel_4.setBounds(20, 200, 120, 18);
		panel_2.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("사용할 적립금:");
		lblNewLabel_5.setBounds(20, 240, 103, 18);
		panel_2.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("회원번호값");
		lblNewLabel_6.setBounds(140, 40, 170, 18);
		panel_2.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("이름값");
		lblNewLabel_7.setBounds(140, 80, 170, 18);
		panel_2.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("전화번호값");
		lblNewLabel_8.setBounds(140, 120, 170, 18);
		panel_2.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("현재 적립금값");
		lblNewLabel_9.setBounds(140, 160, 170, 18);
		panel_2.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("적립예정값");
		lblNewLabel_10.setBounds(140, 200, 170, 18);
		panel_2.add(lblNewLabel_10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(140, 240, 170, 24);
		panel_2.add(textField_1);
		textField_1.setColumns(10);

	}
}
