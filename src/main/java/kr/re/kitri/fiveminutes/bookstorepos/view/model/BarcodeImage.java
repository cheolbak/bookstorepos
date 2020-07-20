package kr.re.kitri.fiveminutes.bookstorepos.view.model;

import kr.re.kitri.fiveminutes.bookstorepos.util.BarcodeReader;
import kr.re.kitri.fiveminutes.bookstorepos.util.Util;
import lombok.Getter;

import java.awt.*;
import java.awt.image.BufferedImage;

@Getter
public class BarcodeImage {

    private String code;

    private BufferedImage image;

    private boolean valid;

    private static final String ISBN_REGEX = "^97[89][0-9]{10}";

    public BarcodeImage(BufferedImage image) {
        this.code = BarcodeReader.read(Util.resizeImage(image, 700, 700));
        this.image = resizingSquareImage(image);
        this.valid = code.matches(ISBN_REGEX);
    }

    public void setCode(String code) {
        this.code = code;
        this.valid = code.matches(ISBN_REGEX);
    }

    // 이미지 회전 참조: https://blog.naver.com/yjhyjh5369/221177835663
    public void rotateClockwiseImage() {
        BufferedImage newImage = new BufferedImage(image.getHeight(), image.getWidth(), image.getType());

        Graphics2D g = (Graphics2D) newImage.getGraphics();

        g.rotate(Math.toRadians(90), newImage.getWidth() / 2., newImage.getHeight() / 2.);

        g.translate((newImage.getWidth() - image.getWidth()) / 2,
                (newImage.getHeight() - image.getHeight()) / 2);

        g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
        g.dispose();

        image = newImage;
    }

    private static BufferedImage resizingSquareImage(BufferedImage originalImage) {
        return Util.resizeImage(originalImage, 550, 550);
    }
}
