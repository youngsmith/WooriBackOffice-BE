package com.woori.wooribackoffice.domain.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@Table(name = "examination_category")
public class ExaminationCategory extends AuditBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "examination_id")
    private Examination examination;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String result;

    @Override
    public String toString() {
        return "ExaminationCategory{" +
                "id=" + id +
                ", examination=" + examination +
                ", category=" + category +
                ", result='" + result + '\'' +
                '}';
    }
}
