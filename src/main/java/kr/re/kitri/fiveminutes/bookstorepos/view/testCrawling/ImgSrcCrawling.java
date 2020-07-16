package kr.re.kitri.fiveminutes.bookstorepos.view.testCrawling;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ImgSrcCrawling {

    public static void main(String[] args) {
        String barcode = "";
        String url = "http://www.kyobobook.co.kr/product/detailViewKor.laf?barcode=";

        barcode = "9788958077633";
        url = url + barcode;

        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("============================================================");
        String imgStr = doc.select("div.cover img").attr("src");
        System.out.println(imgStr);
        System.out.println("============================================================");
    }
}
