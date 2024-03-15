package lk.pesala_X.bookWorm.dao;

import lk.pesala_X.bookWorm.dao.custom.impl.*;
public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null)? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        USER,ADMIN,BRANCH,BOOK,TRANSACTION
    }

    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case USER:
                return new UserDAOImpl();
            case ADMIN:
                return new AdminDAOImpl();
            case BRANCH:
                return new BranchDAOImpl();
            case BOOK:
                return new BookDAOImpl();
            case TRANSACTION:
                return new TransactionDAOImpl();
            default:
                return null;
        }
    }
}
