package lk.ijse.dog_rescue_management_system.bo;

import lk.ijse.dog_rescue_management_system.bo.custom.impl.*;


public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        ADMIN,
        ADOPTER,
        ADOPTIONPROCESSS,
        APPOINTMENT,
        DOG,
        DONOR,
        EXPENSE,
        MEDICALRECORD,
        REQUESTCASE,
        RESCUER,
        SHELTER,
        QUERY,
        VET
    }

    public SuperBO getBo(BOFactory.BOTypes boType) {
        switch(boType) {
            case ADMIN:
                return new AdminBOImpl();
            case ADOPTER:
                return new AdopterBOImpl();
            case ADOPTIONPROCESSS:
                return new AdoptionProcessBOImpl();
            case APPOINTMENT:
                return new AppointmentBOImpl();
            case DOG:
                return new DogBOImpl();
            case DONOR:
                return new DonorBOImpl();
            case EXPENSE:
                return new ExpenseBOImpl();
            case MEDICALRECORD:
                return new MedicalRecordBOImpl();
            case REQUESTCASE:
                return new RequestCaseBOImpl();
            case RESCUER:
                return new RescuerBOImpl();
            case SHELTER:
                return new ShelterBOImpl();
            case QUERY:
                return new QueryBOImpl();
            case VET:
                return new VetBOImpl();
            default:
                return null;
        }
    }
}
