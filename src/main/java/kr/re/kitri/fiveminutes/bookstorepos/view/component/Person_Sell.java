package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import kr.re.kitri.fiveminutes.bookstorepos.view.model.SellUserInfo;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Person_Sell extends JPanel {
	private JTextField userSerchText;
	private JTextField useMoneyText;
	private JPanel panel_2;
	//private SellUserInfo;

	public Person_Sell() {
		setLayout(null);

		JPanel panel = new JPanel();
		TitledBorder serchTb = new TitledBorder(new LineBorder(Color.black), "회원검색");
		panel.setBorder(serchTb);
		panel.setBounds(35, 51, 438, 64);
		add(panel);
		panel.setLayout(null);

		JComboBox userSerchBox = new JComboBox();
		userSerchBox.setBounds(17, 25, 116, 24);
		panel.add(userSerchBox);

		userSerchText = new JTextField();
		userSerchText.setBounds(147, 25, 201, 24);
		panel.add(userSerchText);
		userSerchText.setColumns(10);

		JButton searchBtn = new JButton("확인");

		searchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		searchBtn.setBounds(355, 24, 69, 27);
		panel.add(searchBtn);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(35, 137, 438, 55);
		add(panel_1);
		panel_1.setLayout(null);

		JCheckBox unUserChckbox = new JCheckBox("비회원 구매");
		unUserChckbox.setBounds(10, 15, 131, 30);
		panel_1.add(unUserChckbox);

		panel_2 = new JPanel();
		TitledBorder personinfo = new TitledBorder(new LineBorder(Color.black), "회원정보");
		panel_2.setBorder(personinfo);
		panel_2.setBounds(35, 214, 438, 328);
		add(panel_2);
		panel_2.setLayout(null);

		JLabel userNum = new JLabel("회원번호:");
		userNum.setBounds(20, 40, 62, 18);
		panel_2.add(userNum);

		JLabel userName = new JLabel("이름:");
		userName.setBounds(20, 80, 62, 18);
		panel_2.add(userName);

		JLabel userphone = new JLabel("전화번호:");
		userphone.setBounds(20, 120, 62, 18);
		panel_2.add(userphone);

		JLabel presentMoney = new JLabel("현재 적립금:");
		presentMoney.setBounds(20, 160, 80, 18);
		panel_2.add(presentMoney);

		JLabel expectedMoney = new JLabel("적립예정 적립금:");
		expectedMoney.setBounds(20, 200, 120, 18);
		panel_2.add(expectedMoney);

		JLabel useMoney = new JLabel("사용할 적립금:");
		useMoney.setBounds(20, 240, 103, 18);
		panel_2.add(useMoney);

		JLabel userNumValue = new JLabel("회원번호값");
		userNumValue.setBounds(140, 40, 170, 18);
		panel_2.add(userNumValue);

		JLabel userNameValue = new JLabel("이름값");
		userNameValue.setBounds(140, 80, 170, 18);
		panel_2.add(userNameValue);

		JLabel userPhoneValue = new JLabel("전화번호값");
		userPhoneValue.setBounds(140, 120, 170, 18);
		panel_2.add(userPhoneValue);

		JLabel presentMoneyValue = new JLabel("현재 적립금값");
		presentMoneyValue.setBounds(140, 160, 170, 18);
		panel_2.add(presentMoneyValue);

		JLabel expectedMoneyValue = new JLabel("적립예정값");
		expectedMoneyValue.setBounds(140, 200, 170, 18);
		panel_2.add(expectedMoneyValue);

		useMoneyText = new JTextField();
		useMoneyText.setBounds(140, 240, 170, 24);
		panel_2.add(useMoneyText);
		useMoneyText.setColumns(10);
	}
}
