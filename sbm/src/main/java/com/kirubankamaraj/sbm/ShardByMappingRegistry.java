package com.kirubankamaraj.sbm;


import com.kirubankamaraj.sbm.model.Schema;
import com.kirubankamaraj.sbm.model.UserSchemaMapping;
import com.kirubankamaraj.sbm.resource.ShardByResourceAPI;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceConfiguration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kiruban-11285
 * @date 05/07/25
 */

public class ShardByMappingRegistry {

    private static final Map<String, EntityManagerFactory> SCHEMA_VS_PERSISTENCE_FACTORY = new HashMap<>();

    private static final ThreadLocal<Long> CURRENT_USER = new ThreadLocal<>();


    public static Long setCurrentUserID(Long userID)
    {
        Long oldUser = CURRENT_USER.get();

        CURRENT_USER.set(userID);

        return oldUser;
    }

    public static Long getCurrentUserID()
    {
        return CURRENT_USER.get();
    }

    public static EntityManagerFactory getCurrentUserPersistenceManagerFactory(String persistenceName) throws ShardByException {
        return getPersistenceManagerFactory(CURRENT_USER.get(), persistenceName);
    }

    private static EntityManagerFactory getPersistenceManagerFactory(Long userID, String persistenceName) throws ShardByException {

        UserSchemaMapping userSchemaMapping = ShardByResourceAPI.getUserSchemaMapping(userID);

        if (userSchemaMapping == null) {
            // This user is not exist
            throw new ShardByException("No DataSpace is available");
        }

        String schemaKey = userSchemaMapping.getClusterIP().concat("-").concat(userSchemaMapping.getSchemaName()).concat("-")
                .concat(persistenceName);

        if (SCHEMA_VS_PERSISTENCE_FACTORY.containsKey(schemaKey)) {
            return SCHEMA_VS_PERSISTENCE_FACTORY.get(schemaKey);
        }

        Schema schema = ShardByResourceAPI.getSchema(userSchemaMapping.getSchema().getSchemaID());

        Map<String, String> properties = new HashMap<>();
        properties.put(PersistenceConfiguration.JDBC_URL, constructJDBC_URL(schema));
        properties.put(PersistenceConfiguration.JDBC_USER, schema.getUserName());
        properties.put(PersistenceConfiguration.JDBC_PASSWORD, schema.getPassword());

        EntityManagerFactory persistenceManagerFactory = Persistence.createEntityManagerFactory(persistenceName, properties);
        SCHEMA_VS_PERSISTENCE_FACTORY.put(schemaKey, persistenceManagerFactory);
        return persistenceManagerFactory;
    }

    private static String constructJDBC_URL(Schema schema) {
        return "jdbc:mysql://".concat(schema.getClusterIP()).concat(":").concat("3306").concat("/").concat(schema.getSchemaName());
    }


}
