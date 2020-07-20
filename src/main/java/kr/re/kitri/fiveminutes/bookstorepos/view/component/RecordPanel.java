package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import kr.re.kitri.fiveminutes.bookstorepos.view.model.SellDataSet;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

import static kr.re.kitri.fiveminutes.bookstorepos.view.model.SellChartSection.SIX_WEEKS;

public class RecordPanel extends JPanel {
    JPanel chartPanel;
    JPanel chartSetPanel;

    public RecordPanel(){
        setLayout(null);
        setSize(1600,900);

        SellDataSet sellDataSet = new SellDataSet(SIX_WEEKS);
        SellChartPanel sellChartPanel = new SellChartPanel(sellDataSet);
        sellChartPanel.setSize(1200, 700);
        chartSetPanel=createChartSetPanel();

        add(chartSetPanel);
        add(sellChartPanel);

        chartSetPanel.setLocation(1220,30);
    }

    JPanel createChartSetPanel(){
        JPanel chartSetPanel = new JPanel();
        TitledBorder tBorder= new TitledBorder(new LineBorder(Color.BLACK),"조건");

        JLabel period = new JLabel("기간");
        JLabel bookName = new JLabel("책");
        JLabel memberName = new JLabel("회원");

        JTextField inputBookname = new JTextField();
        JTextField inputMemberName = new JTextField();

        String[] periodArray = {"최근 6주", "최근 6달"};
        JComboBox periodComboBox = new JComboBox(periodArray);

        JButton completeBtn = new JButton("확인");
        JButton bookSearchBtn = new JButton("검색");
        JButton memberSearchBtn = new JButton("검색");


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
}
