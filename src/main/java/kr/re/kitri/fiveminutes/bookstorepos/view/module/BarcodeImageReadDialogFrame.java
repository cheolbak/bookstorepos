package kr.re.kitri.fiveminutes.bookstorepos.view.module;

import kr.re.kitri.fiveminutes.bookstorepos.view.component.ImageViewPanel;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.BarcodeImage;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
public class BarcodeImageReadDialogFrame extends JFrame {

    private final Map<String, BarcodeImage> barcodeImageMap = new HashMap<>();

    private final ImageViewPanel imageViewPanel = new ImageViewPanel();
    private final JTextField codeFixField = new JTextField(24);
    private final JList<String> barcodeCorrectList = new JList<>();
    private final JList<String> barcodeWrongList = new JList<>();
    private final ProgressMonitor proMonitor;

    public BarcodeImageReadDialogFrame() throws HeadlessException {
        setTitle("책 자동 등록");

        proMonitor = new ProgressMonitor(this,
                "이미지 파일 로딩중...",
                "0 / 0 완료", 0, 100);

        initPanel();

        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(900, 680);
        setLocationRelativeTo(null);
        setLocation(getX(), getY());
    }

    private void initPanel() {
        add(createButtonPanel(), BorderLayout.NORTH);
        add(createListPanel(), BorderLayout.WEST);
        add(imageViewPanel, BorderLayout.CENTER);
    }

    private JScrollPane initBarcodeListScrollPane(JList<String> alreadyCreatedJList, String borderTitle) {
        JScrollPane scrollPane = new JScrollPane();

        Border blackLine = BorderFactory.createLineBorder(Color.BLACK);
        TitledBorder border = BorderFactory.createTitledBorder(blackLine, borderTitle);

        alreadyCreatedJList.setBorder(border);
        alreadyCreatedJList.addListSelectionListener(e -> {
            if (alreadyCreatedJList.isSelectionEmpty()) {
                return;
            }

            if (barcodeCorrectList == alreadyCreatedJList) {
                barcodeWrongList.clearSelection();
            }
            else {
                barcodeCorrectList.clearSelection();
            }

            BarcodeImage data = barcodeImageMap.get(alreadyCreatedJList.getSelectedValue());
            imageViewPanel.changeImage(data);
            codeFixField.setText(data.getCode());
        });
        scrollPane.setViewportView(alreadyCreatedJList);
        return scrollPane;
    }

    private JPanel createListPanel() {
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new GridLayout(2, 1));

        JScrollPane wrong = initBarcodeListScrollPane(barcodeWrongList,
                "잘못 인식된 바코드 사진");
        JScrollPane correct = initBarcodeListScrollPane(barcodeCorrectList,
                "인식된 바코드 사진");
        listPanel.add(wrong);
        listPanel.add(correct);

        return listPanel;
    }

    private JPanel createButtonPanel() {
        JPanel btnPanel = new JPanel();

        btnPanel.add(createFileChooseButton());

        btnPanel.add(codeFixField);
        btnPanel.add(createCodeFixConfirmButton());

        btnPanel.add(createRotateButton());
        btnPanel.add(createAcceptButton());

        return btnPanel;
    }

    private JButton createAcceptButton() {
        JButton acceptButton = new JButton("확인");
        // TODO: 책 등록 창으로 데이터 전송 후 프레임 닫기
        acceptButton.addActionListener(e ->
                JOptionPane.showMessageDialog(this,
                        "확인 버튼을 눌렀습니다."));
        return acceptButton;
    }

    private JButton createCodeFixConfirmButton() {
        JButton codeFixConfirmButton = new JButton("코드 수정");
        codeFixConfirmButton.addActionListener(e -> {
            String currentCode = imageViewPanel.getCurrentCode();
            String newCode = codeFixField.getText();
            BarcodeImage currentImage = barcodeImageMap.remove(currentCode);
            currentImage.setCode(newCode);
            barcodeImageMap.put(newCode, currentImage);
            refreshListData();
        });
        return codeFixConfirmButton;
    }

    private JButton createRotateButton() {
        JButton rotateButton = new JButton("선택 이미지 회전");
        rotateButton.addActionListener(e -> imageViewPanel.rotateClockwiseCurrentImage());
        return rotateButton;
    }

    private static JFileChooser createFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("파일 열기");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image", "jpg", "jpeg", "png"));
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        return fileChooser;
    }

    private JButton createFileChooseButton() {
        JButton fileChooseButton = new JButton("파일 열기");
        fileChooseButton.addActionListener(e -> {
            JFileChooser fileChooser = createFileChooser();

            if (fileChooser.showOpenDialog(this) == JFileChooser.FILES_ONLY) {
                java.util.List<File> files = Arrays.asList(fileChooser.getSelectedFiles());
                imageReadTaskSetAndStart(files);
            }
        });
        return fileChooseButton;
    }

    private void imageReadTaskSetAndStart(java.util.List<File> files) {
        ImageReadTask task = new ImageReadTask(files, this);

        int max = files.size();

        proMonitor.setProgress(0);
        proMonitor.setNote("0 / " + max + " 완료");

        task.addPropertyChangeListener(evt -> {
            if (evt.getPropertyName().equals("progress")){
                int count = (int) evt.getNewValue();
                int progress = (int) ((double) count / max * 100);
                proMonitor.setProgress(progress);
                proMonitor.setNote(count + " / " + max + " 완료");

                if (proMonitor.isCanceled()) {
                    task.cancel(true);
                }
            }
        });
        task.execute();
    }

    private static class ImageReadTask extends SwingWorker<java.util.List<BarcodeImage>, Integer> {

        private final List<File> files;
        private final BarcodeImageReadDialogFrame parentFrame;

        public ImageReadTask(List<File> files, BarcodeImageReadDialogFrame parentFrame) {
            this.files = files;
            this.parentFrame = parentFrame;
        }

        @Override
        protected List<BarcodeImage> doInBackground() {
            List<BarcodeImage> barcodeImages = new ArrayList<>();
            setProgress(0);
            int count = 0;
            for (File file : files) {
                setProgress(count++);
                selectImageFromFile(file).ifPresent(barcodeImages::add);
            }
            return barcodeImages;
        }

        @Override
        protected void done() {
            try {
                if (isCancelled()) {
                    log.info("Task Cancelled");
                    return;
                }
                parentFrame.resetCodeList(get());
                parentFrame.proMonitor.close();
                log.info("Task done!");
            }
            catch (InterruptedException | ExecutionException e) {
                log.warn("BarcodeReaderDialogFrame.Task Error!");
                if (log.isDebugEnabled()) {
                    e.printStackTrace();
                }
            }
        }
    } // End ImageReadTask private inner class

    private static Optional<BarcodeImage> selectImageFromFile(File file) {
        try {
            return Optional.of(new BarcodeImage(ImageIO.read(file)));
        }
        catch (IOException ignore) {
            log.warn("File can't read. Is it image?");
            log.warn("Path: {}", file.getAbsolutePath());
        }
        return Optional.empty();
    }

    private void resetCodeList(List<BarcodeImage> imageList) {
        for (BarcodeImage image : imageList) {
            barcodeImageMap.put(image.getCode(), image);
        }
        refreshListData();
    }

    private void refreshListData() {
        String[] correctListData = barcodeImageMap.values().stream()
                .filter(BarcodeImage::isValid)
                .map(BarcodeImage::getCode)
                .toArray(String[]::new);
        barcodeCorrectList.setListData(correctListData);

        String[] wrongListData = barcodeImageMap.values().stream()
                .filter(barcodeImage -> !barcodeImage.isValid())
                .map(BarcodeImage::getCode)
                .toArray(String[]::new);
        barcodeWrongList.setListData(wrongListData);
    }
}
