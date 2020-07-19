package kr.re.kitri.fiveminutes.bookstorepos.apiparsing;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

class WebConnection{
    WebConnection() throws Exception{
        Request req = new Request.Builder()
                .get()
                .url("https://dapi.kakao.com/v3/search/book?query=미움받을")
                .addHeader("Authorization", "KakaoAK 2a22f05dfbf98250a6ae67b4eed0b461")
                .build();

        OkHttpClient client = new OkHttpClient();
        Response res = client.newCall(req).execute();

        System.out.println(res.body().string());
    }
}

public class APIParsing {
    public static void main(String[] args) throws Exception {
        new WebConnection();
    }
}
