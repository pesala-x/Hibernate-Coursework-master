package lk.pesala_X.bookWorm.bo.custom;

import javafx.collections.ObservableList;
import lk.pesala_X.bookWorm.bo.SuperBO;
import lk.pesala_X.bookWorm.dto.AdminDTO;
import lk.pesala_X.bookWorm.entity.Admin;

public interface AdminBO extends SuperBO {

    boolean saveAdmin(AdminDTO dto) throws Exception;

    boolean updateAdmin(String id,AdminDTO dto) throws Exception;

    boolean deleteAdmin(String id) throws Exception;

    AdminDTO searchAdmin(String id) throws Exception;

    ObservableList<AdminDTO> getAllAdmins() throws Exception;

    Admin searchAdmin(String name, String password);

}
