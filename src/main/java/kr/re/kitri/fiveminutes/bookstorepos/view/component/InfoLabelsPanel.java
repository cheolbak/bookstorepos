package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import kr.re.kitri.fiveminutes.bookstorepos.util.view.SpringUtilities;
import lombok.Data;

import javax.swing.*;
import java.util.List;

public class InfoLabelsPanel extends JPanel {

    private final List<Entry> entryList;

    public InfoLabelsPanel(List<Entry> entryList, int horizontalWrap, int verticalWrap) {
        super(new SpringLayout());
        this.entryList = entryList;
        addEntryListInPanel();
        SpringUtilities.makeCompactGrid(this, entryList.size(), 2, horizontalWrap, verticalWrap,
                                                                              horizontalWrap, verticalWrap);
    }

    private void addEntryListInPanel() {
        for (Entry entry : entryList) {
            JLabel columnLabel = new JLabel("<html><b>" + entry.getColumn() + "</b></html>", JLabel.TRAILING);
            JLabel infoLabel = new JLabel(entry.getValue());
            add(columnLabel);
            add(infoLabel);
        }
    }

    @Data
    public static class Entry {
        private final String column;
        private final String value;
    }

}
