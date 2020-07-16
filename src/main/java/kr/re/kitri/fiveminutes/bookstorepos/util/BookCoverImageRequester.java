package kr.re.kitri.fiveminutes.bookstorepos.util;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Slf4j
public class BookCoverImageRequester {

    private static final File TEMP_DIR =
            Paths.get(System.getProperty("java.io.tmpdir"), "BookStorePOSApp").toFile();

    public static BufferedImage requestBookCoverImage(String isbn) throws IOException {
        initTempDir();
        String fileName = isbn + ".jpg";
        File file = new File(TEMP_DIR, fileName);
        if (file.exists()) {
            return ImageIO.read(file);
        }

        String url = requestImageUrl(isbn);
        log.debug("Book {} GetImageUrl: {}", isbn, url);
        return ImageIO.read(saveImageFileInTempDir(url, fileName));
    }

    public static BufferedImage requestThumbnailBookCoverImage(String isbn) throws IOException {
        BufferedImage originalImage = requestBookCoverImage(isbn);

        int originalWidth = originalImage.getWidth();
        int originalHeight = originalImage.getHeight();

        int resizedHeight = 190;
        double ratio = resizedHeight / (double) originalHeight;
        int resizedWidth = (int) (originalWidth * ratio);

        Image image = originalImage.getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH);

        BufferedImage resizedImage = new BufferedImage(resizedWidth, resizedHeight, BufferedImage.TYPE_INT_RGB);
        Graphics g = resizedImage.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();

        return resizedImage;
    }

    private static void initTempDir() {
        if (!TEMP_DIR.isDirectory()) {
            log.debug("TEMP_DIR is not directory.");
            TEMP_DIR.delete();
        }

        if (!TEMP_DIR.exists()) {
            log.debug("TEMP_DIR is not exist.");
            TEMP_DIR.mkdirs();
        }
    }

    private static String requestImageUrl(String isbn) throws IOException {
        if (!isbn.matches("^[0-9]{13}$")) {
            return "";
        }
        String requestUrl = "http://www.kyobobook.co.kr/product/detailViewKor.laf?barcode=" + isbn;

        Document document = Jsoup.connect(requestUrl).get();
        return document.select("div.cover img").attr("src");
    }

    private static File saveImageFileInTempDir(String imageUrl, String fileName) throws IOException {
        Request req = new Request.Builder()
                                .get()
                                .url(imageUrl)
                                .build();

        InputStream in = new OkHttpClient().newCall(req)
                                .execute().body().byteStream();
        File file = new File(TEMP_DIR, fileName);
        Files.copy(in, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        in.close();
        return file;
    }
}
