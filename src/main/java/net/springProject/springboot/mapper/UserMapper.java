package net.springProject.springboot.mapper;
import net.springProject.springboot.dto.UserDto;
import net.springProject.springboot.entity.User;

public class UserMapper {
    // Convert user JPA entity into UserDto
    public static UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail());
        return userDto;
    }
    // Convert user UserDto into JPA entity
    public static  User mapToUser(UserDto userDto){
        User user = new User(userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail());
        return user;
    }
}
