package com.woori.wooribackoffice.service;


import com.google.common.collect.ImmutableMap;
import com.woori.wooribackoffice.constant.RoleType;
import com.woori.wooribackoffice.domain.dto.request.UserRegisterRequest;
import com.woori.wooribackoffice.domain.dto.request.UserUpdateRequest;
import com.woori.wooribackoffice.domain.dto.response.UserRepresentation;
import com.woori.wooribackoffice.domain.entity.Role;
import com.woori.wooribackoffice.domain.entity.User;
import com.woori.wooribackoffice.domain.entity.UserRole;
import com.woori.wooribackoffice.exception.RoleNotFoundException;
import com.woori.wooribackoffice.exception.UserNameAlreadyExistException;
import com.woori.wooribackoffice.exception.UserNameNotFoundException;
import com.woori.wooribackoffice.repository.RoleRepository;
import com.woori.wooribackoffice.repository.UserRepository;
import com.woori.wooribackoffice.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    public static final String USERNAME = "username:";
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional(rollbackFor = Exception.class)
    public void save(UserRegisterRequest userRegisterRequest) {
        ensureUserNameNotExist(userRegisterRequest.getUsername());

        userRegisterRequest.setPassword(bCryptPasswordEncoder.encode(userRegisterRequest.getPassword()));
        User user = User.from(userRegisterRequest);
        userRepository.save(user);

        Role adminRole = roleRepository.findByName(RoleType.ADMIN.getName()).orElseThrow(() -> new RoleNotFoundException(ImmutableMap.of("roleName", RoleType.ADMIN.getName())));
        userRoleRepository.save(UserRole.of(user, adminRole));
    }

    public User findByName(String userName) {
        return userRepository.findByUsername(userName)
                .orElseThrow(() -> new UserNameNotFoundException(ImmutableMap.of(USERNAME, userName)));
    }

    public void update(UserUpdateRequest userUpdateRequest) {
        User user = findByName(userUpdateRequest.getUserName());
        if (Objects.nonNull(userUpdateRequest.getPassword())) {
            user.setPassword(bCryptPasswordEncoder.encode(userUpdateRequest.getPassword()));
        }
        if (Objects.nonNull(userUpdateRequest.getEnabled())) {
            user.setEnabled(userUpdateRequest.getEnabled());
        }
        userRepository.save(user);
    }


    public void delete(String userName) {
        if (!userRepository.existsByUsername(userName)) {
            throw new UserNameNotFoundException(ImmutableMap.of(USERNAME, userName));
        }
        userRepository.deleteByUsername(userName);
    }

    public Page<UserRepresentation> getAll(int pageNum, int pageSize) {
        return userRepository.findAll(PageRequest.of(pageNum, pageSize)).map(User::toUserRepresentation);
    }

    public boolean check(String currentPassword, String password) {
        return this.bCryptPasswordEncoder.matches(currentPassword, password);
    }

    private void ensureUserNameNotExist(String username) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new UserNameAlreadyExistException(ImmutableMap.of(USERNAME, username));
        }
    }
}
