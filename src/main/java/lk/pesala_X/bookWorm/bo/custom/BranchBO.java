package lk.pesala_X.bookWorm.bo.custom;

import javafx.collections.ObservableList;
import lk.pesala_X.bookWorm.bo.SuperBO;
import lk.pesala_X.bookWorm.dto.BranchDTO;

public interface BranchBO extends SuperBO {

    boolean saveBranch(BranchDTO dto) throws Exception;

    boolean updateBranch(String id,BranchDTO dto) throws Exception;

    boolean deleteBranch(String id) throws Exception;

    BranchDTO searchBranch(String id) throws Exception;

    ObservableList<BranchDTO> getAllBranches() throws Exception;

}
