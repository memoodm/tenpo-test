package co.com.memoodm.tenpo.service.user.web.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogInRequest
{

    private String email;
    private String password;

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode()
                .put("email",this.email)
                .put("password","********");
        return objectNode.toString();
    }
    
}
