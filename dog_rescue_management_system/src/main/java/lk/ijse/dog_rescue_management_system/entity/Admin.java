package lk.ijse.dog_rescue_management_system.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Admin {
    private String adminId;
    private String adminName;
    private String adminContact;
    private String adminEmail;
    private String adminAddress;
}
