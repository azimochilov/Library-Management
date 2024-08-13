package library.com.repository.role;


import library.com.domain.entities.user.RolePrivilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolePrivilegeRepository extends JpaRepository<RolePrivilege, Long> {

}
