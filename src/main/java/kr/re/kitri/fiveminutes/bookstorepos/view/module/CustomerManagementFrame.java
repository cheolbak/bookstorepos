package kr.re.kitri.fiveminutes.bookstorepos.view.module;

import kr.re.kitri.fiveminutes.bookstorepos.service.UserManagementService;
import kr.re.kitri.fiveminutes.bookstorepos.view.component.IdInfoReceiver;
import kr.re.kitri.fiveminutes.bookstorepos.view.component.PaginationPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class CustomerManagementFrame extends JFrame implements IdInfoReceiver {

    private final UserManagementService customerService = new UserManagementService();
    private final DefaultTableModel model;
    private final Object[] column;
    private final IdInfoReceiver parentReceiver;

    //회원 관리 페이지 패널 생성자
    public CustomerManagementFrame(IdInfoReceiver parentReceiver) throws HeadlessException {
        this.parentReceiver = parentReceiver;
        setTitle("회원 검색");
        column = new Object[]{" ","회원번호","이름","전화번호","적립금","등급"};
        model = new DefaultTableModel(column, 6);

        initPanel();

        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1270, 864);
        setLocationRelativeTo(null);
        setLocation(getX(), getY());
        setVisible(true);
    }

    private void initPanel(){
        JPanel initpanel = new JPanel();
        initpanel.setLayout(new BorderLayout());
        initpanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        initpanel.add(createMemberTablePanel());
        add(initpanel);
    }

    // 회원관리 페이지 테이블 생성 함수
    private JPanel createMemberTablePanel() {
        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);
        table.setEnabled(true);
        table.setRowHeight(33);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setBackground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(table);

        JPanel memberTablePanel = new JPanel(new BorderLayout());

        memberTablePanel.setPreferredSize(new Dimension(850, Short.MAX_VALUE));

        PaginationPanel pagePanel = createTablePaginationPanel(table);

        memberTablePanel.add(createTableControlButtonPanel(table, pagePanel), BorderLayout.NORTH);
        memberTablePanel.add(scroll, BorderLayout.CENTER);
        memberTablePanel.add(pagePanel, BorderLayout.SOUTH);

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
        JButton addBtn = new JButton("추가");

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int selectedRow : table.getSelectedRows()) {
                    String name = table.getModel().getValueAt(selectedRow, 1).toString();
                    if(!name.equals("")){
                        sendIdInfoToReceiver(name);
                    }
                }
                dispose();
            }
        });

        panel.add(addBtn);
        margin.add(panel, BorderLayout.CENTER);

        return margin;
    }

    @Override
    public void sendIdInfoToReceiver(String name) {
        parentReceiver.sendIdInfoToReceiver(name);
    }
}

