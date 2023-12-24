/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HELPER;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
//import javax.activation.DataHandler;
//import javax.activation.DataSource;
//import javax.activation.FileDataSource;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author CherryCe
 */
public class HELPER_SendMail {

    public static boolean check;

    public static void sendMail(String mail, String OTP) {
        final String userName = "phattmpk02575@fpt.edu.vn";
        final String passWord = "minhphat64647428";
        final String toEmail = mail;
        final String subject = "One Time Password";
        final String body = "This Is My OTP : " + OTP;
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        Session session = Session.getInstance(prop,
                new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, passWord);
            }
        });
        try {
            Message message = new jakarta.mail.internet.MimeMessage(session);
            message.setFrom(new jakarta.mail.internet.InternetAddress(userName));
            message.setRecipients(
                    Message.RecipientType.TO,
                    jakarta.mail.internet.InternetAddress.parse(toEmail)
            );
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);
            JOptionPane.showMessageDialog(null, "Mail Đã Được Gửi, Vui Lòng Kiểm Tra Thông Tin !!!");
            check = true;
        } catch (MessagingException e) {
            JOptionPane.showMessageDialog(null, "Địa Chỉ Mail Không Hợp Lệ ???");
            check = false;
        }
    }
}
