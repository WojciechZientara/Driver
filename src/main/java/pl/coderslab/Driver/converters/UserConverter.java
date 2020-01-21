package pl.coderslab.Driver.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.coderslab.Driver.dto.UserDto;
import pl.coderslab.Driver.entities.User;
import pl.coderslab.Driver.repositories.RoleRepository;
import pl.coderslab.Driver.repositories.UserRepository;

import java.util.HashSet;

@Component
public class UserConverter {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder encoder;

    
    public User convertUserDtoToUser(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(encoder.encode(userDto.getPassword()));
        user.setTestResults(new HashSet<>());
        user.setMessages(new HashSet<>());
        user.setDisplays(new HashSet<>());
        user.setAdvices(new HashSet<>());
        user.setRoles(new HashSet<>());
        user.getRoles().add(roleRepository.findRoleByName("ROLE_USER"));
        return user;
    }

}