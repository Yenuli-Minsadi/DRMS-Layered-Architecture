package lk.ijse.dog_rescue_management_system.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Vet {
    private String vetId;
    private String vetName;
    private String vetClinicName;
    private String vetSpecialization;
    private String vetLicenseNum;
    private String vetContact;
    private String vetEmail;
    private String vetAddress;
    private String vetAvailability;
    private Double vetSalary;
}
