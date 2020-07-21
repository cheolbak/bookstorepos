package kr.re.kitri.fiveminutes.bookstorepos.service;

import kr.re.kitri.fiveminutes.bookstorepos.dao.CustomerDAO;
import kr.re.kitri.fiveminutes.bookstorepos.domain.Customer;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.BookInfo;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.SellUserInfo;

import java.util.List;
import java.util.stream.Collectors;

public class UserManagementService {

    private final CustomerDAO customerDAO = new CustomerDAO();

    public Object[][] searchUserName(String name){
        return customerDAO.selectNameQuery(name).stream()
                .map(SellUserInfo::fromCustomerDomain)
                .map(SellUserInfo::getRowData)
                .toArray(Object[][]::new);
    }

    public Object[][] searchUserPhone(String phone){
        return customerDAO.selectTelQuery(phone).stream()
                .map(SellUserInfo::fromCustomerDomain)
                .map(SellUserInfo::getRowData)
                .toArray(Object[][]::new);
    }

    public boolean updateCustomerInfo(int num, int totalCost, int usedPoint){
        Customer customer= customerDAO.selectId(num);
        int point = customer.getCustomerPoint();
        int totalPrice = customer.getCustomerTotalPrice();

        point = point - usedPoint;
        totalPrice = totalPrice-totalCost;

        int pointResult = customerDAO.updatePoint(num, point);
        int totalResult = customerDAO.updateTotal(num,totalPrice);


        return pointResult == 1 && totalResult==1;
    }
}
