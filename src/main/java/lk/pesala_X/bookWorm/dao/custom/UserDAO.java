package lk.pesala_X.bookWorm.dao.custom;

import lk.pesala_X.bookWorm.dao.CrudDAO;
import lk.pesala_X.bookWorm.entity.User;

public interface UserDAO extends CrudDAO<User> {

    User searchUser(String name, String password);

    User searchUserId(String name);

}
