package kr.re.kitri.fiveminutes.bookstorepos.service;

import kr.re.kitri.fiveminutes.bookstorepos.dao.CustomerDAO;
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
        return customerDAO.selectAllPaging((page - 1) * 25, 25).stream()
                .map(SellUserInfo::fromCustomerDomain)
                .map(SellUserInfo::getRowData)
                .toArray(Object[][]::new);
    }
}
