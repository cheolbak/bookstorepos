package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import kr.re.kitri.fiveminutes.bookstorepos.util.Util;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.StockBookInfo;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class StockRegisterList extends JList<StockBookInfo> implements DataRegister<StockBookInfo> {

    private final StockRegisterListModel listModel;

    public StockRegisterList() {
        this.listModel = new StockRegisterListModel();

        setCellRenderer(new StockRegisterListCellRenderer());
        setModel(listModel);
    }

    @Override
    public void put(StockBookInfo info) {
        listModel.put(info);
    }

    public void removeAt(int index) {
        listModel.removeAt(index);
    }

    private static class StockRegisterListCellRenderer implements ListCellRenderer<StockBookInfo> {

        private final ListCellPanel panel = new ListCellPanel();

        @Override
        public Component getListCellRendererComponent(JList<? extends StockBookInfo> parentList, StockBookInfo value,
                                                      int index, boolean isSelected, boolean cellHasFocus) {
            return panel.setInfoView(value).status(isSelected);
        }

        public static class ListCellPanel extends JPanel {

            public ListCellPanel() {
                super(new GridBagLayout());
                setBackground(Color.WHITE);
            }

            public ListCellPanel setInfoView(StockBookInfo info) {
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

            private void initPanel(StockBookInfo info) {
                removeAll();
                add(createBookCoverImageLabel(info.getBookCoverImage()), createImageLabelConstraints());
                add(createInfoLabelsPanel(info), createInfoLabelsConstraints());
                add(createStockInfoLabelsPanel(info), createStockLabelsConstraints());
            }

            private JLabel createBookCoverImageLabel(BufferedImage original) {
                BufferedImage image = Util.resizeImage(original, 36, 54);
                JLabel label = new JLabel(new ImageIcon(image));
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                return label;
            }

            private JPanel createInfoLabelsPanel(StockBookInfo info) {
                List<InfoLabelsPanel.Entry<JLabel>> entries = Arrays.asList(
                        new InfoLabelsPanel.Entry<>("ISBN", new JLabel(info.getIsbn())),
                        new InfoLabelsPanel.Entry<>("제목", reduceTextAndCreateLabel(info.getTitle())),
                        new InfoLabelsPanel.Entry<>("출판", reduceTextAndCreateLabel(info.getPublisher()))
                );
                InfoLabelsPanel<JLabel> labelPanel = new InfoLabelsPanel<>(entries, 4, 2);
                labelPanel.setBackground(new Color(255, 255, 255, 0));
                return labelPanel;
            }

            private JLabel reduceTextAndCreateLabel(String text) {
                if (text.length() <= 20) {
                    return new JLabel(text);
                }
                JLabel label = new JLabel(text.substring(0, 18) + "...");
                label.setToolTipText(text);
                return label;
            }

            private JPanel createStockInfoLabelsPanel(StockBookInfo info) {
                List<InfoLabelsPanel.Entry<JLabel>> entries = Arrays.asList(
                        new InfoLabelsPanel.Entry<>("가격", new JLabel(String.valueOf(info.getOriginalPrice()))),
                        new InfoLabelsPanel.Entry<>("현재", new JLabel(String.valueOf(info.getCurrentStock()))),
                        new InfoLabelsPanel.Entry<>("추가", new JLabel(String.valueOf(info.getInsertStock())))
                );
                InfoLabelsPanel<JLabel> labelPanel = new InfoLabelsPanel<>(entries, 4, 2);
                labelPanel.setBackground(new Color(255, 255, 255, 0));
                return labelPanel;
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
                c.weightx = 0.2;
                return c;
            }

            private GridBagConstraints createStockLabelsConstraints() {
                GridBagConstraints c = new GridBagConstraints();
                c.insets = new Insets(5, 5, 5, 5);
                c.anchor = GridBagConstraints.WEST;
                return c;
            }

        } // StockRegisterListCellRenderer.ListCellPanel inner class End

    } // StockRegisterListCellRenderer inner class End

    private static class StockRegisterListModel implements ListModel<StockBookInfo> {
        private final List<StockBookInfo> bookInfoList;

        public StockRegisterListModel() {
            this.bookInfoList = new ArrayList<>();
        }

        public void put(StockBookInfo info) {
            bookInfoList.add(info);
        }

        public void removeAt(int index) {
            bookInfoList.remove(index);
        }

        @Override
        public int getSize() {
            return bookInfoList.size();
        }

        @Override
        public StockBookInfo getElementAt(int index) {
            return bookInfoList.get(index);
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
