package ai.ecma.appwarehouseproject.repository;

import ai.ecma.appwarehouseproject.entity.Measure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasureRepository extends JpaRepository<Measure,Long> {

    boolean existsByNameAndActiveTrue(String name);

    boolean existsByNameAndIdNot(String name, Long id);

}
