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
import org.springframework.web.bind.annotation.*;
import pizzaRest.dto.CafeAddBody;
import pizzaRest.dto.CafeDTO;

import javax.validation.Valid;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-04-16T14:31:40.830807727Z[GMT]")
@Validated
public interface CafeControllerInt {


    @Operation(summary = "Get all cafes", description = "", security = {
            @SecurityRequirement(name = "bearerAuth")}, tags = {"Cafe"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CafeDTO.class)))),

            @ApiResponse(responseCode = "401", description = "Access denied")})
    @GetMapping(value = "/api/cafe/all",
            produces = {"application/json"})
    ResponseEntity<List<CafeDTO>> getAllCafes();


    @Operation(summary = "Get cafe", description = "Get one by id", security = {
            @SecurityRequirement(name = "bearerAuth")}, tags = {"Cafe"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CafeDTO.class))),

            @ApiResponse(responseCode = "401", description = "Access denied"),

            @ApiResponse(responseCode = "404", description = "Request failed - No items")})
    @GetMapping(value = "/api/cafe/{cafeId}",
            produces = {"application/json"})
    ResponseEntity<CafeDTO> getCafe(@Parameter(in = ParameterIn.PATH, description = "cafe id", required = true, schema = @Schema()) @PathVariable("cafeId") int cafeId);


    @Operation(summary = "Create new Cafe", description = "create new cafe", security = {
            @SecurityRequirement(name = "bearerAuth")    }, tags={ "Cafe" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CafeDTO.class))),

            @ApiResponse(responseCode = "401", description = "Access denied"),

            @ApiResponse(responseCode = "404", description = "Request failed - No items") })
    @PostMapping(value = "/api/cafe/add",
            produces = { "application/json" },
            consumes = { "application/json" })
    ResponseEntity<CafeDTO> createCafe(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody CafeAddBody body);



    @Operation(summary = "Add pizza to menu", description = "Add pizza to cafes menu", security = {
            @SecurityRequirement(name = "bearerAuth")    }, tags={ "Cafe" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),

            @ApiResponse(responseCode = "401", description = "Access denied"),

            @ApiResponse(responseCode = "406", description = "Bad request parametrs") })
    @PostMapping(value = "/api/cafe/menu/add/{cafeId}/{pizzaId}")
    ResponseEntity<Void> addToMenu(@Parameter(in = ParameterIn.PATH, description = "cafe id", required=true, schema=@Schema()) @PathVariable("cafeId") int cafeId, @Parameter(in = ParameterIn.PATH, description = "pizza id", required=true, schema=@Schema()) @PathVariable("pizzaId") int pizzaId);



    @Operation(summary = "Remove pizza from menu", description = "Remove pizza from cafes menu", security = {
            @SecurityRequirement(name = "bearerAuth")    }, tags={ "Cafe" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),

            @ApiResponse(responseCode = "401", description = "Access denied"),

            @ApiResponse(responseCode = "404", description = "Bad request parametr") })
    @PostMapping(value = "/api/cafe/menu/remove/{cafeId}/{pizzaId}")
    ResponseEntity<Void> removeFromMenu(@Parameter(in = ParameterIn.PATH, description = "cafe id", required=true, schema=@Schema()) @PathVariable("cafeId") int cafeId, @Parameter(in = ParameterIn.PATH, description = "pizza id", required=true, schema=@Schema()) @PathVariable("pizzaId") int pizzaId);

}
