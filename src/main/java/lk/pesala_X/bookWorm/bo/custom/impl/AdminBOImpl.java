package lk.pesala_X.bookWorm.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.pesala_X.bookWorm.bo.custom.AdminBO;
import lk.pesala_X.bookWorm.dao.DAOFactory;
import lk.pesala_X.bookWorm.dao.custom.AdminDAO;
import lk.pesala_X.bookWorm.dto.AdminDTO;
import lk.pesala_X.bookWorm.entity.Admin;

public class AdminBOImpl implements AdminBO {
    private final AdminDAO adminDAO = (AdminDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ADMIN);

    @Override
    public boolean saveAdmin(AdminDTO dto) throws Exception {
        return adminDAO.save(new Admin(
                dto.getName(),
                dto.getPassword()
        ));
    }

    @Override
    public boolean updateAdmin(String id , AdminDTO dto) throws Exception {
        return false;
    }

    @Override
    public boolean deleteAdmin(String id) throws Exception {
        return false;
    }

    @Override
    public AdminDTO searchAdmin(String id) throws Exception {
        return null;
    }

    @Override
    public ObservableList<AdminDTO> getAllAdmins() throws Exception {
        return null;
    }

    @Override
    public Admin searchAdmin(String name, String password) {
        Admin admin = adminDAO.searchAdmin(name, password);
        if (admin != null){
            return new Admin(
                    admin.getName(),
                    admin.getPassword()
            );
        }
        return null;
    }
}
