package kr.re.kitri.fiveminutes.bookstorepos.view.module;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class UserSearchFrame extends JFrame {


    private JPanel userTablePanel;
    private JTable table;
    private JLabel userNum;
    private JLabel userName;
    private JLabel userPhone;
    private JLabel nowPoint;
    private JLabel memberGrade;
    private JCheckBox userCheckBox;
    int row;


    public UserSearchFrame(String userInfo,JCheckBox userCheckBox,JLabel userNum,JLabel userName,JLabel userPhone,JLabel nowPoint,JLabel memeberGrade) {
        setTitle("회원검색");
        this.userCheckBox=userCheckBox;
        this.userNum=userNum;
        this.userName=userName;
        this.userPhone=userPhone;
        this.nowPoint=nowPoint;
        this.memberGrade=memeberGrade;
        userTablePanel=createUserPanel();
        add(userTablePanel);
        setSize(1300,900);
        setVisible(true);
        setDefaultCloseOperation(UserSearchFrame.DISPOSE_ON_CLOSE);
    }

    JPanel createUserPanel() {
        final Object[] column = {" ","회원번호","이름","전화번호","적립금","등급"};
        Object data[][]  = {{(false), "1" , "LEE", "010-2232-2222","1000원", "VIP"},
                {(false), "2" , "SU", "010-2232-2222","1000원", "VIP"},
                {(false), "3" , "SON", "010-2232-2222","1000원", "VIP"},
                {(false), "4" , "GYUNG", "010-2232-2222","1000원", "VIP"},
        };

        //테이블 체크박스뺴고 수정 안되게 설정
        DefaultTableModel dtm = new DefaultTableModel(data,column){
            @Override
            public boolean isCellEditable(int row, int column) {
                if(row>=0 && column==0 ) {
                    return true;
                }else{
                    return false;
                }
            }
        };

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

        //테이블에 마우스클릭으로 로우 값 얻기
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                row=table.getSelectedRow();

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
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
                if(table.getValueAt(row,0).equals(true)) {
                    userCheckBox.setSelected(false);
                    userNum.setText((String)table.getValueAt(row,1));
                    userName.setText((String)table.getValueAt(row,2));
                    userPhone.setText((String)table.getValueAt(row,3));
                    nowPoint.setText((String)table.getValueAt(row,4));
                    memberGrade.setText((String)table.getValueAt(row,5));
                    dispose();
                }

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
