package lk.ijse.dog_rescue_management_system.entity;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class MedicalRecord {
    private String medicalRecordId;
    private String dogId;
    private String vetId;
    private LocalDate medicalRecordDate;
    private String diagnosis;
    private String treatment;
    private String medication;
    private String vetNote;
    private String hasLabReport;
    private String labReportReference;
}
