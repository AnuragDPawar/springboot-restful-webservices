package net.springProject.springboot.service.impl;
import lombok.AllArgsConstructor;
import net.springProject.springboot.dto.UserDto;
import net.springProject.springboot.entity.User;
import net.springProject.springboot.exception.EmailAlreadyExistsException;
import net.springProject.springboot.exception.ResourceNotFoundException;
import net.springProject.springboot.mapper.UserMapper;
import net.springProject.springboot.repository.UserRepository;
import net.springProject.springboot.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private ModelMapper modelMapper;
    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if (optionalUser.isPresent()){
            throw new EmailAlreadyExistsException("Email already exists for user");
        }
        // Convert UserDto into User JPA entity
        //1. Using DTO class
        //User user = UserMapper.mapToUser(userDto);
        //UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);
        //2. Using modelmapper library
        User user = modelMapper.map(userDto, User.class);
        User savedUser = userRepository.save(user);
        // convert userJPA entity to UserDto
        UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);
        return savedUserDto;

    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
        // With DTO
        //return UserMapper.mapToUserDto(user);
        // with modelmapper
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users =  userRepository.findAll();
        //return users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
        // with modelmapper
        return users.stream().map((user) -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(()-> new ResourceNotFoundException("User", "id", user.getId()));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);
        // with user mapper
        //return UserMapper.mapToUserDto(updatedUser);
        // with modelmapper
        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public void deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "id", userId));
        userRepository.deleteById(userId);
    }


}
