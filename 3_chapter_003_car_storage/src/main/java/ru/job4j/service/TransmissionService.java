package ru.job4j.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.dao.EngineDaoImpl;
import ru.job4j.dao.TransmissionDaoImpl;
import ru.job4j.entity.annotations.Transmission;

import java.util.List;

public class TransmissionService {
    private static final Logger LOG = LogManager.getLogger(EngineDaoImpl.class.getName());
    private static final TransmissionDaoImpl TRANSMISSION_DAO = TransmissionDaoImpl.getInstance();
    private static final TransmissionService INSTANCE = new TransmissionService();

    public TransmissionService() {
    }

    public TransmissionService getInstance() {
        return INSTANCE;
    }
    public void save(Transmission transmission) {
        if (transmission != null) {
            TRANSMISSION_DAO.save(transmission);
        }
    }
    public void update(Transmission transmission) {
        if (transmission != null) {
            TRANSMISSION_DAO.update(transmission);
        }
    }
    public void delete(Transmission transmission) {
        if (transmission != null) {
            TRANSMISSION_DAO.delete(transmission);
        }
    }
    public List<Transmission> getTransmissions() {
        return TRANSMISSION_DAO.getEntities();
    }
}
