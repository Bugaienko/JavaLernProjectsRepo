package pizzaRest.controllers.rest;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pizzaRest.controllers.interfases.BaseControllerInterface;
import pizzaRest.dto.BaseDTO;
import pizzaRest.models.Base;
import pizzaRest.services.BaseService;
import pizzaRest.util.ErrorResponse;
import pizzaRest.util.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Sergii Bugaienko
 */

@RestController
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-04-15T16:01:23.022+02:00[Europe/Berlin]")
@Validated
@Api(value = "Bases", description = "Cafes API")
@RequestMapping("/api/base")
public class BaseRestController implements BaseControllerInterface {

    private final BaseService baseService;
    private final ModelMapper modelMapper;

    public BaseRestController(BaseService baseService, ModelMapper modelMapper) {
        this.baseService = baseService;
        this.modelMapper = modelMapper;
    }

    @Override
    @GetMapping(value = "/all", produces = {"application/json", "application/json"})
    public ResponseEntity<List<BaseDTO>> getAllBases() {
        return ResponseEntity.ok(baseService.findAllSorted().stream().map(this::convertToBaseDto).collect(Collectors.toList()));
    }

    @Override
    @GetMapping(value = "/{id}", produces = {"application/json", "application/json"})
    public ResponseEntity<BaseDTO> getOneBase(@Parameter(in = ParameterIn.PATH, description = "base id", required = true, schema = @Schema()) @PathVariable("id") int id) {
        return ResponseEntity.ok(convertToBaseDto(baseService.findById(id)));
    }

    private BaseDTO convertToBaseDto(Base base){
        return modelMapper.map(base, BaseDTO.class);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(NotFoundException e) {
        ErrorResponse response = new ErrorResponse(
                "Object with this id wasn't found", System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
