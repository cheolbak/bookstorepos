package kr.re.kitri.fiveminutes.bookstorepos.service;

import kr.re.kitri.fiveminutes.bookstorepos.dao.CustomerDAO;
import kr.re.kitri.fiveminutes.bookstorepos.domain.Customer;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.BookInfo;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.SellUserInfo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserManagementService {

    private final CustomerDAO customerDAO = new CustomerDAO();

    public Object[][] searchUserName(String name){
        return customerDAO.selectNameQuery(name).stream()
                .map(SellUserInfo::fromCustomerDomain)
                .map(SellUserInfo::getRowData)
                .toArray(Object[][]::new);
    }

    public Object[][] searchUserPhone(String phone){
        log.debug("searchUserPhone: {}", phone);
        return customerDAO.selectTelQuery(phone).stream()
                .map(SellUserInfo::fromCustomerDomain)
                .map(SellUserInfo::getRowData)
                .toArray(Object[][]::new);
    }

    public Object[][] selectAllPaging(int page) {
        return customerDAO.selectAllPaging((page - 1) * 20, 20).stream()
                .map(SellUserInfo::fromCustomerDomain)
                .map(SellUserInfo::getRowData)
                .toArray(Object[][]::new);
    }

    public boolean updateCustomerInfo(int num, int totalCost, int usedPoint,int savedPoint){
        Customer customer= customerDAO.selectId(num);
        int point = customer.getCustomerPoint();
        int totalPrice = customer.getCustomerTotalPrice();

        point = point - usedPoint+savedPoint;
        totalPrice = totalPrice+totalCost;

        int pointResult = customerDAO.updatePoint(num, point);
        int totalResult = customerDAO.updateTotal(num,totalPrice);


        return pointResult == 1 && totalResult==1;
    }

    public void deleteCustomer(int id) {
        customerDAO.deleteCustomer(id);
    }

    public int getCustomerCount() {
        return customerDAO.selectCount();
    }

    public void insertCustomer(String name, String tel){
        customerDAO.insertAdd(name,tel);
    }

}
