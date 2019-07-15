package ru.job4j.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.entity.annotations.Transmission;
import ru.job4j.persistent.DbProvider;

import java.util.List;

public class TransmissionDaoImpl implements EntityDao<Transmission> {
    private static final Logger LOG = LogManager.getLogger(TransmissionDaoImpl.class.getName());
    private static final TransmissionDaoImpl TRANSMISSION_DAO = new TransmissionDaoImpl();
    private static final DbProvider DB_PROVIDER = DbProvider.getInstance();

    public static TransmissionDaoImpl getInstance() {
        return TRANSMISSION_DAO;
    }

    private TransmissionDaoImpl() {

    }

    @Override
    public List<Transmission> getEntities() {
        return (List<Transmission>) DB_PROVIDER.fn(
                session -> session.createQuery("from Transmission order by id").list()
        );
    }


    @Override
    public void save(Transmission transmission) {
        DB_PROVIDER.cn(
                session -> {
                    session.save(transmission);
                    LOG.info("save - " + transmission);
                }
        );
    }

    @Override
    public void update(Transmission transmission) {
        DB_PROVIDER.cn(
                session -> {
                    session.update(transmission);
                    LOG.info("update - " + transmission);
                }
        );
    }

    @Override
    public void delete(Transmission transmission) {
        DB_PROVIDER.cn(
                session -> {
                    session.delete(transmission);
                    LOG.info("delete - " + transmission);
                }
        );
    }
}
