package app.repositories;

import app.constants.Constants;
import app.entities.Product;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    private final EntityManager entityManager;

    public ProductRepositoryImpl() {
        this.entityManager =
                Persistence.createEntityManagerFactory(Constants.PERSISTENCE_UNIT)
                        .createEntityManager();
    }

    @Override
    public Product getObj(String name) {
        return entityManager.createQuery("SELECT p FROM  Product p WHERE p.name = :name order by p.id", Product.class)
                .setParameter("name", name)
                .setMaxResults(1)
                .getSingleResult();
    }

    @Override
    public List<Product> getAll() {

        return entityManager.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }

    @Override
    public void save(Product obj) {
        entityManager.getTransaction().begin();
        entityManager.persist(obj);
        entityManager.getTransaction().commit();
    }
}
