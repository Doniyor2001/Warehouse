package ai.ecma.appwarehouseproject.repository;

import ai.ecma.appwarehouseproject.entity.InputProduct;
import ai.ecma.appwarehouseproject.entity.OutputProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutputProductRepository extends JpaRepository<OutputProduct,Long> {

}
