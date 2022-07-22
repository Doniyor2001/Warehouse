package ai.ecma.appwarehouseproject.repository;

import ai.ecma.appwarehouseproject.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepository extends JpaRepository<Income,Long> {
}
