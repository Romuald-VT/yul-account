package cm.yul.yulaccount.utils;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import cm.yul.yulaccount.entities.Validation;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NotificationService {
    
    private JavaMailSender mailSender;

    public void sendNotification(Validation validation)
    {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("no-reply@yul.cm");
        mailMessage.setTo(validation.getUser().getEmail());
        mailMessage.setSubject("activation de votre compte");
        mailMessage.setText("Mr/Mme "+validation.getUser().getFirstname()+" utilisez le code suivant pour activer votre compte "+validation.getCode());
        
        mailSender.send(mailMessage);

    }
}
