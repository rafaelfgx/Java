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
        final var entities = userRepository.findAll(dto.getExample(User.class), dto.getPageable());
        if (entities.isEmpty()) throw new NoSuchElementException();
        return mapperService.map(entities, UserDto.class);
    }

    public UserDto get(final UUID id) {
        return mapperService.map(userRepository.findById(id).orElseThrow(), UserDto.class);
    }

    public UUID add(final AddUserDto dto) {
        final var exists = userRepository.existsByEmailOrUsername(dto.getEmail(), dto.getUsername());
        if (exists) throw new ApplicationException(HttpStatus.CONFLICT);
        final var entity = mapperService.map(dto, User.class);
        entity.setId(UUID.randomUUID());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.setAuthorities(List.of(Authority.DEFAULT));
        return userRepository.insert(entity).getId();
    }

    public void update(final UpdateUserDto dto) {
        final var exists = userRepository.existsByEmailOrUsername(dto.getId(), dto.getEmail(), dto.getUsername());
        if (exists) throw new ApplicationException(HttpStatus.CONFLICT);
        final var entity = mapperService.map(dto, User.class);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(entity);
    }

    public void delete(final UUID id) {
        userRepository.deleteById(id);
    }
}
