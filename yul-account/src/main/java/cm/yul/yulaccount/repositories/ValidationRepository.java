package cm.yul.yulaccount.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cm.yul.yulaccount.entities.Validation;

@Repository
public interface ValidationRepository extends CrudRepository<Validation,Integer>{
    
  Optional<Validation> findByCode(String code);
}
