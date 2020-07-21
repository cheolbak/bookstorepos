package kr.re.kitri.fiveminutes.bookstorepos.service;

import kr.re.kitri.fiveminutes.bookstorepos.dao.CustomerDAO;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.SellUserInfo;

public class UserManagementService {
    private final CustomerDAO customerDAO= new CustomerDAO();

    public SellUserInfo UserSearchName(String name){
        return SellUserInfo.fromCustomerDomain(customerDAO.selectNameQuery(name));
    }

    public SellUserInfo UserSearchPhone(String phone){
        return SellUserInfo.fromCustomerDomain(customerDAO.selectTelQuery(phone));
    }
}
