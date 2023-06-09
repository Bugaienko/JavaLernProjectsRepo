///**
// * NOTE: This class is auto generated by the swagger code generator program (3.0.42).
// * https://github.com/swagger-api/swagger-codegen
// * Do not edit the class manually.
// */
//package pizzaRest.controllers.templateDEL;
//
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
//import io.swagger.v3.oas.annotations.enums.ParameterIn;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.media.ArraySchema;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.security.SecurityRequirement;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestPart;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.bind.annotation.CookieValue;
//
//import javax.validation.Valid;
//import javax.validation.constraints.*;
//import java.util.List;
//import java.util.Map;
//
//@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-04-18T20:07:26.808629200Z[GMT]")
//@Validated
//public interface ApiApi {
//
//    @Operation(summary = "Add pizza to menu", description = "Add pizza to cafes menu", security = {
//        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Cafe" })
//    @ApiResponses(value = {
//        @ApiResponse(responseCode = "200", description = "OK"),
//
//        @ApiResponse(responseCode = "401", description = "Access denied"),
//
//        @ApiResponse(responseCode = "404", description = "Bad request parametr") })
//    @RequestMapping(value = "/api/cafe/menu/add/{cafeId}/{pizzaId}",
//        method = RequestMethod.POST)
//    ResponseEntity<Void> addToMenu(@Parameter(in = ParameterIn.PATH, description = "cafe id", required=true, schema=@Schema()) @PathVariable("cafeId") Integer cafeId, @Parameter(in = ParameterIn.PATH, description = "pizza id", required=true, schema=@Schema()) @PathVariable("pizzaId") Integer pizzaId);
//
//
//
//
//    @Operation(summary = "Create new Cafe", description = "create new cafe", security = {
//        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Cafe" })
//    @ApiResponses(value = {
//        @ApiResponse(responseCode = "200", description = "Ok", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cafe.class))),
//
//        @ApiResponse(responseCode = "401", description = "Access denied"),
//
//        @ApiResponse(responseCode = "404", description = "Request failed - No items") })
//    @RequestMapping(value = "/api/cafe/add",
//        produces = { "application/json" },
//        consumes = { "application/json" },
//        method = RequestMethod.POST)
//    ResponseEntity<Cafe> createCafe(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody CafeAddBody body);
//
//
//
//    @Operation(summary = "Get ingredient", description = "Get one by id", security = {
//        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Ingredients" })
//    @ApiResponses(value = {
//        @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Ingredient.class))),
//
//        @ApiResponse(responseCode = "401", description = "Access denied"),
//
//        @ApiResponse(responseCode = "404", description = "Request failed - No items") })
//    @RequestMapping(value = "/api/ingredients/{id}",
//        produces = { "application/json" },
//        method = RequestMethod.GET)
//    ResponseEntity<Ingredient> getIngredient(@Parameter(in = ParameterIn.PATH, description = "record id", required=true, schema=@Schema()) @PathVariable("id") Integer id);
//
//
//
//
//}
//
