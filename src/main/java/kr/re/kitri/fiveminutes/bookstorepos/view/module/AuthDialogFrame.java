package kr.re.kitri.fiveminutes.bookstorepos.view.module;

import kr.re.kitri.fiveminutes.bookstorepos.dao.POSEntryDAO;
import kr.re.kitri.fiveminutes.bookstorepos.view.MainFrame;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;

@Slf4j
public class AuthDialogFrame extends JFrame {

    public AuthDialogFrame() throws HeadlessException {
        setTitle("로그인");

        initPanel();

        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(380, 114);
        setLocationRelativeTo(null);
        setLocation(getX(), getY());

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
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

        JPasswordField passwordField = createPasswordField();
        JButton submitButton = createAuthSubmitButton();
        JButton passwordChangeButton = createPasswordChangeButton();

        panel.add(createPasswordLabel());
        panel.add(passwordField);
        panel.add(submitButton);
        panel.add(passwordChangeButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                POSEntryDAO posEntryDAO = new POSEntryDAO();
                String realPassword = posEntryDAO.select(0).getPassword();

                if(!BCrypt.checkpw(String.valueOf(passwordField.getPassword()), realPassword)){
                    JOptionPane.showConfirmDialog(null,"관리자 비밀번호가 일치하지 않습니다."
                            ,"비밀번호 일치오류",JOptionPane.DEFAULT_OPTION);
                }
                else{
                    SwingUtilities.invokeLater(MainFrame::new);
                    dispose();
                }
            }
        });

        passwordChangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                POSEntryDAO posEntryDAO = new POSEntryDAO();

                // posEntryDAO.updatePassword(0, "secret"); // testing

                String realPassword = posEntryDAO.select(0).getPassword();
                if(!BCrypt.checkpw(String.valueOf(passwordField.getPassword()), realPassword)){
                    JOptionPane.showConfirmDialog(null,"관리자 비밀번호가 일치해야 변경이 가능합니다."
                            ,"비밀번호 일치오류",JOptionPane.DEFAULT_OPTION);
                }
                else{
                    JPasswordField inputPassword = new JPasswordField(18);
                    int inClick = JOptionPane.showConfirmDialog(null,inputPassword,"새로운 비밀번호를 입력하세요."
                            ,JOptionPane.OK_CANCEL_OPTION);
                    if(inClick == JOptionPane.OK_OPTION) {

                        String inputPasswordText = String.valueOf(inputPassword.getPassword());
                        if (inputPasswordText.equals("") || inputPasswordText.length() <= 5) {
                            JOptionPane.showConfirmDialog(null, "비밀번호는 6자리이상 가능합니다."
                                    , "비밀번호 조건오류", JOptionPane.DEFAULT_OPTION);
                        } else {
                            posEntryDAO.updatePassword(0, inputPasswordText);
                            JOptionPane.showConfirmDialog(null, "새로운 비밀번호로 다시 로그인해주세요."
                                    , "비밀번호 업데이트", JOptionPane.DEFAULT_OPTION);
                        }
                    }
                }
            }
        });

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
