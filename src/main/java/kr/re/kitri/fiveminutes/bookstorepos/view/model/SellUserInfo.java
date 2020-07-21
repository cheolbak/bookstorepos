package kr.re.kitri.fiveminutes.bookstorepos.view.model;

import kr.re.kitri.fiveminutes.bookstorepos.domain.Customer;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class SellUserInfo {

    private int userNum;
    private String userName;
    private String userPhoneNum;
    private int nowReserves; //현재 적립금
    private int expectedReserves; // 예정 적립금
    private int usingReserves; // 사용할 적립금
    private String userGrade;

    public Object[] getRowData() {
        return new Object[] {
                Boolean.FALSE,
                Integer.toString(getUserNum()),
                getUserName(),
                getUserPhoneNum(),
                getNowReserves(),
                getUserGrade()
        };
    }

    public static SellUserInfo fromCustomerDomain(Customer customer) {
        String userGrade = getUserGrade(customer.getCustomerTotalPrice());

        return SellUserInfo.builder()
                .userNum(customer.getCustomerId())
                .userName(customer.getCustomerName())
                .userPhoneNum(customer.getCustomerTel())
                .nowReserves(customer.getCustomerPoint())
                .userGrade(userGrade)
                .build();
    }

    private static String getUserGrade(int totalPrice) {
        if (totalPrice >= 100000 && totalPrice < 200000) {
            return "VIP";
        } else if (totalPrice >= 200000) {
            return "VVIP";
        }
        return "일반";
    }
}
