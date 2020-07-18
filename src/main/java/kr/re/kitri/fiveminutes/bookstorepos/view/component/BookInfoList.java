package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import kr.re.kitri.fiveminutes.bookstorepos.util.Util;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.BookInfo;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.NumberFormat;
import java.util.*;
import java.util.List;

public class BookInfoList extends JList<BookInfo> implements DataRegister<BookInfo> {

    private final BookInfoListModel listModel;

    public BookInfoList() {
        this.listModel = new BookInfoListModel();

        setCellRenderer(new BookInfoListCellRenderer());
        setModel(listModel);
    }

    @Override
    public void put(BookInfo info) {
        listModel.put(info);
    }

    public void removeAtSelected() {
        if (getSelectedIndex() == -1) {
            return;
        }
        BookInfo value = getSelectedValue();
        remove(value.getIsbn());
    }

    public void remove(String isbn) {
        listModel.remove(isbn);
    }

    private static class BookInfoListCellRenderer implements ListCellRenderer<BookInfo> {

        private final ListCellPanel panel = new ListCellPanel();

        @Override
        public Component getListCellRendererComponent(JList<? extends BookInfo> parentList, BookInfo value,
                                                      int index, boolean isSelected, boolean cellHasFocus) {
            return panel.setInfoView(value).status(isSelected);
        }

        public static class ListCellPanel extends JPanel {

            public ListCellPanel() {
                super(new GridBagLayout());
                setBackground(Color.WHITE);
            }

            public ListCellPanel setInfoView(BookInfo info) {
                setToolTipText(info.getTitle());
                initPanel(info);
                return this;
            }

            public ListCellPanel status(boolean select) {
                if (select) {
                    setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    setBackground(new Color(231, 226, 218));
                }
                else {
                    setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
                    setBackground(Color.WHITE);
                }
                return this;
            }

            private void initPanel(BookInfo info) {
                removeAll();
                add(createBookCoverImageLabel(info.getBookCoverImage()), createImageLabelConstraints());
                add(createInfoLabelsPanel(info), createInfoLabelsConstraints());
            }

            private JLabel createBookCoverImageLabel(BufferedImage original) {
                BufferedImage image = Util.resizeImage(original, 40, 60);
                JLabel label = new JLabel(new ImageIcon(image));
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                return label;
            }

            private JPanel createInfoLabelsPanel(BookInfo info) {
                NumberFormat numFormat = NumberFormat.getInstance();
                numFormat.setCurrency(Currency.getInstance(Locale.KOREA));
                List<InfoLabelsPanel.Entry<JLabel>> entries = Arrays.asList(
                        new InfoLabelsPanel.Entry<>("ISBN", new JLabel(info.getIsbn())),
                        new InfoLabelsPanel.Entry<>("제목", reduceTextAndCreateLabel(info.getTitle())),
                        new InfoLabelsPanel.Entry<>("가격", new JLabel(numFormat.format(info.getPrice())))
                );
                InfoLabelsPanel<JLabel> labelPanel = new InfoLabelsPanel<>(entries, 4, 2);
                labelPanel.setBackground(new Color(255, 255, 255, 0));
                return labelPanel;
            }

            private JLabel reduceTextAndCreateLabel(String text) {
                if (text.length() <= 28) {
                    return new JLabel(text);
                }
                JLabel label = new JLabel(text.substring(0, 26) + "...");
                label.setToolTipText(text);
                return label;
            }

            private GridBagConstraints createImageLabelConstraints() {
                GridBagConstraints c = new GridBagConstraints();
                c.insets = new Insets(5, 5, 5, 5);
                c.anchor = GridBagConstraints.WEST;
                return c;
            }

            private GridBagConstraints createInfoLabelsConstraints() {
                GridBagConstraints c = new GridBagConstraints();
                c.insets = new Insets(5, 5, 5, 5);
                c.anchor = GridBagConstraints.WEST;
                c.weightx = 0.1;
                return c;
            }

        } // StockRegisterListCellRenderer.ListCellPanel inner class End

    } // StockRegisterListCellRenderer inner class End

    private static class BookInfoListModel implements ListModel<BookInfo> {
        private final List<String> isbnList;
        private final Map<String, BookInfo> bookInfoMap;

        public BookInfoListModel() {
            this.isbnList = new ArrayList<>();
            this.bookInfoMap = new HashMap<>();
        }

        public void put(BookInfo info) {
            isbnList.add(info.getIsbn());
            bookInfoMap.put(info.getIsbn(), info);
        }

        public void remove(String isbn) {
            isbnList.remove(isbn);
            bookInfoMap.remove(isbn);
        }

        @Override
        public int getSize() {
            return isbnList.size();
        }

        @Override
        public BookInfo getElementAt(int index) {
            return bookInfoMap.get(isbnList.get(index));
        }

        @Override
        public void addListDataListener(ListDataListener l) {
            // TODO: DB Connection Event to POS_Book Table Reference
        }

        @Override
        public void removeListDataListener(ListDataListener l) {

        }
    } // StockRegisterListModel inner class End
}
