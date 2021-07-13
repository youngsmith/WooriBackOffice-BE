package com.woori.wooribackoffice.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.woori.wooribackoffice.security.model.dto.request.UserRegisterRequest;
import com.woori.wooribackoffice.security.model.dto.request.UserRepresentation;
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
@Table(name = "member")     // 테이블 명을 user 로 하면 보통 user 는 예약어이기 때문에 실행시 테이블을 찾을 수 없는 오류가 발생한다. member 테이블로 이름을 변경함
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
