package kr.re.kitri.fiveminutes.bookstorepos.view.component;
import kr.re.kitri.fiveminutes.bookstorepos.view.module.NewBookListDialogFrame;
import lombok.Data;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

@Data
public class ListPanel extends JPanel {
	private JTextField totalprice_Text;
	private DefaultListModel<String> m;
	private JList<String>list;

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
				m.remove(selected);


			}
		});

	}
}
