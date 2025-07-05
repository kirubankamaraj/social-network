package com.kirubankamaraj.sbm.resource;


import com.kirubankamaraj.sbm.model.Schema;
import com.kirubankamaraj.sbm.model.UserSchemaMapping;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * @author kiruban-11285
 * @date 05/07/25
 */

public class ShardByResourceAPI {

    private static final String USER_MAPPING_PERSISTENCE = "UserMappingPersistence";
    private static final EntityManagerFactory SHARED_BY_MAPPING_MANAGER_FACTORY;

    static {
        SHARED_BY_MAPPING_MANAGER_FACTORY = Persistence.createEntityManagerFactory(USER_MAPPING_PERSISTENCE);
    }

    public static UserSchemaMapping getUserSchemaMapping(Long userID) {

        EntityManager userSchemaManager = SHARED_BY_MAPPING_MANAGER_FACTORY.createEntityManager();

        return userSchemaManager.find(UserSchemaMapping.class, userID);
    }

    public static Schema getSchema(Long schemaID) {

        EntityManager userSchemaManager = SHARED_BY_MAPPING_MANAGER_FACTORY.createEntityManager();

        return userSchemaManager.find(Schema.class, schemaID);
    }


}
