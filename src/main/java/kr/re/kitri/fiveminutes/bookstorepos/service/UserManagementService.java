package kr.re.kitri.fiveminutes.bookstorepos.service;


import kr.re.kitri.fiveminutes.bookstorepos.dao.CustomerDAO;
import kr.re.kitri.fiveminutes.bookstorepos.domain.Customer;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.SellUserInfo;

import javax.swing.*;

public class UserManagementService {
    private final CustomerDAO customerDAO= new CustomerDAO();

    public SellUserInfo UserSearchName(String name){
        Customer customer= customerDAO.selectNameQuery(name);
        SellUserInfo sellUserInfo = null;
        String userGrade = null;

        int userNum=customer.getCustomerId();
        String userName=customer.getCustomerName();
        String userPhone=customer.getCustomerTel();
        int nowPoint=customer.getCustomerPoint();
        int memeberGrade=customer.getCustomerTotalPrice();
        if(memeberGrade>=0 || memeberGrade<100000) {
            userGrade="일반";

        }else if(memeberGrade>=100000 ||memeberGrade<200000) {
            userGrade="VIP";
        }else if (memeberGrade>=200000){
            userGrade="VVIP";
        }

        sellUserInfo.setUserNum(userNum);
        sellUserInfo.setUserName(userName);
        sellUserInfo.setUserPhoneNum(userPhone);
        sellUserInfo.setNowReserves(nowPoint);
        sellUserInfo.setUserGrade(userGrade);




        return sellUserInfo;
    }

    public SellUserInfo UserSearchPhone(String phone){
        Customer customer =customerDAO.selectTelQuery(phone);
        SellUserInfo sellUserInfo = null;
        String userGrade=null;
//        int userNum=customer.getCustomerId();
//        String userName=customer.getCustomerName();
//        String userPhone=customer.getCustomerTel();
//        int nowPoint=customer.getCustomerPoint();
        int memeberGrade=customer.getCustomerTotalPrice();

        if(memeberGrade>=0 || memeberGrade<100000) {
            userGrade="일반";
        }else if(memeberGrade>=100000 ||memeberGrade<200000) {
            userGrade="VIP";
        }else if (memeberGrade>=200000){
            userGrade="VVIP";
        }

        sellUserInfo.setUserNum(customer.getCustomerId());
        sellUserInfo.setUserName(customer.getCustomerName());
        sellUserInfo.setUserPhoneNum(customer.getCustomerTel());
        sellUserInfo.setNowReserves(customer.getCustomerPoint());
        sellUserInfo.setUserGrade(userGrade);
        return sellUserInfo;
    }
}
