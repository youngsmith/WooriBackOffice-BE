package com.woori.wooribackoffice.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.woori.wooribackoffice.domain.dto.request.UserRegisterRequest;
import com.woori.wooribackoffice.domain.dto.response.UserRepresentation;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@Table(name = "member")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    @Column(name = "is_enabled")
    private boolean enabled;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<UserRole> userRoles = new ArrayList<>();

    public List<SimpleGrantedAuthority> getRoles() {
        return userRoles.stream().map(UserRole::getRole)
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toList());
    }

    public UserRepresentation toUserRepresentation() {
        return UserRepresentation.builder()
                .userName(this.username).build();
    }

    public static User from(UserRegisterRequest userRegisterRequest) {
        return new User().setUsername(userRegisterRequest.getUsername())
                .setEnabled(true)
                .setPassword(userRegisterRequest.getPassword());
    }
}
