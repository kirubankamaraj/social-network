package com.kirubankamaraj.accounts;


import com.kirubankamaraj.accounts.model.User;
import org.apache.commons.lang3.StringUtils;

/**
 * @author kiruban-11285
 * @date 06/07/25
 */

public class UserValidationUtil {

    static void validateUser(User user) throws UserException {

        if (StringUtils.isEmpty(user.getUserNama())){

            throw new UserException("username cannot be empty");
        }

        if (StringUtils.isEmpty(user.getDisplayName())) {

            throw new UserException("Display name cannot be empty");
        }
    }


}
