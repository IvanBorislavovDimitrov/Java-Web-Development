package app.config;

import app.constatns.Constants;
import org.modelmapper.ModelMapper;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class BeanProvider {

    @Produces
    public EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory(Constants.PERSISTENCE_UNIT_NAME)
                .createEntityManager();
    }

    @Produces
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

}
