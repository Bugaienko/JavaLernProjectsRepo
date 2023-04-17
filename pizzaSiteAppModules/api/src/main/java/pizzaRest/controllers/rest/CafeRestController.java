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
import pizzaRest.controllers.interfases.CafeControllerInt;
import pizzaRest.dto.CafeAddBody;
import pizzaRest.dto.CafeDTO;
import pizzaRest.models.Cafe;
import pizzaRest.services.CafeService;
import pizzaRest.util.ErrorResponse;
import pizzaRest.util.NotFoundException;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Sergii Bugaienko
 */

@RestController
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-04-15T16:01:23.022+02:00[Europe/Berlin]")
@Validated
@Api(value = "Cafe", description = "Cafes API")
@RequestMapping("/api/cafe")
public class CafeRestController implements CafeControllerInt {

    private final ModelMapper modelMapper;
    private final CafeService cafeService;

    public CafeRestController(ModelMapper modelMapper, CafeService cafeService) {
        this.modelMapper = modelMapper;
        this.cafeService = cafeService;
    }


    @Override
    @GetMapping(value = "/all", produces = {"application/json"})
    public ResponseEntity<List<CafeDTO>> getAllCafes() {
        return ResponseEntity.ok(cafeService.findAll().stream().map(this::convertCafeToDTO).collect(Collectors.toList()));
    }

    @Override
    @GetMapping(value = "/{cafeId}", produces = {"application/json"})
    public ResponseEntity<CafeDTO> getCafe(@Parameter(in = ParameterIn.PATH, description = "cafe id", required = true, schema = @Schema()) @PathVariable("cafeId") int cafeId) {
        return ResponseEntity.ok(convertCafeToDTO(cafeService.findById(cafeId)));
    }

    @Override
    @PostMapping(value = "/add", produces = { "application/json" }, consumes = { "application/json" })
    public ResponseEntity<CafeDTO> createCafe(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody CafeAddBody body) {
        Cafe newCafe = convertToCafeFromBody(body);
        cafeService.create(newCafe);
        return ResponseEntity.ok(convertCafeToDTO(newCafe));
    }

    @Override
    @PostMapping(value = "/menu/add/{cafeId}/{pizzaId}")
    public ResponseEntity<Void> addToMenu(@Parameter(in = ParameterIn.PATH, description = "cafe id", required=true, schema=@Schema()) @PathVariable("cafeId") int cafeId, @Parameter(in = ParameterIn.PATH, description = "pizza id", required=true, schema=@Schema()) @PathVariable("pizzaId") int pizzaId) {
        return null;
    }

    @Override
    @PostMapping(value = "/menu/remove/{cafeId}/{pizzaId}")
    public ResponseEntity<Void> removeFromMenu(@Parameter(in = ParameterIn.PATH, description = "cafe id", required=true, schema=@Schema()) @PathVariable("cafeId") int cafeId, @Parameter(in = ParameterIn.PATH, description = "pizza id", required=true, schema=@Schema()) @PathVariable("pizzaId") int pizzaId) {
        return null;
    }

    private CafeDTO convertCafeToDTO(Cafe cafe){
        return modelMapper.map(cafe, CafeDTO.class);
    }

    private Cafe convertToCafeFromBody(CafeAddBody addBody){
        return modelMapper.map(addBody, Cafe.class);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(NotFoundException e) {
        ErrorResponse response = new ErrorResponse(
                "Object with this id wasn't found", System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
    }
}
