package lk.ijse.dog_rescue_management_system.dao.custom;

import lk.ijse.dog_rescue_management_system.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
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
        USER,
        VET


    }

    public SuperDAO getDAO(DAOTypes daoType) {
        switch(daoType) {
            case ADMIN:
                return new AdminDAOImpl();
            case ADOPTER:
                return new AdopterDAOImpl();
            case ADOPTIONPROCESSS:
                return new AdoptionProcessDAOImpl();
            case APPOINTMENT:
                return new AppointmentDAOImpl();
            case DOG:
                return new DogDAOImpl();
            case DONOR:
                return new DonorDAOImpl();
            case EXPENSE:
                return new ExpenseDAOImpl();
            case MEDICALRECORD:
                return new MedicalRecordDAOImpl();
            case REQUESTCASE:
                return new RequestCaseDAOImpl();
            case RESCUER:
                return new RescuerDAOImpl();
            case SHELTER:
                return new ShelterDAOImpl();
            case USER:
                return new UserDAOImpl();
            case VET:
                return new VetDAOImpl();
            default:
                return null;
        }
    }
}
