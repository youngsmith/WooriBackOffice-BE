package com.woori.wooribackoffice.domain.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Accessors(fluent = true, chain = true)
@NoArgsConstructor
@Table(name = "examination_category")
public class ExaminationCategory extends AuditBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "examination_id")
    private Examination examination;

    private String name;

    @Override
    public String toString() {
        return "ExaminationCategory{" +
                "id=" + id +
                ", examination=" + examination +
                ", name='" + name + '\'' +
                '}';
    }
}
