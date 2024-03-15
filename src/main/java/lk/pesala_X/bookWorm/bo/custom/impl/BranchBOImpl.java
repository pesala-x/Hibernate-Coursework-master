package lk.pesala_X.bookWorm.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.pesala_X.bookWorm.bo.custom.BranchBO;
import lk.pesala_X.bookWorm.dao.DAOFactory;
import lk.pesala_X.bookWorm.dao.custom.BranchDAO;
import lk.pesala_X.bookWorm.dto.BranchDTO;
import lk.pesala_X.bookWorm.entity.Branch;

import java.util.ArrayList;
import java.util.List;

public class BranchBOImpl implements BranchBO {
    private final BranchDAO branchDAO = (BranchDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BRANCH);

    @Override
    public boolean saveBranch(BranchDTO dto) throws Exception {
        return branchDAO.save(new Branch(
                dto.getName(),
                dto.getManager(),
                dto.getAddress(),
                dto.getEmail()
        ));
    }

    @Override
    public boolean updateBranch(String id,BranchDTO dto) throws Exception {
        return branchDAO.update(id,new Branch(
                dto.getName(),
                dto.getManager(),
                dto.getAddress(),
                dto.getEmail()
        ));
    }

    @Override
    public boolean deleteBranch(String id) throws Exception {
        return branchDAO.delete(id);
    }

    @Override
    public BranchDTO searchBranch(String id) throws Exception {
        Branch branch = branchDAO.search(id);
        if (branch != null){
            return new BranchDTO(
                    branch.getName(),
                    branch.getManager(),
                    branch.getAddress(),
                    branch.getEmail());
        }
        return null;
    }

    @Override
    public ObservableList<BranchDTO> getAllBranches() throws Exception {
        List<Branch> branchList = branchDAO.loadAll();
        List<BranchDTO> branchDTOS = new ArrayList<>();
        for (Branch branch : branchList){
            branchDTOS.add(new BranchDTO(
                    branch.getId(),
                    branch.getName(),
                    branch.getManager(),
                    branch.getAddress(),
                    branch.getEmail()
            ));
        }
        return FXCollections.observableArrayList(branchDTOS);
    }
}
