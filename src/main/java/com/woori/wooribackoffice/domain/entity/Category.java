package com.woori.wooribackoffice.domain.entity;

import com.woori.wooribackoffice.domain.dto.request.CategoryRequest;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@Table(name = "category")
public class Category extends AuditBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    public void update(final CategoryRequest categoryRequest) {
        this.name = categoryRequest.getName();
        this.description = categoryRequest.getDescription();
    }

    public static Category from(final CategoryRequest categoryRequest) {
        return new Category()
                .setDescription(categoryRequest.getDescription())
                .setName(categoryRequest.getName());
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
