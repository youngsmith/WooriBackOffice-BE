package com.woori.wooribackoffice.domain.entity;

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
public class Examination extends AuditBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "diagnostic_result")
    private String diagnosticResult;

    @Column(name = "registration_number")
    private String registrationNumber;

    @ManyToOne
    @JoinColumn(name = "farm_id")
    private Farm farm;

    @Override
    public String toString() {
        return "Examination{" +
                "id=" + id +
                ", diagnosticResult='" + diagnosticResult + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", farm=" + farm +
                '}';
    }
}
