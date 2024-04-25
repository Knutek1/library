package com.kodilla.kodillalibrary.mapper;

import com.kodilla.kodillalibrary.domain.User;
import com.kodilla.kodillalibrary.domain.UserDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserMapper {
    public User mapToUser(UserDto userDto){
        return new User(userDto.getId(), userDto.getName(), userDto.getSurname(), userDto.getDateOfJoining());
    }
    public UserDto mapToUserDto(User user){
        return new UserDto(user.getId(), user.getName(), user.getSurname(), user.getDateOfJoining());
    }
    public List<UserDto> mapToUserDtoList (List<User> userList){
        return userList.stream()
                .map(this::mapToUserDto)
                .toList();
    }
}
