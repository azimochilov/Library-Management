package library.com.repository.library;

import library.com.domain.entities.library.Branch;
import library.com.domain.entities.user.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository  extends JpaRepository<Branch,Long> {
    Branch getById(Long branchId);
}
