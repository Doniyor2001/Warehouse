package ai.ecma.appwarehouseproject.repository;

import ai.ecma.appwarehouseproject.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency,Long> {

    boolean existsByNameAndActiveTrue(String name);

    boolean existsByNameAndIdNot(String name, Long id);

}
