package lk.ijse.dog_rescue_management_system.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class User {
    private String userId;
    private String userName;
    private String userContact;
    private String userEmail;
    private String userAddress;
    private String userPassword;
    private String userRole; //"admin", "vet", "owner"
}
