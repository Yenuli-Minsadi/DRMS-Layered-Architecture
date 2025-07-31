package lk.ijse.dog_rescue_management_system.entity;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Appointment {
    private String appointmentId;
    private String dogId;
    private String vetId;
    private LocalDate appointmentDate;
    private String appointmentReason;
    private String appointmentStatus;

    public Appointment(String vetId, String appointmentStatus) {
    }
}
