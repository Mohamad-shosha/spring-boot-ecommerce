package com.shosha.ecommerce.config;

import com.shosha.ecommerce.entity.Country;
import com.shosha.ecommerce.entity.Product;
import com.shosha.ecommerce.entity.ProductCategory;
import com.shosha.ecommerce.entity.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * The type My data rest config.
 */
@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    private final EntityManager entityManager;

    /**
     * Instantiates a new My data rest config.
     *
     * @param theEntityManager the entity manager
     */
    @Autowired
    public MyDataRestConfig(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE, HttpMethod.PATCH};
        disableHttpMethods(Product.class,config,theUnsupportedActions);
        disableHttpMethods(ProductCategory.class,config,theUnsupportedActions);
        disableHttpMethods(Country.class,config,theUnsupportedActions);
        disableHttpMethods(State.class,config,theUnsupportedActions);

        exposeIds(config);
    }

    private void disableHttpMethods(Class theclass ,RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(theclass)
                .withItemExposure((metadata, httpMethods)
                        -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metadata, httpMethods)
                        -> httpMethods.disable(theUnsupportedActions));
    }

    private void exposeIds(RepositoryRestConfiguration config) {
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
        List<Class> entityClasses = new ArrayList<>();

        for (EntityType tempEntityType : entities) {
            entityClasses.add(tempEntityType.getJavaType());
        }

        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }
}