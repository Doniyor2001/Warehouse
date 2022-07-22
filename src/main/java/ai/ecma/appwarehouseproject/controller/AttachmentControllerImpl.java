package ai.ecma.appwarehouseproject.controller;

import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.AttachmentDTO;
import ai.ecma.appwarehouseproject.service.interfaces.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class AttachmentControllerImpl implements AttachmentController {
    private final AttachmentService attachmentService;


    @Override
    public ApiResult<AttachmentDTO> upload(MultipartHttpServletRequest request) {
        return attachmentService.uploadDb(request);
    }

    @Override
    public void download(Long id, boolean inline, HttpServletResponse response) {
        attachmentService.downloadDb(id, inline, response);
    }
}
