package ai.ecma.appwarehouseproject.repository;

import ai.ecma.appwarehouseproject.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByName(String name);

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);

    @Query(nativeQuery = true, value = "delete from roles_permissions r where r.permissions=:permission and r.roles_id=:id")
    void deletePermissions(@Param("permission") String permission,
                           @Param("id") Long id);

}
