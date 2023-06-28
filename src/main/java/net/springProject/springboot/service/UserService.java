package net.springProject.springboot.service;
import net.springProject.springboot.dto.UserDto;
import net.springProject.springboot.entity.User;
import java.util.List;
public interface UserService {
    UserDto createUser(UserDto user);
    UserDto getUserById(Long userId);
    List<UserDto> getAllUsers();
    UserDto updateUser(UserDto user);

    void deleteUser(Long userId);
}
