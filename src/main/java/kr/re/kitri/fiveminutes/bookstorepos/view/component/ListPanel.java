package kr.re.kitri.fiveminutes.bookstorepos.view.component;
import javax.swing.*;

public class ListPanel extends JPanel {
	private JTextField totalprice_Text;
	
	public ListPanel() {
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
		
		
		DefaultListModel<String> m = new DefaultListModel<>();
		JList<String>list = new JList<>(m);
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(51, 121, 398, 450);
		add(scrollPane);
		
		
		
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	
	}

}
