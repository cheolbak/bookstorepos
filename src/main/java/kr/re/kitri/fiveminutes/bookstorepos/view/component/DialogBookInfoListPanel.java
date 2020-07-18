package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import kr.re.kitri.fiveminutes.bookstorepos.view.model.DialogBookInfo;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DialogBookInfoListPanel extends JPanel {

    @Setter
    private List<DialogBookInfo> dialogBookInfoList;

    private final DialogBookInfoReceiver parentFrame;

    public DialogBookInfoListPanel(DialogBookInfoReceiver parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(createScrollPane(), BorderLayout.CENTER);
    }

    @Override
    public void updateUI() {
        removeAll();
        add(createScrollPane(), BorderLayout.CENTER);
        super.updateUI();
    }

    public JScrollPane createScrollPane() {
        JScrollPane scrollPane = new JScrollPane(createBookInfoListPanel());
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        return scrollPane;
    }

    public JPanel createBookInfoListPanel() {
        JPanel panel = new JPanel(new GridBagLayout());

        if (dialogBookInfoList == null) {
            return panel;
        }

        DialogBookInfoPanel.AddStockClickListener listener =
                e -> parentFrame.sendBookInfoToReceiver(e.getCurrentBookInfo());

        int count = 0;
        for (DialogBookInfo dialogBookInfo : dialogBookInfoList) {
            panel.add(new DialogBookInfoPanel(dialogBookInfo, listener), createBookInfoPanelConstraints(count));
            count += 1;
        }

        return panel;
    }

    private GridBagConstraints createBookInfoPanelConstraints(int sequence) {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 1;
        c.gridy = sequence;
        return c;
    }
}
