package kr.re.kitri.fiveminutes.bookstorepos.view.module;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class AuthDialogFrame extends JFrame {

    public AuthDialogFrame() throws HeadlessException {
        setTitle("로그인");

        initPanel();

        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(380, 114);
        setLocationRelativeTo(null);
        setLocation(getX(), getY());
    }

    private void initPanel() {
        add(createPasswordPanel(), BorderLayout.CENTER);
    }

    private JPanel createPasswordPanel() {
        Border margin = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        JPanel marginPanel = new JPanel();
        marginPanel.setBorder(margin);
        marginPanel.setLayout(new BorderLayout());

        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK);
        JPanel panel = new JPanel();
        panel.setBorder(lineBorder);

        panel.add(createPasswordLabel());
        panel.add(createPasswordField());
        panel.add(createAuthSubmitButton());
        panel.add(createPasswordChangeButton());

        marginPanel.add(panel, BorderLayout.CENTER);
        return marginPanel;
    }

    private JLabel createPasswordLabel() {
        return new JLabel("비밀번호 : ");
    }

    private JPasswordField createPasswordField() {
        return new JPasswordField(18);
    }

    private JButton createAuthSubmitButton() {
        return new JButton("확인");
    }

    private JButton createPasswordChangeButton() {
        return new JButton("비밀번호 변경");
    }


}
