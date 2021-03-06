package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import kr.re.kitri.fiveminutes.bookstorepos.domain.Book;
import kr.re.kitri.fiveminutes.bookstorepos.service.SellManagementService;
import kr.re.kitri.fiveminutes.bookstorepos.service.UserManagementService;
import kr.re.kitri.fiveminutes.bookstorepos.util.Util;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.DefaultBookInfo;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.SellBookInfo;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.SellUserInfo;
import kr.re.kitri.fiveminutes.bookstorepos.view.module.BarcodeImageReadDialogFrame;
import kr.re.kitri.fiveminutes.bookstorepos.view.module.CustomerSearchDialogFrame;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

@Slf4j
public class SellPanel extends JPanel implements BookInfoReceiver {

	private static final int COMBO_PHONE = 0;
	private static final int COMBO_NAME = 1;

	private JPanel bookInfoPanel;
	private JPanel userInfoPanel;
	private SellListPanel bookListPanel;

	SellManagementService sellService = new SellManagementService();

	@Setter
	List<DefaultBookInfo> bookInfoList;

	public JCheckBox userCheckbox;
	public JLabel userNum;
	public JLabel userName;
	public JLabel userPhone;
	public JLabel nowPoint;
	public JLabel memberGrade;
	int memberSearchnum=0;

	public JLabel title;
	public JLabel author;
	public JLabel publisher;
	public JLabel isbn;
	public JLabel originPrice;
	public JLabel sellPrice;
	public JLabel point;
	public JLabel nowStock;
	public JLabel bookImage;

	JTextField inputIsbnField;
	JTextField userSearchTextField;

//	@Setter
//	BookInfoReceiver bookInfoReceiver;

	public SellPanel() {
		setLayout(new BoxLayout(this ,BoxLayout.X_AXIS));

		JLabel title = new JLabel(" ");
		JLabel author = new JLabel();
		JLabel publisher = new JLabel();
		JLabel isbn = new JLabel();
		JLabel originPrice = new JLabel();
		JLabel sellPrice = new JLabel();
		JLabel point = new JLabel();
		JLabel nowStock = new JLabel();
		JLabel bookImage = new JLabel();

		userInfoPanel = createMemberPanel();
		bookInfoPanel = createBookInfoPanel();
		bookListPanel = new SellListPanel("판매", SellBookInfo.class,this);

		add(bookListPanel);
		add(bookInfoPanel);
		add(userInfoPanel);

	}

	JPanel createMemberPanel(){
		/*
		 memberPanel : 고개 파티션
		 searchMemberPanel : 회원 검색 패널
		 memberInfoPanel : 회원 정보 패널
		  * */

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
		userSearchTextField = new JTextField();
		JButton searchBtn = new JButton("확인");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//전화번호로 콤보박스 해서 확인


				if(memberSearchnum==COMBO_PHONE) {
					UserManagementService userManagementService =new UserManagementService();
					String userInfo = userSearchTextField.getText();
					System.out.println(userInfo);
					Object[][] data =userManagementService.searchUserPhone(userInfo);

					new CustomerSearchDialogFrame(data,SellPanel.this);
				}
				//이름으로 콤보 박스 해서 확인
				else if(memberSearchnum==COMBO_NAME){
					UserManagementService userManagementService =new UserManagementService();
					String userInfo = userSearchTextField.getText();
					Object[][] data=userManagementService.searchUserName(userInfo);
					new CustomerSearchDialogFrame(data,SellPanel.this);
				}

			}
		});
		userCheckbox = new JCheckBox("비회원 구매",true);
		userCheckbox.setEnabled(false);
		userCheckbox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				userNum.setText("0");
				userName.setText("비회원");
				userPhone.setText("0");
				nowPoint.setText("0 원");
				memberGrade.setText("비회원");

			}
		});

		memberSearchList.addItem("전화번호");
		memberSearchList.addItem("이름");
		//콤보 박스 선택
		memberSearchList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				memberSearchnum=memberSearchList.getSelectedIndex();
			}
		});


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

		userNum = new JLabel("0");
		userName = new JLabel("비회원");
		userPhone = new JLabel("0");
		nowPoint = new JLabel("0 원");
		memberGrade = new JLabel("비회원");


		userNumLabel.setSize(80,20);
		userNameLabel.setSize(80,20);
		memberGradeLabel.setSize(80,20);
		userPhoneLabel.setSize(80,20);
		nowPointLabel.setSize(80,20);

		userNum.setSize(170,20);
		userName.setSize(170,20);
		userPhone.setSize(170,20);
		nowPoint.setSize(170,20);
		memberGrade.setSize(170,20);

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
		memberInfoPanel.add(memberGradeLabel);

		memberInfoPanel.add(userNum);
		memberInfoPanel.add(userName);
		memberInfoPanel.add(userPhone);
		memberInfoPanel.add(nowPoint);
		memberInfoPanel.add(memberGrade);

		userNameLabel.setLocation(20,80);
		userNumLabel.setLocation(20,40);
		userPhoneLabel.setLocation(20,120);
		nowPointLabel.setLocation(20,160);
		memberGradeLabel.setLocation(20,200);

		userNum.setLocation(140,40);
		userName.setLocation(140,80);
		userPhone.setLocation(140,120);
		nowPoint.setLocation(140,160);
		memberGrade.setLocation(140,200);


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
		bookInfoPanel.setSize(530,900);

		//ISBN 검색 패널
		JPanel searchIsbnPanel = new JPanel();
		searchIsbnPanel.setLayout(null);
		searchIsbnPanel.setSize(500,70);
		searchIsbnPanel.setLocation(30,30);

		JLabel isbnLabel = new JLabel("ISBN: ");
		inputIsbnField = new JTextField();
		JButton isbnAddBtn = new JButton("추가");

		JButton imageCognitionBtn = new JButton("사진 인식");

		imageCognitionBtn.addActionListener(e -> new BarcodeImageReadDialogFrame(this));

		isbnLabel.setSize(45,20);
		isbnLabel.setLocation(15,30);

		inputIsbnField.setSize(200,25);
		inputIsbnField.setLocation(60,30);

		isbnAddBtn.setSize(105,30);
		isbnAddBtn.setLocation(265,25);

		imageCognitionBtn.setSize(105,30);
		imageCognitionBtn.setLocation(375,25);

		searchIsbnPanel.add(isbnLabel);
		searchIsbnPanel.add(inputIsbnField);
		searchIsbnPanel.add(isbnAddBtn);
		searchIsbnPanel.add(imageCognitionBtn);

		//책 정보 패널
		JPanel minBookInfoPanel = new JPanel();
		TitledBorder bookInfoBorder = new TitledBorder(new LineBorder(Color.BLACK),"책 정보");
		minBookInfoPanel.setBorder(bookInfoBorder);
		minBookInfoPanel.setLayout(null);
		minBookInfoPanel.setSize(500,420);
		minBookInfoPanel.setLocation(30,120);

		//책정보 텍스트 레이블
		JLabel titleLabel = new JLabel("제목:");
		JLabel authorLabel = new JLabel("저자:");
		JLabel publisherLabel = new JLabel("출판사:");
		JLabel isbnInfoLabel = new JLabel("ISBN:");
		JLabel originPriceLabel = new JLabel("정가:");
		JLabel sellPriceLabel = new JLabel("판매가:");
		JLabel pointLabel = new JLabel("적립금:");
		JLabel nowStockLabel = new JLabel("현재 재고량:");

		//책정보 value 레이블

		title = new JLabel("");
		author = new JLabel();
		publisher = new JLabel();
		isbn = new JLabel();
		originPrice = new JLabel();
		sellPrice = new JLabel();
		point = new JLabel();
		nowStock = new JLabel();
		bookImage = new JLabel();


		ActionListener listener = e -> {
			if (!inputIsbnField.getText().matches("^97[89][0-9]{10}$")) {
				return;
			}
			Book book = sellService.searchBook(inputIsbnField.getText());
			SellBookInfo sellBookInfo = SellBookInfo.fromBookDomain(book);

			if(sellBookInfo.getIsbn().equals("ERROR")||sellBookInfo.getStock()==0){
				JOptionPane.showMessageDialog(this, "해당 책이 재고에 존재하지 않습니다.", "오류", JOptionPane.WARNING_MESSAGE);
			}

			else {
				updateBookInfoList(sellBookInfo);

			}
		};

		inputIsbnField.addActionListener(listener);
		isbnAddBtn.addActionListener(listener);


		// 책정보패널 레이블 위치 설정
		titleLabel.setBounds(20,40,60,18);
		authorLabel.setBounds(20, 80, 62, 18);
		publisherLabel.setBounds(20, 120, 62, 18);
		isbnInfoLabel.setBounds(20, 160, 62, 18);
		originPriceLabel.setBounds(20, 200, 62, 18);
		sellPriceLabel.setBounds(20, 240, 62, 18);
		pointLabel.setBounds(20, 280, 62, 18);
		nowStockLabel.setBounds(20, 320, 94, 18);

		//책정보 밸류 위치 설정
		title.setBounds(90,40,160,20);
		author.setBounds(90,80,160,20);
		publisher.setBounds(90,120,160,20);
		isbn.setBounds(90,160,160,20);
		originPrice.setBounds(90,200,160,20);
		sellPrice.setBounds(90,240,160,20);
		point.setBounds(90,280,160,20);
		nowStock.setBounds(90,320,160,20);
		bookImage.setBounds(270,40,200,300);
		//bookImagePanel.setBounds(250,40,200,300);


		// 책정보 패널에 레이블 추가
		minBookInfoPanel.add(titleLabel);
		minBookInfoPanel.add(authorLabel);
		minBookInfoPanel.add(publisherLabel);
		minBookInfoPanel.add(isbnInfoLabel);
		minBookInfoPanel.add(originPriceLabel);
		minBookInfoPanel.add(sellPriceLabel);
		minBookInfoPanel.add(pointLabel);
		minBookInfoPanel.add(nowStockLabel);

		minBookInfoPanel.add(title);
		minBookInfoPanel.add(author);
		minBookInfoPanel.add(publisher);
		minBookInfoPanel.add(isbn);
		minBookInfoPanel.add(originPrice);
		minBookInfoPanel.add(sellPrice);
		minBookInfoPanel.add(point);
		minBookInfoPanel.add(nowStock);
		minBookInfoPanel.add(bookImage);

		bookInfoPanel.add(searchIsbnPanel);
		bookInfoPanel.add(minBookInfoPanel);

		return bookInfoPanel;
	}

	public void updateUserInfo(SellUserInfo info) {
		// TODO: 유저 정보 갱신
		NumberFormat numFormat = NumberFormat.getCurrencyInstance(Locale.KOREA);
		userCheckbox.setSelected(false);
		userCheckbox.setEnabled(true);
		userNum.setText(String.valueOf(info.getUserNum()));
		userName.setText(info.getUserName());
		userPhone.setText(info.getUserPhoneNum());
		nowPoint.setText(numFormat.format(info.getNowReserves())+"원");
		//nowPoint.setText(String.valueOf(info.getNowReserves()));
		memberGrade.setText(info.getUserGrade());
	}

	public void refreshBookInfoView(SellBookInfo info) {
		if (info == null) {
			return;
		}
		NumberFormat numFormat = NumberFormat.getCurrencyInstance(Locale.KOREA);

		title.setText(info.getTitle());
		author.setText(info.getAuthor());
		publisher.setText(info.getPublisher());
		isbn.setText(info.getIsbn());
		originPrice.setText(numFormat.format(info.getPrice()) + "원");
		sellPrice.setText(numFormat.format(info.getSellPrice()) + "원");
		point.setText(Integer.toString(info.getPoint()));
		nowStock.setText(Integer.toString(info.getStock()));
		bookImage.setIcon(new ImageIcon(Util.resizeImage(info.getBookCoverImage(), 200, 300)));
	}

	public void updateBookInfoList(SellBookInfo sellBookInfo) {
		bookListPanel.pushData(sellBookInfo);
		refreshBookInfoView(sellBookInfo);
		inputIsbnField.setText("");
	}

	@Override
	public void sendBookInfoToReceiver(String isbn) {
		SellBookInfo info = SellBookInfo.fromBookDomain(sellService.searchBook(isbn));

		if (info.getIsbn().equals("ERROR") || info.getStock() == 0) {
			JOptionPane.showMessageDialog(this, "해당 책이 재고에 존재하지 않습니다.",
											"오류", JOptionPane.WARNING_MESSAGE);
			return;
		}
		updateBookInfoList(info);
	}
}


