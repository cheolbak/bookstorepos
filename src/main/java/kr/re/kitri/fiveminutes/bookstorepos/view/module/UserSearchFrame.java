package kr.re.kitri.fiveminutes.bookstorepos.view.module;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class UserSearchFrame extends JFrame {

    private JTable table;
    private JPanel userTablePanel;

    public UserSearchFrame() {
        setTitle("회원검색");
//		initPanel();
        userTablePanel=createUserPanel();
        add(userTablePanel);
        userTablePanel.setLocation(10, 30);
        setSize(1300,900);
        setVisible(true);
        setDefaultCloseOperation(UserSearchFrame.DISPOSE_ON_CLOSE);
    }

//	private void initPanel() {
//		userTablePanel=createUserPanel();
//		add(userTablePanel);
//	}

    JPanel createUserPanel() {
        final Object[] column = {" ","회원번호","이름","전화번호","적립금","등급"};
        Object data[][]  = {{(false), "1" , "LEE", "010-2232-2222","1000원", "VIP"}};

        DefaultTableModel dtm = new DefaultTableModel(data,column);

        table = new JTable(dtm);
        table.setPreferredScrollableViewportSize(new Dimension(400,200));
        table.setFillsViewportHeight(true);
        table.setEnabled(true);
        table.setBackground(Color.white);

        JCheckBox box = new JCheckBox();
        box.setHorizontalAlignment(JLabel.CENTER);


        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        DefaultTableCellRenderer right = new DefaultTableCellRenderer();

        //체크박스 셀 랜더링
        DefaultTableCellRenderer boxRender = new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected, boolean hasFocus, int row, int column) {
                JCheckBox comp = null;
                if(column==0){
                    comp = new JCheckBox();
                    comp.setSelected(((Boolean)value).booleanValue());
                    comp.setHorizontalAlignment(SwingConstants.CENTER);
                }
                return comp;
            }
        };


        //체크 박스 에디터
        DefaultCellEditor boxEditor = new DefaultCellEditor(box) {
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                JCheckBox editor;
                editor = (JCheckBox)super.getTableCellEditorComponent(table, value, isSelected, row, column);
                return editor;
            }
        };

        center.setHorizontalAlignment(JLabel.CENTER);
        right.setHorizontalAlignment(JLabel.RIGHT);
        boxRender.setHorizontalAlignment(JLabel.CENTER);


        table.getColumn(" ").setCellRenderer(boxRender);
        table.getColumn(" ").setCellEditor(boxEditor);
        table.getColumn("회원번호").setCellRenderer(center);
        table.getColumn("이름").setCellRenderer(center);
        table.getColumn("전화번호").setCellRenderer(center);
        table.getColumn("적립금").setCellRenderer(right);
        table.getColumn("등급").setCellRenderer(center);


        JScrollPane scroll = new JScrollPane(table);


        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);


        scroll.setSize(1200,750);

        JPanel userTablePanel = new JPanel();

        userTablePanel.setLayout(null);
        userTablePanel.setSize(1200,750);

        JButton confirmBtn = new JButton("확인");
        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                dispose();

            }
        });
        confirmBtn.setSize(100,50);

        userTablePanel.add(scroll);
        userTablePanel.add(confirmBtn);

        confirmBtn.setLocation(1100,0);
        scroll.setLocation(0, 50);

        return userTablePanel;
    }
}
