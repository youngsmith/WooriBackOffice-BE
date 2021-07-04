package com.woori.wooribackoffice.domain.entity;

import com.woori.wooribackoffice.domain.dto.request.ExaminationRequest;
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
@Accessors(fluent = true, chain = true)
@NoArgsConstructor
@Table(name = "examination")
public class Examination extends AuditBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "registration_number")
    private String registrationNumber;

    @ManyToOne
    @JoinColumn(name = "farm_id")
    private Farm farm;

    public void update (ExaminationRequest examinationRequest) {

    }

    public static Examination from(ExaminationRequest examinationRequest) {
        return null;
    }

    @Override
    public String toString() {
        return "Examination{" +
                "id=" + id +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", farm=" + farm +
                '}';
    }
}
