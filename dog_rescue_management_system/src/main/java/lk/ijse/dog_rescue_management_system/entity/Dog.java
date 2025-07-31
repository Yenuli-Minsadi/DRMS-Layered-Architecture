package lk.ijse.dog_rescue_management_system.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Dog {
    private String dogId;
    private String rescueRequestId;
    private String dogName;
    private String dogBreed;
    private String dogColor;
    private String dogSize;
    private String dogGender;
    private String dogStatus;
    private String dogEstAge;
}
