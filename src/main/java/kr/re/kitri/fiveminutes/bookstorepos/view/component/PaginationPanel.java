package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import lombok.Data;

import javax.swing.*;
import java.awt.*;

public class PaginationPanel extends JPanel {

    private static final int FIRST_PAGE = 1;

    private int currentPage;
    private final int lastPage;
    private final PageChangeListener pageChangeListener;

    public PaginationPanel(int lastPage, PageChangeListener pageChangeListener) {
        this.currentPage = FIRST_PAGE;
        this.lastPage = lastPage;
        this.pageChangeListener = pageChangeListener;

        setLayout(new GridBagLayout());

        PageStatusLabel pageStatusLabel = new PageStatusLabel(lastPage);

        add(createFirstButton(pageStatusLabel));
        add(createPreviousButton(pageStatusLabel));
        add(pageStatusLabel);
        add(createNextButton(pageStatusLabel));
        add(createLastButton(pageStatusLabel));
    }


    private JButton createFirstButton(PageStatusLabel pageStatusLabel) {
        JButton button = new JButton("<<");
        button.addActionListener(e -> {
            currentPage = FIRST_PAGE;
            pageChangeListener.change(new PaginationEvent(PaginationPanel.this, currentPage));
            pageStatusLabel.setViewPageStatus(currentPage);
        });
        return button;
    }

    private JButton createPreviousButton(PageStatusLabel pageStatusLabel) {
        JButton button = new JButton("<");
        button.addActionListener(e -> {
            currentPage = currentPage <= FIRST_PAGE
                            ? FIRST_PAGE
                            : currentPage - 1;
            pageChangeListener.change(new PaginationEvent(PaginationPanel.this, currentPage));
            pageStatusLabel.setViewPageStatus(currentPage);
        });
        return button;
    }

    private JButton createNextButton(PageStatusLabel pageStatusLabel) {
        JButton button = new JButton(">");
        button.addActionListener(e -> {
            currentPage = currentPage >= lastPage
                            ? lastPage
                            : currentPage + 1;
            pageChangeListener.change(new PaginationEvent(PaginationPanel.this, currentPage));
            pageStatusLabel.setViewPageStatus(currentPage);
        });
        return button;
    }

    private JButton createLastButton(PageStatusLabel pageStatusLabel) {
        JButton button = new JButton(">>");
        button.addActionListener(e -> {
            currentPage = lastPage;
            pageChangeListener.change(new PaginationEvent(PaginationPanel.this, currentPage));
            pageStatusLabel.setViewPageStatus(currentPage);
        });
        return button;
    }

    private static class PageStatusLabel extends JLabel {

        private final int lastPage;

        public PageStatusLabel(int lastPage) {
            this.lastPage = lastPage;
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
            setViewPageStatus(1);
        }

        public void setViewPageStatus(int currentPage) {
            setText(String.format(" %d / %d ", currentPage, lastPage));
        }
    }

    @FunctionalInterface
    public interface PageChangeListener {
        void change(PaginationEvent e);
    }

    @Data
    public static class PaginationEvent {
        private final PaginationPanel paginationPanel;
        private final int currentPageNumber;
    }
}
