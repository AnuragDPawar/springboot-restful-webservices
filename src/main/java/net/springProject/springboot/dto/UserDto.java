package net.springProject.springboot.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Schema(
        description = "UserDTO model information"
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    @NotEmpty(message = "User first name must not be empty")
    private String firstName; //shouldn't be empty
    @NotEmpty(message = "User last name must not be empty")
    private String lastName; //shouldn't be empty
    @Email(message = "User email must not be empty or incorrect")
    @NotEmpty(message = "User email name must not be empty")
    private String email; //shouldn't be empty & email address must be valid

}
