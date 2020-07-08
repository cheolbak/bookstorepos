package kr.re.kitri.fiveminutes.bookstorepos.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class POSEntry {

    private int id;
    private String password;
    private LocalDateTime modifyDate;

}
