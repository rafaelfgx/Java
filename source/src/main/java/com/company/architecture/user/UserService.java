package com.company.architecture.user;

import com.company.architecture.auth.Authority;
import com.company.architecture.shared.exception.ApplicationException;
import com.company.architecture.shared.services.MapperService;
import com.company.architecture.user.dtos.AddUserDto;
import com.company.architecture.user.dtos.GetUserDto;
import com.company.architecture.user.dtos.UpdateUserDto;
import com.company.architecture.user.dtos.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final MapperService mapperService;
    private final UserRepository userRepository;

    public Page<UserDto> get(final GetUserDto dto) {
        final var users = userRepository.findAll(dto.getExample(User.class), dto.getPageable());
        if (users.isEmpty()) throw new NoSuchElementException();
        return mapperService.mapPage(users, UserDto.class);
    }

    public UserDto get(UUID id) {
        return userRepository.findById(id).map(user -> mapperService.map(user, UserDto.class)).orElseThrow();
    }


    public UUID add(final AddUserDto dto) {
        final var exists = userRepository.existsByEmailOrUsername(dto.email(), dto.username());
        if (exists) throw new ApplicationException(HttpStatus.CONFLICT);
        final var user = mapperService.map(dto, User.class);
        user.setId(UUID.randomUUID());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setAuthorities(List.of(Authority.DEFAULT));
        return userRepository.insert(user).getId();
    }

    public void update(final UpdateUserDto dto) {
        final var exists = userRepository.existsByEmailOrUsername(dto.id(), dto.email(), dto.username());
        if (exists) throw new ApplicationException(HttpStatus.CONFLICT);
        final var user = mapperService.map(dto, User.class);
        user.setPassword(passwordEncoder.encode(dto.password()));
        userRepository.save(user);
    }

    public void delete(final UUID id) {
        userRepository.deleteById(id);
    }
}
