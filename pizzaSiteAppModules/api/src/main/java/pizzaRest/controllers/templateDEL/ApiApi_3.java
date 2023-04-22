//
//package pizzaRest.controllers.templateDEL;
//
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
//import io.swagger.v3.oas.annotations.enums.ParameterIn;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.security.SecurityRequirement;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import javax.validation.Valid;
//
//@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-04-19T15:53:45.783885733Z[GMT]")
//@Validated
//public interface ApiApi_3 {
//
//
//    @Operation(summary = "Change ingredient (Admin only)", description = "Change ingredient", security = {
//        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Admin" })
//    @ApiResponses(value = {
//        @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Ingredient.class))),
//
//        @ApiResponse(responseCode = "403", description = "Access denied", content = @Content(mediaType = "application/json", schema = @Schema(implementation = InlineResponse403.class))),
//
//        @ApiResponse(responseCode = "404", description = "Incorrect data", content = @Content(mediaType = "application/json", schema = @Schema(implementation = InlineResponse404.class))) })
//    @RequestMapping(value = "/api/admin/ingredient/change/{id}",
//        produces = { "application/json", "application/json" },
//        consumes = { "application/json" },
//        method = RequestMethod.POST)
//    ResponseEntity<Ingredient> changeIngredient(@Parameter(in = ParameterIn.PATH, description = "record id", required=true, schema=@Schema()) @PathVariable("id") Integer id, @Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody BodyIngredient body);
//
//
//    @Operation(summary = "Change pizza (Admin only)", description = "Change pizza", security = {
//        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Admin" })
//    @ApiResponses(value = {
//        @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pizza.class))),
//
//        @ApiResponse(responseCode = "403", description = "Access denied", content = @Content(mediaType = "application/json", schema = @Schema(implementation = InlineResponse403.class))),
//
//        @ApiResponse(responseCode = "404", description = "Incorrect data", content = @Content(mediaType = "application/json", schema = @Schema(implementation = InlineResponse404.class))) })
//    @RequestMapping(value = "/api/admin/pizza/change/{pizzaId}",
//        produces = { "application/json", "application/json" },
//        consumes = { "application/json" },
//        method = RequestMethod.POST)
//    ResponseEntity<Pizza> changePizza(@Parameter(in = ParameterIn.PATH, description = "pizza id", required=true, schema=@Schema()) @PathVariable("pizzaId") Integer pizzaId, @Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody BodyPizza body);
//
//
//
//
//
//
//}
//
