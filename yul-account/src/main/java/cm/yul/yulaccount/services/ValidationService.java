package cm.yul.yulaccount.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import cm.yul.yulaccount.entities.User;
import cm.yul.yulaccount.entities.Validation;
import cm.yul.yulaccount.repositories.ValidationRepository;
import cm.yul.yulaccount.utils.NotificationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ValidationService {
    
    private ValidationRepository repository;
    private NotificationService notificationService;

    public void saveUser(User user)
    {
        Validation validation = new Validation();
        validation.setUser(user);
        Instant activation = Instant.now();
        validation.setActivation(activation);
        validation.setExpiration(activation.plus(10,ChronoUnit.MINUTES));

        Random rand = new Random();
        rand.nextInt(999999);
        String code = String.format("%06d", rand);
        validation.setCode(code);

        repository.save(validation);
        notificationService.sendNotification(validation);
    }

    public Validation getByCode(String code)
    {
        Optional<Validation> validation = repository.findByCode(code);
        if(validation.isEmpty())
        {
             throw new EntityNotFoundException("la validation recherchee est introuvable !");
        }
        else
        {
            return validation.get();
        }
    }
}
