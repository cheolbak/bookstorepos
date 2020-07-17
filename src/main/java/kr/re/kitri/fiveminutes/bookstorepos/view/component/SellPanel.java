package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class SellPanel extends JPanel{

	private JPanel bookListPanel;
	private JPanel bookInfoPanel;
	private JPanel userInfoPanel;


	public SellPanel() {
		setLayout(null);
		setSize(1600,800);
		userInfoPanel = createMemberPanel();

		add(userInfoPanel);
		userInfoPanel.setLocation(1060,0);
	}

	JPanel createMemberPanel(){
		JPanel memberPanel = new JPanel();
		memberPanel.setLayout(null);
		memberPanel.setSize(500,900);

		JPanel searchMemberPanel = new JPanel();

		searchMemberPanel.setLayout(null);
		searchMemberPanel.setSize(440,65);
		searchMemberPanel.setLocation(35,50);

		TitledBorder searchMemberTitleBorder = new TitledBorder(new LineBorder(Color.black), "회원 검색");
		searchMemberPanel.setBorder(searchMemberTitleBorder);

		JComboBox memberSearchList = new JComboBox();
		JTextField userSearchTextField = new JTextField();
		JButton searchBtn = new JButton("확인");
		JCheckBox userCheckbox = new JCheckBox("비회원 구매",true);

		memberSearchList.setSize(120,25);
		userSearchTextField.setSize(200,25);
		searchBtn.setSize(70,25);
		userCheckbox.setSize(130,30);

		JPanel userCheckboxPanel = new JPanel();
		userCheckboxPanel.setSize(440,55);
		userCheckboxPanel.setLayout(null);

		JPanel memberInfoPanel = new JPanel();
		TitledBorder memberInfoBorder = new TitledBorder(new LineBorder(Color.black), "회원 정보");
		memberInfoPanel.setBorder(memberInfoBorder);
		memberInfoPanel.setSize(440,330);
		memberInfoPanel.setLayout(null);

		JLabel userNumLabel = new JLabel("회원 번호: ");
		JLabel userNameLabel = new JLabel("이름: ");
		JLabel memberGradeLabel = new JLabel("등급: ");
		JLabel userPhoneLabel = new JLabel("전화번호:");
		JLabel nowPointLabel = new JLabel("현재 적립금:");
		JLabel usingPointLabel = new JLabel("사용 적립금:");

		JLabel userNum = new JLabel("0:");
		JLabel userName = new JLabel("비회원:");
		JLabel userPhone = new JLabel("0");
		JLabel nowPoint = new JLabel("0");
		JLabel memberGrade = new JLabel("비회원");

		JTextField inputPointField = new JTextField();

		userNumLabel.setSize(60,20);
		userNameLabel.setSize(60,20);
		memberGradeLabel.setSize(60,20);
		userPhoneLabel.setSize(60,20);
		nowPointLabel.setSize(60,20);
		usingPointLabel.setSize(60,20);

		userNum.setSize(50,20);
		userName.setSize(50,20);
		userPhone.setSize(50,20);
		nowPoint.setSize(50,20);
		memberGrade.setSize(50,20);

		inputPointField.setSize(170,25);
		inputPointField.setEnabled(false);

		//회원 검색 패널
		searchMemberPanel.add(memberSearchList);
		searchMemberPanel.add(searchBtn);
		searchMemberPanel.add(userSearchTextField);

		memberSearchList.setLocation(15,25);
		searchBtn.setLocation(355,25);
		userSearchTextField.setLocation(150,25);

		//유저 체크 박스 패널
		userCheckboxPanel.add(userCheckbox);
		userCheckbox.setSize(130,30);
		userCheckbox.setLocation(10,15);

		//회원 정보 패널
		memberInfoPanel.add(userNumLabel);
		memberInfoPanel.add(userNameLabel);
		memberInfoPanel.add(userPhoneLabel);
		memberInfoPanel.add(nowPointLabel);
		memberInfoPanel.add(usingPointLabel);
		memberInfoPanel.add(memberGradeLabel);

		memberInfoPanel.add(userNum);
		memberInfoPanel.add(userName);
		memberInfoPanel.add(userPhone);
		memberInfoPanel.add(nowPoint);
		memberInfoPanel.add(inputPointField);
		memberInfoPanel.add(memberGrade);

		userNameLabel.setLocation(20,80);
		userNumLabel.setLocation(20,40);
		userPhoneLabel.setLocation(20,120);
		nowPointLabel.setLocation(20,160);
		memberGradeLabel.setLocation(20,200);
		usingPointLabel.setLocation(20,240);

		userNum.setLocation(140,40);
		userName.setLocation(140,80);
		userPhone.setLocation(140,120);
		nowPoint.setLocation(140,160);
		memberGrade.setLocation(140,200);
		inputPointField.setLocation(140,240);


		memberPanel.add(searchMemberPanel);
		memberPanel.add(userCheckboxPanel);
		memberPanel.add(memberInfoPanel);


		searchMemberPanel.setLocation(35,50);
		userCheckboxPanel.setLocation(35,135);
		memberInfoPanel.setLocation(35,215);

		return memberPanel;
	}

	JPanel createBookInfoPanel(){
		JPanel bookInfoPanel = new JPanel();
		bookInfoPanel.setLayout(null);
		bookInfoPanel.setSize(500,900);


		//ISBN 검색 패널
		JPanel searchIsbnPanel = new JPanel();
		searchIsbnPanel.setLayout(null);
		searchIsbnPanel.setSize(500,70);

		JLabel isbnLabel = new JLabel();
		JTextField inputIsbnField = new JTextField();
		JButton isbnAddBtn = new JButton("추가");

		isbnLabel.setSize(60,20);
		isbnLabel.setLocation(15,30);

		inputIsbnField.setSize(200,25);
		inputIsbnField.setLocation(60,30);

		isbnAddBtn.setSize(105,30);
		isbnAddBtn.setLocation(265,25);

		searchIsbnPanel.add(isbnLabel);
		searchIsbnPanel.add(inputIsbnField);
		searchIsbnPanel.add(isbnAddBtn);


		return bookInfoPanel;
	}

/*
    public static void main(String[] args) {
        JFrame fr = new JFrame();
        SellPanel p = new SellPanel();

        fr.setLayout(null);
        fr.add(p);
        p.setLocation(0,0);

        fr.setSize(1600,900);




        *//*JPanel test1 = new JPanel();
        JPanel test2 = new JPanel();
        JPanel test3 = new JPanel();

        test1.setSize(500,900);
        test2.setSize(500,900);
        test3.setSize(500,900);

        test1.setBackground(Color.cyan);
        test2.setBackground(Color.RED);
        test3.setBackground(Color.GREEN);

        fr.setLayout(null);
        fr.setSize(1600,900);

        fr.add(test1);
        fr.add(test2);
        fr.add(test3);

        test1.setLocation(20,0);
        test2.setLocation(540,0);
        test3.setLocation(1060,0);
*//*
        fr.setVisible(true);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }*/
}


