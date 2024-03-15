package lk.pesala_X.bookWorm.bo.custom;

import javafx.collections.ObservableList;
import lk.pesala_X.bookWorm.bo.SuperBO;
import lk.pesala_X.bookWorm.dto.UserDTO;
import lk.pesala_X.bookWorm.entity.User;

public interface UserBO extends SuperBO {

    boolean saveUser(UserDTO dto) throws Exception;

    boolean updateUser(String id,UserDTO dto) throws Exception;

    boolean deleteUser(String id) throws Exception;

    UserDTO searchUser(String id) throws Exception;

    ObservableList<UserDTO> getAllUsers() throws Exception;

    User searchUser(String name, String password) throws Exception;

    User searchUserId(String name) throws Exception;

}
