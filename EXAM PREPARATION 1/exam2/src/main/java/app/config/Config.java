package app.config;

import app.constants.Constants;
import org.modelmapper.ModelMapper;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Config {

    @Produces
    public EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory(Constants.PERSISTENCE_UNIT)
                .createEntityManager();
    }

    @Produces
    public ModelMapper createModelMapper() {
        return new ModelMapper();
    }
}
