package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import kr.re.kitri.fiveminutes.bookstorepos.util.view.SpringUtilities;
import lombok.Data;

import javax.swing.*;
import java.util.List;

public class InfoLabelsPanel<T extends JComponent> extends JPanel {

    private final List<Entry<T>> entryList;

    public InfoLabelsPanel(List<Entry<T>> entryList, int horizontalWrap, int verticalWrap) {
        super(new SpringLayout());
        this.entryList = entryList;
        addEntryListInPanel();
        SpringUtilities.makeCompactGrid(this, entryList.size(), 2, horizontalWrap, verticalWrap,
                                                                              horizontalWrap, verticalWrap);
    }

    private void addEntryListInPanel() {
        for (Entry<?> entry : entryList) {
            JLabel columnLabel = new JLabel("<html><b>" + entry.getColumn() + "</b></html>", JLabel.TRAILING);
            add(columnLabel);
            add(entry.getValue());
        }
    }

    @Data
    public static class Entry<T extends JComponent> {
        private final String column;
        private final T value;
    }

}
