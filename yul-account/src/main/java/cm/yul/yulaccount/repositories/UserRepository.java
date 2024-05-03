package cm.yul.yulaccount.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cm.yul.yulaccount.entities.User;

@Repository
public interface UserRepository  extends CrudRepository<User,Integer>{
    
    Optional<User> findByEmail(String email);
    void deleteByEmail(String email);
    Optional<User> findByPhoneNumber(String number);
    void deleteByPhoneNumber (String number);
}
