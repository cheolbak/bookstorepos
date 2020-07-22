package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import kr.re.kitri.fiveminutes.bookstorepos.service.UserManagementService;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class CustomerManagementPanel extends JPanel {

    private final UserManagementService customerService = new UserManagementService();
    private final DefaultTableModel model;
    private final Object[] column;

    private JTable table;
    private PaginationPanel pagePanel;
    //회원 관리 페이지 패널 생성자
    public CustomerManagementPanel() {
        column = new Object[]{" ","회원번호","이름","전화번호","적립금","등급"};
        model = new DefaultTableModel(column, 6){
            @Override
            public boolean isCellEditable(int row, int column) {
                return row >= 0 && (column == 2 || column==3);
            }
        };

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(createMemberTablePanel());
        add(createAlterMemberPanel(table,pagePanel));
    }

    // 회원관리 페이지 테이블 생성 함수
    private JPanel createMemberTablePanel() {
        //JTable table = new JTable(model);
        table=new JTable(model);

        table.setFillsViewportHeight(true);
        table.setEnabled(true);
        table.setRowHeight(33);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setBackground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(table);

        JPanel memberTablePanel = new JPanel(new BorderLayout());

        memberTablePanel.setPreferredSize(new Dimension(850, Short.MAX_VALUE));

        //PaginationPanel pagePanel = createTablePaginationPanel(table);
        pagePanel=createTablePaginationPanel(table);

        memberTablePanel.add(createTableControlButtonPanel(table, pagePanel), BorderLayout.NORTH);
        memberTablePanel.add(scroll, BorderLayout.CENTER);
        memberTablePanel.add(pagePanel, BorderLayout.SOUTH);

        table.addMouseListener(new MouseAdapter() {
            int row;
            @Override
            public void mouseClicked(MouseEvent e) {
                row=table.getSelectedRow();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                int id = Integer.parseInt(table.getModel().getValueAt(row, 1).toString());
                String name = table.getModel().getValueAt(row,2).toString();
                String tel = table.getModel().getValueAt(row,3).toString();
                customerService.updateCustomerName(id,name);
                customerService.updateCustomerTel(id,tel);
            }
        });

                updateTable(table, 1);

        return memberTablePanel;
    }

    private void updateTableUI(JTable table) {
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
                    comp.setSelected(isSelected);
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
        table.updateUI();
        table.getTableHeader().resizeAndRepaint();
    }

    private PaginationPanel createTablePaginationPanel(JTable table) {
        PaginationPanel panel = new PaginationPanel(customerService.getCustomerCount() / 20 + 1);
        panel.setPageChangeListener(e -> updateTable(table, e.getCurrentPageNumber()));
        panel.updateUI();
        return panel;
    }

    private void updateTable(JTable table, int page) {
        Object[][] data = customerService.selectAllPaging(page);
        model.setDataVector(data, column);
        updateTableUI(table);
    }

    private JPanel createTableControlButtonPanel(JTable table, PaginationPanel pagePanel) {
        JPanel margin = new JPanel(new BorderLayout());
        margin.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton deleteBtn = new JButton("삭제");
        JButton lookUpBtn = new JButton("조회");


        deleteBtn.addActionListener(e -> {
            for (int selectedRow : table.getSelectedRows()) {
                int id = Integer.parseInt(table.getModel().getValueAt(selectedRow, 1).toString());
                customerService.deleteCustomer(id);
            }
            pagePanel.setLastPage(customerService.getCustomerCount() / 20 + 1);
            pagePanel.updateUI();
            updateTable(table, 1);
        });

        lookUpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pagePanel.setLastPage(customerService.getCustomerCount() / 20 + 1);
                pagePanel.updateUI();
                updateTable(table, 1);
            }
        });

        panel.add(lookUpBtn);
        panel.add(deleteBtn);
        margin.add(panel, BorderLayout.CENTER);

        return margin;
    }

    //회원 추가 패널 생성 함수
    private JPanel createAlterMemberPanel(JTable table, PaginationPanel pagePanel) {
        JPanel alterMemberPanel = new JPanel();
        JTabbedPane addTab = new JTabbedPane();

        JPanel searchPanel = new JPanel();
        JLabel userName = new JLabel("이름");
        JLabel phoneNum = new JLabel("전화번호");
        JTextField inputName = new JTextField(20);
        JTextField inputPhone = new JTextField(20);
        JButton addBtn=new JButton("추가");

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerService.insertCustomer(inputName.getText(),inputPhone.getText());
                pagePanel.setLastPage(customerService.getCustomerCount() / 20 + 1);
                pagePanel.updateUI();
                updateTable(table, 1);
                inputName.setText("");
                inputPhone.setText("");

            }
        });

        alterMemberPanel.setSize(350,750);
        searchPanel.setSize(350,750);
        searchPanel.setLayout(null);


        //searchPanel.setBackground(Color.white);
        userName.setSize(350,50);
        phoneNum.setSize(350,50);

        inputName.setSize(290,50);
        inputPhone.setSize(290,50);

        addBtn.setSize(100,50);

        searchPanel.add(userName);
        searchPanel.add(phoneNum);
        searchPanel.add(inputPhone);
        searchPanel.add(inputName);
        searchPanel.add(addBtn);

        userName.setLocation(30,50);
        phoneNum.setLocation(30,300);
        inputName.setLocation(30, 110);
        inputPhone.setLocation(30, 360);
        addBtn.setLocation(220,700);

        addTab.add("추가",searchPanel);

        LineBorder border = new LineBorder(Color.BLACK,3);
        //alterMemberPanel.setBackground(Color.white);
        alterMemberPanel.setLayout(new BorderLayout());

        alterMemberPanel.add(addTab);

        return alterMemberPanel;
    }
}

