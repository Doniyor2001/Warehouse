package ai.ecma.appwarehouseproject.repository;

import ai.ecma.appwarehouseproject.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
