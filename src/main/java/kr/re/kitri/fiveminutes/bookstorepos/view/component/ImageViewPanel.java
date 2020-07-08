package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import kr.re.kitri.fiveminutes.bookstorepos.view.model.BarcodeImage;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public class ImageViewPanel extends JPanel {

    private final JLabel imageLabel;

    @Getter
    private String currentCode;

    private BarcodeImage currentImage;

    public ImageViewPanel() {
        imageLabel = new JLabel();
        setLayout(new GridBagLayout());
        add(imageLabel);
    }

    public void changeImage(BarcodeImage image) {
        currentImage = image;
        currentCode = image.getCode();
        refreshView();
    }

    public void rotateClockwiseCurrentImage() {
        currentImage.rotateClockwiseImage();
        refreshView();
    }

    private void refreshView() {
        imageLabel.setIcon(new ImageIcon(currentImage.getImage()));
        imageLabel.updateUI();
    }
}
