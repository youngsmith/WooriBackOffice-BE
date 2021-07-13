package com.woori.wooribackoffice.security.service;


import com.google.common.collect.ImmutableMap;
import com.woori.wooribackoffice.security.constant.RoleType;
import com.woori.wooribackoffice.security.model.dto.request.UserRegisterRequest;
import com.woori.wooribackoffice.domain.entity.Role;
import com.woori.wooribackoffice.domain.entity.User;
import com.woori.wooribackoffice.domain.entity.UserRole;
import com.woori.wooribackoffice.exception.RoleNotFoundException;
import com.woori.wooribackoffice.exception.UserNameAlreadyExistException;
import com.woori.wooribackoffice.exception.UserNameNotFoundException;
import com.woori.wooribackoffice.security.repository.RoleRepository;
import com.woori.wooribackoffice.security.repository.UserRepository;
import com.woori.wooribackoffice.security.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public void delete(String userName) {
        if (!userRepository.existsByUsername(userName)) {
            throw new UserNameNotFoundException(ImmutableMap.of(USERNAME, userName));
        }
        userRepository.deleteByUsername(userName);
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
