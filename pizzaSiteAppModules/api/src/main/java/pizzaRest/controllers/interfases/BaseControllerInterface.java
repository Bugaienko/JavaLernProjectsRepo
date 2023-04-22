package pizzaRest.controllers.interfases;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pizzaRest.dto.BaseDTO;
import pizzaRest.dto.responsesModel.AcssessDeneidedResponse403;
import pizzaRest.dto.responsesModel.BadDataResponse404;

import java.util.List;

/**
 * @author Sergii Bugaienko
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-04-16T14:31:40.830807727Z[GMT]")
@Validated
public interface BaseControllerInterface {

    @Operation(summary = "Get all bases", description = "", security = {
            @SecurityRequirement(name = "bearerAuth")}, tags = {"Bases"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = BaseDTO.class)))),

            @ApiResponse(responseCode = "403", description = "Access denied", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AcssessDeneidedResponse403.class)))})
    @GetMapping(value = "/api/base/all", produces = {"application/json", "application/json"})
    ResponseEntity<List<BaseDTO>> getAllBases();


    @Operation(summary = "Get base by id", description = "Get base", security = {
            @SecurityRequirement(name = "bearerAuth")}, tags = {"Bases"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BaseDTO.class))),

            @ApiResponse(responseCode = "403", description = "Access denied", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AcssessDeneidedResponse403.class))),

            @ApiResponse(responseCode = "404", description = "Incorrect data", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadDataResponse404.class)))})
    @GetMapping(value = "/api/base/{id}", produces = {"application/json", "application/json"})
    ResponseEntity<BaseDTO> getOneBase(@Parameter(in = ParameterIn.PATH, description = "base id", required = true, schema = @Schema()) @PathVariable("id") int id);


}
