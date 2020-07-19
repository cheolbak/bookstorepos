package kr.re.kitri.fiveminutes.bookstorepos.view.module;

import kr.re.kitri.fiveminutes.bookstorepos.util.requester.BookInfoSearchRequester;
import kr.re.kitri.fiveminutes.bookstorepos.view.component.DialogBookInfoListPanel;
import kr.re.kitri.fiveminutes.bookstorepos.view.component.DialogBookInfoReceiver;
import kr.re.kitri.fiveminutes.bookstorepos.view.component.MarginTitledBorderPanel;
import kr.re.kitri.fiveminutes.bookstorepos.view.component.PaginationPanel;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.BookSearchScope;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.DialogBookInfo;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.SearchMeta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BookSearchDialogFrame extends JFrame implements DialogBookInfoReceiver {

    public BookSearchDialogFrame() throws HeadlessException {
        setTitle("책 검색");

        initPanel();

        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(670, 770);
        setLocationRelativeTo(null);
        setLocation(getX(), getY());
        setVisible(true);
    }

    private void initPanel() {
        DialogBookInfoListPanel infoListPanel = new DialogBookInfoListPanel(this);
        PaginationPanel paginationPanel = new PaginationPanel(1);
        add(createSearchInputPanel(paginationPanel, infoListPanel), BorderLayout.NORTH);
        add(infoListPanel, BorderLayout.CENTER);
        add(paginationPanel, BorderLayout.SOUTH);
    }

    private JPanel createSearchInputPanel(PaginationPanel paginationPanel, DialogBookInfoListPanel infoListPanel) {
        MarginTitledBorderPanel panel = new MarginTitledBorderPanel("검색");

        JComboBox<BookSearchScope> scopeCombo = createSearchScopeComboBox();
        JTextField searchField = new JTextField();
        JButton submitButton = new JButton("검색");

        ActionListener searchSubmitAction = e -> {
            String query = searchField.getText();
            Object scopeObj = scopeCombo.getSelectedItem();
            if (!(scopeObj instanceof BookSearchScope)
                    || query.isBlank()) {
                return;
            }
            BookSearchScope scope = (BookSearchScope) scopeObj;
            updatePanels(scope, query, paginationPanel, infoListPanel);
        };

        searchField.addActionListener(searchSubmitAction);
        submitButton.addActionListener(searchSubmitAction);

        panel.addSubPanel(scopeCombo, createStandardConstraints(10, 5));
        panel.addSubPanel(searchField, createTextFieldConstraints());
        panel.addSubPanel(submitButton, createStandardConstraints(5, 10));

        return panel;
    }

    private GridBagConstraints createStandardConstraints(int left, int right) {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, left, 10, right);
        c.anchor = GridBagConstraints.WEST;
        return c;
    }

    private GridBagConstraints createTextFieldConstraints() {
        GridBagConstraints c = createStandardConstraints(10, 10);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.1;
        return c;
    }

    private JComboBox<BookSearchScope> createSearchScopeComboBox() {
        JComboBox<BookSearchScope> box = new JComboBox<>();
        for (BookSearchScope scope : BookSearchScope.values()) {
            box.addItem(scope);
        }
        return box;
    }

    private void updatePanels(BookSearchScope searchScope, String query, PaginationPanel paginationPanel, DialogBookInfoListPanel infoListPanel) {
        paginationPanel.setPageChangeListener(e -> {
            SearchMeta innerMeta = BookInfoSearchRequester.requestBookSearch(searchScope, query, e.getCurrentPageNumber());
            e.getPaginationPanel().setLastPage(innerMeta.getTotalCount() / 10 + 1);
            infoListPanel.setDialogBookInfoList(innerMeta.getBookInfoList());
            e.getPaginationPanel().updateUI();
            infoListPanel.updateUI();
        });
        SearchMeta meta = BookInfoSearchRequester.requestBookSearch(searchScope, query, 1);
        paginationPanel.setLastPage(meta.getTotalCount() / 10 + 1);
        infoListPanel.setDialogBookInfoList(meta.getBookInfoList());
        paginationPanel.updateUI();
        infoListPanel.updateUI();
    }

    @Override
    public void sendBookInfoToReceiver(DialogBookInfo info) {
        // TODO: Action Add Stock Button
    }
}
