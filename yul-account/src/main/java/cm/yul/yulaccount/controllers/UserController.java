package cm.yul.yulaccount.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cm.yul.yulaccount.dto.UserDto;
import cm.yul.yulaccount.services.UserService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserController {
    
    private UserService service;

    @GetMapping(path = "/all")
    public ResponseEntity<List<UserDto>> readAllUser()
    {
        return new ResponseEntity<>(service.getAllUser(),HttpStatus.OK);
    }
    
    @GetMapping(path = "/customer/{Email}")
    public ResponseEntity<UserDto> readUserByEmail(@PathVariable(name = "Email") String email)
    {
        return new ResponseEntity<>(service.getUSerByEmail(email),HttpStatus.OK);
    }
    
    @GetMapping(path = "/user/phone/{Phone}")
    public ResponseEntity<UserDto> readUserByPhoneNumber(@PathVariable(name = "Phone") String phone)
    {
        return new ResponseEntity<>(service.getUserByPhone(phone),HttpStatus.OK);
    }
    
    @PostMapping(path="add")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto dto)
    {
        return new ResponseEntity<>(service.createUser(dto),HttpStatus.CREATED);
    }
    
    @PostMapping(path="activate")
    public ResponseEntity<HttpStatus>activateUser(Map<String,String> activation)
    {
        service.receiveActivation(activation);
        return new ResponseEntity<>(HttpStatus.FOUND);
    }

    @PutMapping(path= "/customer/{Email}")
    public ResponseEntity<UserDto> renameUser(@PathVariable(name="Email")String email,@RequestBody UserDto dto)
    {
        return new ResponseEntity<>(service.updateUserByEmail(email, dto),HttpStatus.ACCEPTED);
    }
    
    @DeleteMapping(path= "/customer/{Email}")
    public ResponseEntity<HttpStatus> removeUserByEmail(@PathVariable(name="Email") String email)
    {
        service.whipeUserByEmail(email);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @DeleteMapping(path = "/user/phone/{Phone}")
    public ResponseEntity<HttpStatus> removeUserByPhoneNumber(@PathVariable(name="Phone")String phone)
    {
        service.whipeByphoneNumber(phone);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @DeleteMapping(path= "/all")
    public ResponseEntity<HttpStatus> whipeAllUser()
    {
        service.removeAllUser();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
