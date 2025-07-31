package lk.ijse.dog_rescue_management_system.entity;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class AdoptionProcess {
    private String adoptProcessId;
    private String dogId;
    private String adopterId;
    private LocalDate adopterApplyDate;
    private LocalDate adoptApprovalDate;
    private LocalDate adoptionDate;
    private String adoptionStatus;
    private String adoptionType;
}
