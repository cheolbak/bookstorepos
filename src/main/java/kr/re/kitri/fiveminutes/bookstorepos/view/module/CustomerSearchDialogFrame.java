package kr.re.kitri.fiveminutes.bookstorepos.view.module;

import kr.re.kitri.fiveminutes.bookstorepos.view.component.SellPanel;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.SellUserInfo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class CustomerSearchDialogFrame extends JFrame {


    private final SellPanel sellPanel;
    private JPanel userTablePanel;
    private JTable table;
    private Object[][] data;


    public CustomerSearchDialogFrame(Object[][] data, SellPanel sellPanel) {
        this.sellPanel = sellPanel;
        this.data=data;
        setTitle("회원검색");
        userTablePanel=createUserPanel();
        add(userTablePanel);
        setSize(1300,900);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    JPanel createUserPanel() {
        final Object[] column = {" ","회원번호","이름","전화번호","적립금","등급"};

        //테이블 체크박스뺴고 수정 안되게 설정
        DefaultTableModel dtm = new DefaultTableModel(data, column){
            @Override
            public boolean isCellEditable(int row, int column) {
                return row >= 0 && column == 0;
            }
        };

        table = new JTable(dtm);
        table.setPreferredScrollableViewportSize(new Dimension(400,200));
        table.setFillsViewportHeight(true);
        table.setEnabled(true);
        table.setBackground(Color.white);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JCheckBox box = new JCheckBox();
        box.setHorizontalAlignment(JLabel.CENTER);
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        DefaultTableCellRenderer right = new DefaultTableCellRenderer();

        //체크박스 셀 랜더링
        DefaultTableCellRenderer boxRender = new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
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
                editor = (JCheckBox) super.getTableCellEditorComponent(table, value, isSelected, row, column);
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

        //테이블에 마우스로 선택된 로우줄 과 체크박스의 값 확인으로 회원정보 변경
        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row != -1 && table.getValueAt(row,0).equals(true)) {
                    sellPanel.updateUserInfo(
                            SellUserInfo.builder()
                                .userNum(Integer.parseInt(table.getValueAt(row,1).toString()))
                                .userName(table.getValueAt(row, 2).toString())
                                .userPhoneNum(table.getValueAt(row, 3).toString())
                                .nowReserves(Integer.parseInt(table.getValueAt(row, 4).toString()))
                                .userGrade(table.getValueAt(row, 5).toString())
                                .build());
                }
                dispose();

            }
        });
        confirmBtn.setSize(100,50);

        userTablePanel.add(scroll);
        userTablePanel.add(confirmBtn);

        confirmBtn.setLocation(1140,30);
        scroll.setLocation(40, 80);

        return userTablePanel;
    }


}
