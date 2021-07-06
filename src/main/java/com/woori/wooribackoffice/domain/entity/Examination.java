package com.woori.wooribackoffice.domain.entity;

import com.woori.wooribackoffice.domain.dto.request.ExaminationRequest;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
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

    @Column(name = "examination_date")
    private LocalDate examinationDate;

    public void update (ExaminationRequest examinationRequest, Farm farm) {
        this.registrationNumber = examinationRequest.getRegistrationNumber();
        this.examinationDate = examinationRequest.getExaminationDate();
        this.farm = farm;
    }

    public static Examination of(ExaminationRequest examinationRequest, Farm farm) {
        return new Examination().setExaminationDate(examinationRequest.getExaminationDate())
                .setFarm(farm)
                .setRegistrationNumber(examinationRequest.getRegistrationNumber());
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
