package kr.re.kitri.fiveminutes.bookstorepos.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class POSEntry {

    private int id;
    private String password;
    private LocalDateTime modifyDate;

}
