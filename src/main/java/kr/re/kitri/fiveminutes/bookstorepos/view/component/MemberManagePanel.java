package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class MemberManagePanel extends JPanel {

    //회원 관리 페이지 패널 생성자
    public MemberManagePanel() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(createMemberTablePanel());
        add(createAlterMemberPanel());
    }

    // 회원관리 페이지 테이블 생성 함수
    private JPanel createMemberTablePanel() {
        final Object[] column = {" ","회원번호","이름","전화번호","적립금","등급"};
        Object data[][]  = {{(false), "1" , "LEE", "010-2232-2222", "1000원", "VIP"}};

        DefaultTableModel dtm = new DefaultTableModel(data,column);

        JTable table = new JTable(dtm);
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
                    comp.setSelected((Boolean) value);
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

        JPanel memberTablePanel = new JPanel(new BorderLayout());

        memberTablePanel.setPreferredSize(new Dimension(850, Short.MAX_VALUE));

        memberTablePanel.add(createTableControlButtonPanel(), BorderLayout.NORTH);
        memberTablePanel.add(scroll, BorderLayout.CENTER);
        memberTablePanel.add(new PaginationPanel(1), BorderLayout.SOUTH);

        return memberTablePanel;
    }

    private JPanel createTableControlButtonPanel() {
        JPanel margin = new JPanel(new BorderLayout());
        margin.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton deleteBtn = new JButton("선택삭제");
        panel.add(deleteBtn);
        margin.add(panel, BorderLayout.CENTER);
        return margin;
    }

    //회원 추가 패널 생성 함수
    private JPanel createAlterMemberPanel() {
        JPanel alterMemberPanel = new JPanel();
        JTabbedPane addTab = new JTabbedPane();

        JPanel searchPanel = new JPanel();
        JLabel userName = new JLabel("이름");
        JLabel phoneNum = new JLabel("전화번호");
        JTextField inputName = new JTextField(20);
        JTextField inputPhone = new JTextField(20);

        alterMemberPanel.setSize(350,750);
        searchPanel.setSize(350,750);
        searchPanel.setLayout(null);


        //searchPanel.setBackground(Color.white);
        userName.setSize(350,50);
        phoneNum.setSize(350,50);

        inputName.setSize(290,50);
        inputPhone.setSize(290,50);

        searchPanel.add(userName);
        searchPanel.add(phoneNum);
        searchPanel.add(inputPhone);
        searchPanel.add(inputName);

        userName.setLocation(30,50);
        phoneNum.setLocation(30,300);
        inputName.setLocation(30, 110);
        inputPhone.setLocation(30, 360);

        addTab.add("추가",searchPanel);

        LineBorder border = new LineBorder(Color.BLACK,3);
        //alterMemberPanel.setBackground(Color.white);
        alterMemberPanel.setLayout(new BorderLayout());

        alterMemberPanel.add(addTab);

        return alterMemberPanel;
    }
}

