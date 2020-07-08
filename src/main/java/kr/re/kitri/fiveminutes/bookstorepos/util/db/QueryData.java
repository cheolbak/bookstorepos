package kr.re.kitri.fiveminutes.bookstorepos.util.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryData {

    private String tag;
    private String query;
}
