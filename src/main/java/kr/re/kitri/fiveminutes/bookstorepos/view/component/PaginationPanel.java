package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import lombok.Data;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PaginationPanel extends JPanel {

    private static final int FIRST_PAGE = 1;

    private int currentPage;

    @Setter
    private int lastPage;

    @Setter
    private PageChangeListener pageChangeListener;

    private final PageStatusLabel pageStatusLabel;

    private final JButton firstButton;
    private final JButton previousButton;
    private final JButton nextButton;
    private final JButton lastButton;

    public PaginationPanel(int lastPage) {
        this.pageStatusLabel = new PageStatusLabel(FIRST_PAGE);
        this.currentPage = FIRST_PAGE;
        this.lastPage = lastPage;
        this.pageChangeListener = e -> {};

        firstButton = createFirstButton(pageStatusLabel);
        previousButton = createPreviousButton(pageStatusLabel);
        nextButton = createNextButton(pageStatusLabel);
        lastButton = createLastButton(pageStatusLabel);

        setLayout(new GridBagLayout());

        initButton();
    }

    private void initButton() {
        ActionListener buttonEnableAction = e -> enableButtonAction();

        firstButton.addActionListener(buttonEnableAction);
        previousButton.addActionListener(buttonEnableAction);
        nextButton.addActionListener(buttonEnableAction);
        lastButton.addActionListener(buttonEnableAction);

        add(firstButton, createStandardConstraints());
        add(previousButton, createStandardConstraints());
        add(pageStatusLabel, createStandardConstraints());
        add(nextButton, createStandardConstraints());
        add(lastButton, createStandardConstraints());

        enableButtonAction();
    }

    private void enableButtonAction() {
        if (currentPage == FIRST_PAGE && lastPage == FIRST_PAGE) {
            firstButton.setEnabled(false);
            previousButton.setEnabled(false);
            nextButton.setEnabled(false);
            lastButton.setEnabled(false);
        }
        else if (currentPage == FIRST_PAGE) {
            firstButton.setEnabled(false);
            previousButton.setEnabled(false);
            nextButton.setEnabled(true);
            lastButton.setEnabled(true);
        }
        else if (currentPage == lastPage) {
            firstButton.setEnabled(true);
            previousButton.setEnabled(true);
            nextButton.setEnabled(false);
            lastButton.setEnabled(false);
        }
        else {
            firstButton.setEnabled(true);
            previousButton.setEnabled(true);
            nextButton.setEnabled(true);
            lastButton.setEnabled(true);
        }
    }

    private GridBagConstraints createStandardConstraints() {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 20, 10);
        return c;
    }

    @Override
    public void updateUI() {
        if (pageStatusLabel != null) {
            pageStatusLabel.setLastPage(lastPage);
            pageStatusLabel.setViewPageStatus(FIRST_PAGE);
            enableButtonAction();
        }
        super.updateUI();
    }

    private JButton createFirstButton(PageStatusLabel pageStatusLabel) {
        JButton button = new JButton("<<");
        button.addActionListener(e -> {
            currentPage = FIRST_PAGE;
            pageChangeListener.change(new PaginationEvent(PaginationPanel.this, currentPage));
            pageStatusLabel.setViewPageStatus(currentPage);
            enableButtonAction();
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
            enableButtonAction();
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
            enableButtonAction();
        });
        return button;
    }

    private JButton createLastButton(PageStatusLabel pageStatusLabel) {
        JButton button = new JButton(">>");
        button.addActionListener(e -> {
            currentPage = lastPage;
            pageChangeListener.change(new PaginationEvent(PaginationPanel.this, currentPage));
            pageStatusLabel.setViewPageStatus(currentPage);
            enableButtonAction();
        });
        return button;
    }

    private static class PageStatusLabel extends JLabel {

        @Setter
        private int lastPage;

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
