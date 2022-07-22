package ai.ecma.appwarehouseproject.service.interfaces;

import ai.ecma.appwarehouseproject.entity.Attachment;
import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.AttachmentDTO;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;

public interface AttachmentService {
    ApiResult<AttachmentDTO> uploadDb(MultipartHttpServletRequest request);

    void downloadDb(Long id, boolean inline, HttpServletResponse response);

    Attachment getByOrElseThrow(Long attachmentId);
}
