package lk.pesala_X.bookWorm.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.pesala_X.bookWorm.bo.custom.UserBO;
import lk.pesala_X.bookWorm.dao.DAOFactory;
import lk.pesala_X.bookWorm.dao.custom.UserDAO;
import lk.pesala_X.bookWorm.dto.UserDTO;
import lk.pesala_X.bookWorm.entity.User;

public class UserBOImpl implements UserBO {

    private final UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public boolean saveUser(UserDTO dto) throws Exception {
        return userDAO.save(new User(
                dto.getName(),
                dto.getEmail(),
                dto.getPassword()
        ));
    }

    @Override
    public boolean updateUser(String id , UserDTO dto) throws Exception {
        return userDAO.update(id,new User(
                dto.getName(),
                dto.getEmail(),
                dto.getPassword()
        ));
    }

    @Override
    public boolean deleteUser(String id) throws Exception {
        return false;
    }

    @Override
    public UserDTO searchUser(String id) throws Exception {
        return null;
    }

    @Override
    public ObservableList<UserDTO> getAllUsers() throws Exception {
        return null;
    }

    @Override
    public User searchUser(String name, String password) {
        User user = userDAO.searchUser(name,password);
        if (user != null){
            return new User(
                    user.getName(),
                    user.getPassword()
            );
        }
        return null;
    }

    @Override
    public User searchUserId(String name)  {
        User user = userDAO.searchUserId(name);
        if (user != null){
            return new User(
                    user.getId()
            );
        }
        return null;
    }
}
