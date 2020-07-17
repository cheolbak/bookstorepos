package kr.re.kitri.fiveminutes.bookstorepos.view.component;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListPanel extends JPanel {
	private JTextField totalpriceText;
	
	public ListPanel() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(51, 50, 405, 53);
		add(panel);
		panel.setLayout(null);
		
		JButton selectRemoveBtn = new JButton("선택삭제");

		selectRemoveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		selectRemoveBtn.setBounds(286, 12, 105, 27);
		panel.add(selectRemoveBtn);
		
				
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(51, 583, 405, 61);
		add(panel_1);
		
		JLabel totalpriceLabel = new JLabel("총계 :");
		panel_1.add(totalpriceLabel);
		
		totalpriceText = new JTextField();
		panel_1.add(totalpriceText);
		totalpriceText.setColumns(10);
		
		JButton totalpriceBtn = new JButton("결제");

		totalpriceBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		panel_1.add(totalpriceBtn);
		
		
		DefaultListModel<String> m = new DefaultListModel<>();
		JList<String>booklist = new JList<>(m);
		
		JScrollPane scrollPane = new JScrollPane(booklist);
		scrollPane.setBounds(51, 121, 398, 450);
		add(scrollPane);
		
		
		
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	
	}

}
