package app.configuration;

import org.modelmapper.ModelMapper;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.enterprise.inject.Produces;

public class Config {

    @Produces
    public ModelMapper createModelMapper() {
        return new ModelMapper();
    }

    @Produces
    public EntityManager createEntityManager() {
        return Persistence.createEntityManagerFactory("cats_homework")
                .createEntityManager();
    }
}
