package net.springProject.springboot.controller;
import lombok.AllArgsConstructor;
import net.springProject.springboot.dto.UserDto;
import net.springProject.springboot.entity.User;
import net.springProject.springboot.exception.ErrorDetails;
import net.springProject.springboot.exception.ResourceNotFoundException;
import net.springProject.springboot.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {
    private UserService userService;

    // Build create User REST API
    // http://localhost:8080/api/users
    @PostMapping
    public ResponseEntity <UserDto> createUser(@RequestBody  UserDto user){
        UserDto savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);    }

    // Buid get user by Id REST API
    //http://localhost:8080/api/users/1
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
        UserDto user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // Build get all users REST API
    // http://localhost:8080/api/users
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllusers(){
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    // Buid update user REST API
    //http://localhost:8080/api/users/1
    @PutMapping("{id}")
    public  ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId, @RequestBody UserDto user){
        user.setId(userId);
        UserDto updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    //Build delete user REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("User successfully deleted", HttpStatus.OK);
    }
    //to hand specific exception
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
//        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(), webRequest.getDescription(false), "USER_NOT_FOUND");
//        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
//    }
}
