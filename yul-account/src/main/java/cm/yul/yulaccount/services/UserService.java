package cm.yul.yulaccount.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cm.yul.yulaccount.dto.UserDto;
import cm.yul.yulaccount.entities.Role;
import cm.yul.yulaccount.entities.User;
import cm.yul.yulaccount.entities.Validation;
import cm.yul.yulaccount.enumeration.UserRole;
import cm.yul.yulaccount.exceptions.EntityAlReadyExistException;
import cm.yul.yulaccount.exceptions.EntityNotFoundException;
import cm.yul.yulaccount.mappers.UserDtoMapper;
import cm.yul.yulaccount.mappers.UserMapper;
import cm.yul.yulaccount.repositories.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    
    private UserRepository repository;
    private UserMapper mapper;
    private UserDtoMapper dtoMapper;
    private BCryptPasswordEncoder passwordEncoder;
    private ValidationService validationService;


    public List<UserDto> getAllUser()
    {
        List<UserDto> userList = new ArrayList<>();
        Iterable<User> i = repository.findAll();
        
        Iterator<User> iterator = i.iterator();
        while(iterator.hasNext())
        {
            userList.add(dtoMapper.apply(iterator.next()));
        }
        return userList;
    }

    public UserDto getUSerByEmail(String email)
    {
       Optional<User> u = repository.findByEmail(email);
       if(u.isEmpty())
       {
         throw new EntityNotFoundException("l'utilisateur ayant l'email "+email+" n'existe pas !");
       }

       return dtoMapper.apply(u.get());
    }

    public UserDto getUserByPhone(String number)
    {
        if(repository.findByPhoneNumber(number).isEmpty())
        {
            throw new EntityNotFoundException(" l'utilisateur ayant le numero suivant "+number+" est intouvable");
        }
        return dtoMapper.apply(repository.findByPhoneNumber(number).get());
    }

    public UserDto createUser(UserDto dto)
    {
        Optional<User> u = repository.findByEmail(dto.email());
        if (u.isPresent())
        {
            throw new EntityAlReadyExistException("l'utilisateur entre est deja inscrit !");
        }
        else
        {
            User usr = new User();
            usr.setFirstname(dto.firstname());
            usr.setLastname(dto.lastname());
            usr.setEmail(dto.email());
            usr.setPassword(passwordEncoder.encode(dto.password()));
            usr.setPhoneNumber(dto.phoneNumber());
            usr.setRole(new Role(null, UserRole.USER));
            
            User user = repository.save(usr);
            validationService.saveUser(user);
           return dtoMapper.apply(user);
        }
    }

    public void whipeUserByEmail(String email)
    {
        repository.deleteByEmail(email);
    }

    public void whipeByphoneNumber(String number)
    {
        repository.deleteByPhoneNumber(number);
    }

    public void removeAllUser()
    {
        repository.deleteAll();
    }
    
    public void receiveActivation(Map<String,String> activation)
    {
        Validation valid = validationService.getByCode(activation.get("code"));
        if(Instant.now().isAfter(valid.getExpiration()))
        {
            throw new RuntimeException("code de validation expire !");
        }

        Optional<User> usr = repository.findByEmail(valid.getUser().getEmail());
        if(usr.isEmpty())
        {
            throw new EntityNotFoundException("l'utilisateur recherche n'existe pas !");
        }
        else
        {
            User u =  usr.get();
            u.setActive(true);
        }
    }

    public UserDto updateUserByEmail(String Email,UserDto dto)
    {
        User u = mapper.apply(this.getUSerByEmail(Email));
        if(u==null)
        {
            throw new EntityAlReadyExistException("l'utilisateur que vous recherches n'existe pas !");
        }
        else
        {
            u.setFirstname(dto.firstname());
            u.setLastname(dto.lastname());
            u.setEmail(dto.email());
            u.setPassword(passwordEncoder.encode(dto.password()));
            u.setPhoneNumber(dto.phoneNumber());
            u.setRole(new Role(null, UserRole.USER));
        }

        return dtoMapper.apply(repository.save(u));
    }
}
