package pizzaRest.dto;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
//import javax.validation.Valid;
import javax.validation.Valid;
import javax.validation.constraints.Min;
//import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * BodyBase
 */


@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-04-20T17:23:29.477025709Z[GMT]")
public class BodyBaseS {
    @NotBlank(message = "Should be not empty")
    @JsonProperty(value = "name", required = true)
    @Valid
    private String name;

    /**
     * Gets or Sets size
     */



    @JsonProperty("size")
    @NotEmpty
    @Valid
    private String size;

    @JsonProperty("price")
    @Min(4)
    @Valid
    private double price;

    public BodyBaseS name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get name
     *
     * @return name
     **/
    @Schema(example = "Thin crust", description = "")

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BodyBaseS size(String size) {
        this.size = size;
        return this;
    }

    /**
     * Get size
     *
     * @return size
     **/
    @Schema(description = "")
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public BodyBaseS price(double price) {
        this.price = price;
        return this;
    }

    /**
     * Get price
     *
     * @return price
     **/
    @Schema(example = "3.50", description = "")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BodyBaseS bodyBaseS = (BodyBaseS) o;
        return Objects.equals(this.name, bodyBaseS.name) &&
                Objects.equals(this.size, bodyBaseS.size) &&
                Objects.equals(this.price, bodyBaseS.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size, price);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class BodyBase {\n");

        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    size: ").append(toIndentedString(size)).append("\n");
        sb.append("    price: ").append(toIndentedString(price)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
