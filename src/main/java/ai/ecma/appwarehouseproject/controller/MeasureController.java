package ai.ecma.appwarehouseproject.controller;

import ai.ecma.appwarehouseproject.payload.ApiResult;
import ai.ecma.appwarehouseproject.payload.MeasureAddDTO;
import ai.ecma.appwarehouseproject.payload.MeasureInfoDTO;
import ai.ecma.appwarehouseproject.payload.MeasureUpdateDTO;
import ai.ecma.appwarehouseproject.utils.AppConstant;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(MeasureController.MEASURE_CONTROLLER_PATH)
public interface MeasureController {

    String MEASURE_CONTROLLER_PATH = AppConstant.BASE_PATH + "/measure/";
    String ADD = "add";
    String DELETE = "delete";
    String UPDATE = "update";
    String VIEW = "view";

    @PreAuthorize(value = "hasAuthority('VIEW_MEASURES')")
    @GetMapping(VIEW)
    ApiResult<List<MeasureInfoDTO>> getAll(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size);

    @PreAuthorize(value = "hasAuthority('VIEW_MEASURE')")
    @GetMapping(VIEW + "/{id}")
    ApiResult<MeasureInfoDTO> getOne(@PathVariable Long id);

    @PreAuthorize(value = "hasAuthority('ADD_MEASURE')")
    @PostMapping(ADD)
    ApiResult<MeasureInfoDTO> add(@RequestBody MeasureAddDTO measureAddDTO);

    @PreAuthorize(value = "hasAuthority('UPDATE_MEASURE')")
    @PutMapping(UPDATE + "/{id}")
    ApiResult<MeasureInfoDTO> update(MeasureUpdateDTO measureUpdateDTO, @PathVariable Long id);

    @PreAuthorize(value = "hasAuthority('DELETE_MEASURE')")
    @DeleteMapping(DELETE + "/{id}")
    String delete(@PathVariable Long id);


}
