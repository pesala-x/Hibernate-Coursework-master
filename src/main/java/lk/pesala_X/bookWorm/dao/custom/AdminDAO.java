package lk.pesala_X.bookWorm.dao.custom;

import lk.pesala_X.bookWorm.dao.CrudDAO;
import lk.pesala_X.bookWorm.entity.Admin;

public interface AdminDAO extends CrudDAO<Admin> {

    Admin searchAdmin(String name,String password);

}
