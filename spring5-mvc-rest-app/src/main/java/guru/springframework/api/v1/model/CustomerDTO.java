package guru.springframework.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    @ApiModelProperty(value = "This is the first name", required = true)
    private String firstName;

    @ApiModelProperty(required = true )
    private String lastName;

    @JsonProperty("customer_url")//without this annotation, the name of the attribute is customerUrl. This property was added to reflect perfectly the result of the original service
    private String customerUrl;

}