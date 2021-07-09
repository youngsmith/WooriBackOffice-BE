package com.woori.wooribackoffice.domain.entity;

import com.woori.wooribackoffice.domain.dto.request.ExaminationCategoryRequest;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public static List<ExaminationCategory> of(final Examination examination, final Map<Long, Category> categoryMapById, final List<ExaminationCategoryRequest> examinationCategoryRequests) {
        List<ExaminationCategory> examinationCategories = new ArrayList<>();

        for(ExaminationCategoryRequest examinationCategoryRequest : examinationCategoryRequests) {
            ExaminationCategory examinationCategory = new ExaminationCategory().setResult(examinationCategoryRequest.getResult())
                    .setExamination(examination)
                    .setCategory(categoryMapById.get(examinationCategoryRequest.getCategoryId()));

            examinationCategories.add(examinationCategory);
        }

        return examinationCategories;
    }

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
