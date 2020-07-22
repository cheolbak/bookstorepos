package kr.re.kitri.fiveminutes.bookstorepos.view.component;

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

import static kr.re.kitri.fiveminutes.bookstorepos.view.model.SellChartSection.SIX_MONTHS;
import static kr.re.kitri.fiveminutes.bookstorepos.view.model.SellChartSection.SIX_WEEKS;

public class RecordPanel extends JPanel implements BookInfoReceiver, IdInfoReceiver
{
    JPanel chartSetPanel;
    JPanel sellChartPanel;
    JPanel sellChartPanel2;
    SellDataSet sellDataSet;
    JTextField inputBookname;
    JTextField inputMemberName;

    public RecordPanel(){
        setLayout(null);
        setSize(1600,900);

        sellChartPanel=createSellChartPanel(SIX_WEEKS);
        sellChartPanel2=createSellChartPanel(SIX_MONTHS);
        chartSetPanel=createChartSetPanel();
        sellChartPanel2.setVisible(false);

        add(chartSetPanel);
        add(sellChartPanel);
        add(sellChartPanel2);

        sellChartPanel.setLocation(10,30);
        sellChartPanel2.setLocation(10,30);
        chartSetPanel.setLocation(1220,30);

        setVisible(true);
    }

    JPanel createSellChartPanel(SellChartSection section){
        sellDataSet = new SellDataSet(section);
        JPanel tempChartPanel = new SellChartPanel(sellDataSet);
        tempChartPanel.setSize(1200, 700);

        return tempChartPanel;
    }

    JPanel createChartSetPanel(){
        chartSetPanel = new JPanel();
        TitledBorder tBorder= new TitledBorder(new LineBorder(Color.BLACK),"조건");

        JLabel period = new JLabel("기간");
        JLabel bookName = new JLabel("책");
        JLabel memberName = new JLabel("회원");

        inputBookname = new JTextField();
        inputMemberName = new JTextField();

        String[] periodArray = {"최근 6주", "최근 6달"};
        JComboBox periodComboBox = new JComboBox(periodArray);
        periodComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cb = ((JComboBox) e.getSource()).getSelectedItem().toString();
                if(cb.equals("최근 6달")){
                    sellChartPanel.setVisible(false);
                    sellChartPanel2.setVisible(true);
                } else{
                    sellChartPanel.setVisible(true);
                    sellChartPanel2.setVisible(false);
                }
            }
        });

        JButton completeBtn = new JButton("확인");
        completeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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

        chartSetPanel.setSize(350,750);
        chartSetPanel.setLayout(null);
        chartSetPanel.setBorder(tBorder);

        //Label 크기 설정
        period.setSize(30,50);
        bookName.setSize(30,50);
        memberName.setSize(30,50);

        //텍스트박스 크기 설정
        inputBookname.setSize(200,40);
        inputMemberName.setSize(200,40);

        //체크박스 크기설정
        periodComboBox.setSize(100,40);

        //버튼 크기설정
        completeBtn.setSize(60,25);
        bookSearchBtn.setSize(60,25);
        memberSearchBtn.setSize(60,25);

        chartSetPanel.add(period);
        chartSetPanel.add(bookName);
        chartSetPanel.add(memberName);
        chartSetPanel.add(inputMemberName);
        chartSetPanel.add(inputBookname);
        chartSetPanel.add(periodComboBox);
        chartSetPanel.add(completeBtn);
        chartSetPanel.add(bookSearchBtn);
        chartSetPanel.add(memberSearchBtn);


        period.setLocation(30,100);
        periodComboBox.setLocation(110,100);

        bookName.setLocation(30,250);
        inputBookname.setLocation(70,250);
        bookSearchBtn.setLocation(280,255);

        memberName.setLocation(30,450);
        inputMemberName.setLocation(70,450);
        memberSearchBtn.setLocation(280,455);

        completeBtn.setLocation(150,550);

        return chartSetPanel;
    }

    @Override
    public void sendBookInfoToReceiver(String isbn) {
        RecordPanel.this.inputBookname.setText(isbn);
    }

    @Override
    public void sendIdInfoToReceiver(String id) {
        RecordPanel.this.inputMemberName.setText(id);
    }
}
