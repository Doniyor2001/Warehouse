package ai.ecma.appwarehouseproject.repository;

import ai.ecma.appwarehouseproject.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByNameAndActiveTrue(String name);

    boolean existsByNameAndIdNot(String name, Long id);
}
