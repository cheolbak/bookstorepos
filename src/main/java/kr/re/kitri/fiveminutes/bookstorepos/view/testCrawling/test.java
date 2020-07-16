package kr.re.kitri.fiveminutes.bookstorepos.view.testCrawling;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class test {
    public static void main(String[] args) {

        FormBody form = new FormBody.Builder()
                .add("mallGb", "KOR")
                .add("tabGB", "1")
                .add("subEjkGb", "KOR")
                .add("newYmw", "2020073")
                .add("linkClass", "33")
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
                .url("http://www.kyobobook.co.kr/newproduct/newProductList.laf?mallGb=KOR")
                .build();

        OkHttpClient client = new OkHttpClient();
        try {
            Response res = client.newCall(req).execute();
            System.out.println(res.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

