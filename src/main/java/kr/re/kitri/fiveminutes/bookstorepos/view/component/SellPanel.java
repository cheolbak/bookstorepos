package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import kr.re.kitri.fiveminutes.bookstorepos.util.requester.BookInfoSearchRequester;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.BookInfo;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.DefaultBookInfo;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.SellBookInfo;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.StockBookInfo;
import lombok.Setter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class SellPanel extends JPanel{
	private JPanel bookListPanel = new JPanel();
	private JPanel bookInfoPanel;
	private JPanel userInfoPanel;
	private ListPanel bookListTestPanel;


	SellBookInfo sellBookInfo;
	ArrayList<SellBookInfo> sellBookInfoList = new ArrayList<>();
	BookInfo bookInfo;

	@Setter
	List<DefaultBookInfo> bookInfoList;

	public SellPanel() {
		setLayout(new BoxLayout(this ,BoxLayout.X_AXIS));

		userInfoPanel = createMemberPanel();
		bookInfoPanel = createBookInfoPanel();
		bookListTestPanel = new ListPanel("판매", StockBookInfo.class);

		add(bookListTestPanel);
		add(bookInfoPanel);
		add(userInfoPanel);
		/*setLayout(null);
		setSize(1600,900);
		userInfoPanel = createMemberPanel();
		bookInfoPanel = createBookInfoPanel();
		bookListTestPanel = new ListPanel("판매", StockBookInfo.class);
		//	bookListPanel = createBookListPanel();

		add(userInfoPanel);
		userInfoPanel.setLocation(1060,0);

		add(bookInfoPanel);
		bookInfoPanel.setLocation(500,0);

		bookListPanel.setSize(500,900);
		bookListPanel.add(bookListTestPanel);

		add(bookListPanel);
		bookListPanel.setLocation(0,0);
*//*
		add(bookListTestPanel);
		//bookListPaneltest.setLocation(0,0);
*/
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
		JTextField userSearchTextField = new JTextField();
		JButton searchBtn = new JButton("확인");
		JCheckBox userCheckbox = new JCheckBox("비회원 구매",true);

		memberSearchList.addItem("전화번호");
		memberSearchList.addItem("이름");

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

		JLabel userNum = new JLabel("0");
		JLabel userName = new JLabel("비회원");
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
		bookInfoPanel.setSize(530,900);

		//ISBN 검색 패널
		JPanel searchIsbnPanel = new JPanel();
		searchIsbnPanel.setLayout(null);
		searchIsbnPanel.setSize(500,70);
		searchIsbnPanel.setLocation(30,30);

		JLabel isbnLabel = new JLabel("ISBN: ");
		JTextField inputIsbnField = new JTextField();
		JButton isbnAddBtn = new JButton("추가");
		JButton imageCognitionBtn = new JButton("사진 인식");

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
		JLabel title = new JLabel();
		JLabel author = new JLabel();
		JLabel publisher = new JLabel();
		JLabel isbn = new JLabel();
		JLabel originPrice = new JLabel();
		JLabel sellPrice = new JLabel();
		JLabel point = new JLabel();
		JLabel nowStock = new JLabel();
		JLabel bookImage = new JLabel();
		//JPanel bookImagePanel = new JPanel();

		//bookImagePanel.setLayout(null);


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
		title.setBounds(70,40,160,20);
		author.setBounds(70,80,160,20);
		publisher.setBounds(70,120,160,20);
		isbn.setBounds(70,160,160,20);
		originPrice.setBounds(70,200,160,20);
		sellPrice.setBounds(70,240,160,20);
		point.setBounds(70,280,160,20);
		nowStock.setBounds(70,320,160,20);
		bookImage.setBounds(250,40,200,300);
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


		//버튼 리스너
		isbnAddBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String searchedIsbn = inputIsbnField.getText();
				bookInfo = BookInfoSearchRequester.requestBookSearchScopeISBN(searchedIsbn);
				//ImageIcon imageIcon = new ImageIcon(bookInfo.getBookCoverImage());
				Image img = bookInfo.getBookCoverImage();
				Image resizeImage = img.getScaledInstance(200,300,Image.SCALE_SMOOTH);
				ImageIcon imageIcon = new ImageIcon(resizeImage);

				title.setText(bookInfo.getTitle());
				author.setText(bookInfo.getAuthor());
				publisher.setText(bookInfo.getPublisher());
				isbn.setText(bookInfo.getIsbn());
				originPrice.setText(Integer.toString(bookInfo.getPrice())+ "원");
				sellPrice.setText(Integer.toString(bookInfo.getPrice())+ "원");
				bookImage.setIcon(imageIcon);
			}
		});

		bookInfoPanel.add(searchIsbnPanel);
		bookInfoPanel.add(minBookInfoPanel);

		return bookInfoPanel;
	}

	JPanel createBookListPanel(){
		JPanel bookListPanel = new JPanel();
		bookListPanel.setLayout(null);
		bookListPanel.setSize(500,900);

		JButton deleteBookBtn = new JButton("선택 삭제");
		deleteBookBtn.setSize(105,30);
		deleteBookBtn.setLocation(340,60);

		bookListPanel.add(deleteBookBtn);

		DefaultListModel<String> listModel = new DefaultListModel<>();
		JList<String> booklist = new JList<>(listModel);

		JScrollPane scrollPane = new JScrollPane(booklist);
		TitledBorder bookListBorder = new TitledBorder(new LineBorder(Color.BLACK,0),"결제 목록");
		scrollPane.setBorder(bookListBorder);

		scrollPane.setSize(400,450);
		scrollPane.setLocation(50,120);

		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		JPanel paymentPanel = new JPanel();
		paymentPanel.setLayout(null);
		paymentPanel.setSize(400,60);
		paymentPanel.setLocation(50,580);

		JLabel totalPriceLabel = new JLabel("총계: ");
		JLabel sellPriceValueLabel = new JLabel();
		JButton paymentBtn = new JButton("결제");

		totalPriceLabel.setSize(30,30);
		totalPriceLabel.setLocation(0,10);

		sellPriceValueLabel.setSize(100,30);
		sellPriceValueLabel.setLocation(40,10);

		paymentBtn.setSize(60,20);
		paymentBtn.setLocation(300,10);

		paymentPanel.add(totalPriceLabel);
		paymentPanel.add(sellPriceValueLabel);
		paymentPanel.add(paymentBtn);


		bookListPanel.add(deleteBookBtn);
		bookListPanel.add(scrollPane);
		bookListPanel.add(paymentPanel);

		return bookListPanel;
	}




}


