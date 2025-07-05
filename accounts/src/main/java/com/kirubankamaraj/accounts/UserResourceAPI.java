package com.kirubankamaraj.accounts;


import com.kirubankamaraj.accounts.model.User;
import com.kirubankamaraj.accounts.model.UserIDMaintainer;
import com.kirubankamaraj.sbm.ShardByException;
import com.kirubankamaraj.sbm.ShardByMappingRegistry;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.LockModeType;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

/**
 * @author kiruban-11285
 * @date 05/07/25
 */

public class UserResourceAPI {

    private static final String PERSISTENCE_UNIT_NAME = "AccountsPersistence";

    private static EntityManager getAccountsManager() throws ShardByException {

        EntityManagerFactory userPersistenceManagerFactory = ShardByMappingRegistry.getCurrentUserPersistenceManagerFactory(PERSISTENCE_UNIT_NAME);

        return userPersistenceManagerFactory.createEntityManager();
    }

    private static User getUserData(Long userID) throws ShardByException {

        EntityManager accountsManager = getAccountsManager();

        return accountsManager.find(User.class, userID);
    }

    public static User getCurrentUserData() throws ShardByException {
        return getUserData(ShardByMappingRegistry.getCurrentUserID());
    }

    public static User createUser(User user) throws ShardByException, UserException {

        UserValidationUtil.validateUser(user);

        EntityManager accountsManager = getAccountsManager();

        accountsManager.getTransaction().begin();

        accountsManager.persist(user);

        accountsManager.getTransaction().commit();


        return user;

    }

    public static Long getNewUserID() throws ShardByException {

        EntityManager accountsManager = getAccountsManager();

        accountsManager.getTransaction().begin();

        TypedQuery<UserIDMaintainer> query = accountsManager.createQuery("SELECT userID FROM UserIDMaintainer", UserIDMaintainer.class);
        query.setLockMode(LockModeType.PESSIMISTIC_READ);

        List<UserIDMaintainer> userIDMaintainers = query.getResultList();

        UserIDMaintainer userIDMaintainer = userIDMaintainers.get(0);

        Long newUserID = userIDMaintainer.getUserID();

        userIDMaintainer.setUserID(newUserID + 1L);

        accountsManager.persist(userIDMaintainer);

        accountsManager.getTransaction().commit();

        return newUserID;
    }

}
