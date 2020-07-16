package kr.re.kitri.fiveminutes.bookstorepos.view.testCrawling;

import okhttp3.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class DataCrawling {
    public static void main(String[] args) {

        FormBody form = new FormBody.Builder()
                .add("mallGb", "KOR")
                .add("tabGB", "1")
                .add("subEjkGb", "KOR")
                .add("newYmw", "2020073")
                .add("linkClass", "00")
                .add("sortColumn", "near_date")
                .add("excelYn", "Y")
                .add("seeOverYn", "Y")
                .add("pageNumber", "1")
                .add("perPage", "20")
                .add("targetPage", "")
                .add("filter", "ALL")
                .add("loginYN", "N")
                .build();

        Request req = new Request.Builder()
                .post(form)
                .url("http://www.kyobobook.co.kr/newproduct/newProductList.laf")
                .build();

        OkHttpClient client = new OkHttpClient();
        Response res = null;
        try {
            res = client.newCall(req).execute();
            Document doc = Jsoup.parse(res.body().string());

            System.out.println("============================================================");

            Elements rowList = doc.select("table tr:gt(1)");
            for (Element row : rowList) {
                Elements cellList = row.select("td:gt(0)");
                System.out.println(cellList.get(0).text());
                System.out.println(cellList.get(1).text());
                System.out.println(cellList.get(2).text());
                System.out.println(cellList.get(3).text());
                System.out.println(cellList.get(4).text());
                System.out.println(cellList.get(5).text());
                System.out.println();
            }

            System.out.println("============================================================");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
