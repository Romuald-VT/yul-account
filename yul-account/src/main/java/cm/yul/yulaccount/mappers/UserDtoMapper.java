package cm.yul.yulaccount.mappers;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import cm.yul.yulaccount.dto.UserDto;
import cm.yul.yulaccount.entities.User;

@Component
public class UserDtoMapper implements Function<User,UserDto> {

    @Override
    public UserDto apply(User arg0) {
        return new UserDto(arg0.getFirstname(), arg0.getLastname(), arg0.getEmail(), arg0.getPassword(), arg0.getPhoneNumber());
    }
    
}
