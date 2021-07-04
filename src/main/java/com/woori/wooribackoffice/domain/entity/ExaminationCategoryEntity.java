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
public class ExaminationCategoryEntity extends AuditBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "examination_id")
    private ExaminationEntity examinationEntity;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryEntity;

    private String name;

    private String result;

    @Override
    public String toString() {
        return "ExaminationCategory{" +
                "id=" + id +
                ", examination=" + examinationEntity +
                ", category=" + categoryEntity +
                ", name='" + name + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
