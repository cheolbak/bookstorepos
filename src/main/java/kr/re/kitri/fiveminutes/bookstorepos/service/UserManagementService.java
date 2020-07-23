package kr.re.kitri.fiveminutes.bookstorepos.service;

import kr.re.kitri.fiveminutes.bookstorepos.dao.CustomerDAO;
import kr.re.kitri.fiveminutes.bookstorepos.domain.Customer;
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

        int point = savedPoint-usedPoint;
        int pointResult = customerDAO.updatePoint(num, point);
        int totalResult = customerDAO.updateTotal(num,totalCost);


        return pointResult == 1 && totalResult==1;
    }

    public int getCustomerPoint(int num){
        Customer customer = customerDAO.selectId(num);
        return customer.getCustomerPoint();
    }

    public boolean insertCustomerInfo(String name, String tel){
        Customer customer= new Customer();
        customer.setCustomerName(name);
        customer.setCustomerTel(tel);

        int result = customerDAO.insertCustomer(customer);
        if(result == 0){
            return true;
        }
        else return false;
    }

    public int getFindMaxID() {
        return customerDAO.selectFindMaxID();
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

    public void updateCustomerName(int id, String name){
        customerDAO.updateName(id,name);
    }

    public void updateCustomerTel(int id, String tel){
        customerDAO.updateTel(id,tel);
    }

    public void updateCustomer(int id,String name, String tel){
        customerDAO.updateUser(id,name,tel);
    }

}
