package lk.ijse.dog_rescue_management_system.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Donor {
    private String donorId;
    private String donorName;
    private String donorContact;
    private String donorEmail;
    private String donorAddress;
    private Double donorAmount;
}
