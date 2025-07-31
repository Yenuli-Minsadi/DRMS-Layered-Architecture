package lk.ijse.dog_rescue_management_system.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Shelter {
    private String shelterId;
    private String shelterLocation;
    private int shelterCapacity;
    private int currentShelterOccupancy;
    private String shelterStatus;
    private String shelterResources;
}
