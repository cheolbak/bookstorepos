package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import kr.re.kitri.fiveminutes.bookstorepos.service.SellChartService;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.SellChartSection;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.SellDataSet;
import kr.re.kitri.fiveminutes.bookstorepos.view.module.BookSearchDialogFrame;
import kr.re.kitri.fiveminutes.bookstorepos.view.module.CustomerManagementFrame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecordPanel extends JPanel implements BookInfoReceiver, IdInfoReceiver
{
    JPanel chartSetPanel;
    SellChartPanel sellChartPanel;
    JTextField inputBookName;
    JTextField inputMemberName;
    private final SellChartService chartService;

    public RecordPanel(){
        chartService = new SellChartService();
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        sellChartPanel = new SellChartPanel();
        chartSetPanel = createChartSetPanel();

        add(sellChartPanel);
        add(chartSetPanel);

        setVisible(true);
    }

    private JPanel createChartSetPanel(){
        chartSetPanel = new JPanel();
        TitledBorder tBorder= new TitledBorder(new LineBorder(Color.BLACK),"조건");

        JLabel period = new JLabel("기간");
        JLabel bookName = new JLabel("책");
        JLabel memberName = new JLabel("회원");

        inputBookName = new JTextField();
        inputMemberName = new JTextField();

        JComboBox<SellChartSection> periodComboBox = new JComboBox<>(SellChartSection.values());

        JButton completeBtn = new JButton("확인");
        completeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object itemObj = periodComboBox.getSelectedItem();
                if (!(itemObj instanceof SellChartSection)) {
                    return;
                }
                SellChartSection section = (SellChartSection) itemObj;
                SellDataSet sellDataSet = chartService.requestSellChartDataSet(section, inputBookName.getText(), 0);
                sellChartPanel.updateChart(sellDataSet);
            }
        });
        JButton bookSearchBtn = new JButton("검색");
        bookSearchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BookSearchDialogFrame(RecordPanel.this);
            }
        });

        JButton memberSearchBtn = new JButton("검색");
        memberSearchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CustomerManagementFrame(RecordPanel.this);
            }
        });

        JCheckBox checkBookName = new JCheckBox("책 검색");
        checkBookName.setSelected(true);
        checkBookName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean selected = checkBookName.isSelected();
                if(selected){
                    memberSearchBtn.setEnabled(false);
                    inputMemberName.setEnabled(false);
                    inputMemberName.setText("");
                }
                else{
                    memberSearchBtn.setEnabled(true);
                    inputMemberName.setEnabled(true);
                }
            }
        });

        JCheckBox checkMemberName = new JCheckBox("회원 검색");
        memberSearchBtn.setEnabled(false);
        inputMemberName.setEnabled(false);
        checkMemberName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean selected = checkMemberName.isSelected();
                if(selected){
                    bookSearchBtn.setEnabled(false);
                    inputBookName.setEnabled(false);
                    inputBookName.setText("");
                }
                else{
                    bookSearchBtn.setEnabled(true);
                    inputBookName.setEnabled(true);
                }
            }
        });

        chartSetPanel.setSize(350,750);
        chartSetPanel.setLayout(null);
        chartSetPanel.setBorder(tBorder);

        //Label 크기 설정
        period.setSize(30,50);
        bookName.setSize(30,50);
        memberName.setSize(30,50);

        //텍스트박스 크기 설정
        inputBookName.setSize(200,40);
        inputMemberName.setSize(200,40);

        //체크박스 크기설정
        periodComboBox.setSize(100,40);

        //버튼 크기설정
        completeBtn.setSize(60,25);
        bookSearchBtn.setSize(60,25);
        memberSearchBtn.setSize(60,25);

        checkBookName.setSize(100,30);
        checkMemberName.setSize(100,30);

        chartSetPanel.add(period);
        chartSetPanel.add(bookName);
        chartSetPanel.add(memberName);
        chartSetPanel.add(inputMemberName);
        chartSetPanel.add(inputBookName);
        chartSetPanel.add(periodComboBox);
        chartSetPanel.add(completeBtn);
        chartSetPanel.add(bookSearchBtn);
        chartSetPanel.add(memberSearchBtn);
        chartSetPanel.add(checkBookName);
        chartSetPanel.add(checkMemberName);

        period.setLocation(30,100);
        periodComboBox.setLocation(110,100);

        bookName.setLocation(30,250);
        inputBookName.setLocation(70,250);
        bookSearchBtn.setLocation(280,255);

        memberName.setLocation(30,450);
        inputMemberName.setLocation(70,450);
        memberSearchBtn.setLocation(280,455);

        completeBtn.setLocation(150,550);

        checkBookName.setLocation(30,220);
        checkMemberName.setLocation(30,420);

        return chartSetPanel;
    }

    @Override
    public void sendBookInfoToReceiver(String isbn) {
        this.inputBookName.setText(isbn);
    }

    @Override
    public void sendIdInfoToReceiver(String id) {
        this.inputMemberName.setText(id);
    }
}
