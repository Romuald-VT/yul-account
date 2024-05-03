package cm.yul.yulaccount.mappers;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import cm.yul.yulaccount.dto.UserDto;
import cm.yul.yulaccount.entities.User;

@Component
public class UserMapper implements Function<UserDto,User>{

    @Override
    public User apply(UserDto arg0) {
        return new User(null, arg0.firstname(), arg0.lastname(), arg0.email(), arg0.password(), arg0.phoneNumber());
    }
    
}
