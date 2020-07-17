package kr.re.kitri.fiveminutes.bookstorepos.view.model;

import lombok.Builder;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDate;

@Data
@Builder
public class SellUserInfo {

    private int userNum;
    private String userName;
    private int userPhoneNum;
    private int nowReserves; //현재 적립금
    private int expectedReserves; // 예정 적립금
    private int usingReserves; // 사용할 적립금

}
