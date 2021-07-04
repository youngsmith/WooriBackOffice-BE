package com.woori.wooribackoffice.domain.entity;

import com.woori.wooribackoffice.domain.dto.request.FarmRequest;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Accessors(fluent = true, chain = true)
@NoArgsConstructor
public class FarmEntity extends AuditBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String owner;

    private String address;

    @Override
    public String toString() {
        return "Farm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public void update(FarmRequest farmRequest) {

    }

    public static FarmEntity from(FarmRequest farmRequest) {
        return new FarmEntity();
    }
}
