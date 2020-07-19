package kr.re.kitri.fiveminutes.bookstorepos.view.component;
import kr.re.kitri.fiveminutes.bookstorepos.domain.Book;
import kr.re.kitri.fiveminutes.bookstorepos.service.StockManagementService;
import lombok.Data;
import lombok.ToString;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Data
public class ListPanel extends JPanel {
	private JTextField totalprice_Text;
	private DefaultListModel<String> m;
	private JList<String>list;
	@ToString.Exclude
	private StockPanel stockPanel;

	// 판매 탭에서 처리
	public ListPanel(){
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(51, 50, 405, 53);
		add(panel);
		panel.setLayout(null);

		JButton selectRemove = new JButton("선택삭제");
		selectRemove.setBounds(286, 12, 105, 27);
		panel.add(selectRemove);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(51, 583, 405, 61);
		add(panel_1);

		JLabel totalprice_Label = new JLabel("총계 :");
		panel_1.add(totalprice_Label);

		totalprice_Text = new JTextField();
		panel_1.add(totalprice_Text);
		totalprice_Text.setColumns(10);

		JButton totalprice_Button = new JButton("결제");
		panel_1.add(totalprice_Button);
		totalprice_Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(totalprice_Text.getText().equals("")){
					return;
				}
				int result = JOptionPane.showConfirmDialog(null,totalprice_Text.getText()+"원을 결제하시겠습니까?","결제 창",JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION){

				}
			}
		});

		m = new DefaultListModel<>();
		list = new JList<>(m);

		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(51, 121, 398, 450);
		add(scrollPane);

		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		selectRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selected = list.getSelectedIndex();
				if(selected == -1){
					return;
				}
				String[] splitStr = m.get(selected).split("  ");

				int minusPrice = (int) ((100 - Integer.parseInt(splitStr[4])) * 0.01 * Integer.parseInt(splitStr[3]));
				int totalPrice = Integer.parseInt(totalprice_Text.getText());

				totalprice_Text.setText((totalPrice-minusPrice)+"");
				m.remove(selected);
			}
		});

		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {

			}

			public void returnToBookinfo_Stock(Book book){


			}
		});
	}

	// 입고 탭
	public ListPanel(StockPanel stockPanel) {
		this.stockPanel = stockPanel;
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(51, 50, 405, 53);
		add(panel);
		panel.setLayout(null);
		
		JButton selectRemove = new JButton("선택삭제");
		selectRemove.setBounds(286, 12, 105, 27);
		panel.add(selectRemove);
				
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(51, 583, 405, 61);
		add(panel_1);
		
		JLabel totalprice_Label = new JLabel("총계 :");
		panel_1.add(totalprice_Label);
		
		totalprice_Text = new JTextField();
		panel_1.add(totalprice_Text);
		totalprice_Text.setColumns(10);
		
		JButton totalprice_Button = new JButton("결제");
		panel_1.add(totalprice_Button);
		totalprice_Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(totalprice_Text.getText().equals("")){
					return;
				}
				int result = JOptionPane.showConfirmDialog(null,totalprice_Text.getText()+"원을 결제하시겠습니까?","결제 창",JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION){

				}
			}
		});

		m = new DefaultListModel<>();
		list = new JList<>(m);
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(51, 121, 398, 450);
		add(scrollPane);

		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		selectRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selected = list.getSelectedIndex();
				if(selected == -1){
					return;
				}
				String[] splitStr = m.get(selected).split("  ");

				int minusPrice = (int) ((100 - Integer.parseInt(splitStr[4])) * 0.01 * Integer.parseInt(splitStr[3]));
				int totalPrice = Integer.parseInt(totalprice_Text.getText());

				totalprice_Text.setText((totalPrice-minusPrice)+"");
				m.remove(selected);
			}
		});

		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				Bookinfo_Stock p = stockPanel.getP2();

				int selected = list.getSelectedIndex();
				if(selected == -1){
					return;
				}
				String[] splitStr = m.get(selected).split("  ");
				String isbn = splitStr[6];
				Book book = new StockManagementService().searchBook(isbn);

				p.getSubjectV_Label().setText("제목 " + book.getBookTitle());
				p.getLblNewLabel_2_1().setText("저자 "+ book.getBookAuthor());
				p.getLblNewLabel_2_2().setText("출판사 " + book.getBookPublisher());
				p.getLblNewLabel_2_3().setText("ISBN" + book.getBookISBN());
				p.getLblNewLabel_2_4().setText(book.getBookMSRP()+"원");
				p.getLblNewLabel_2_5().setText("판매가 " + (100-book.getBookDiscountRate())*0.01*book.getBookMSRP()+"원");
				p.getLblNewLabel_2_6().setText("적립금 " + book.getBookPointRate()*0.01*book.getBookMSRP()+"원");
				p.getLblNewLabel_2_7().setText(book.getBookStock() + " 재고량");
				p.getLblNewLabel_2_9().setText("추가 재고량");
				p.getLblNewLabel_1_8().setText("그림 값");
				p.getLblNewLabel_3().setText(book.getBookDiscountRate() + "% 할인");
				p.getLblNewLabel_4().setText(book.getBookPointRate() + "% 적립");
			}
		});

	}
}
