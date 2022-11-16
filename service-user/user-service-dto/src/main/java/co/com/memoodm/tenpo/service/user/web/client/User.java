package co.com.memoodm.tenpo.service.user.web.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable
{
	
	private Long id;
	private String name;
	private String email;
	private String password;
	private String created;

	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode objectNode = mapper.createObjectNode()
				.put("name",this.name)
				.put("email",this.email)
				.put("password","********")
				.put("created",this.created)
				.put("password","********")
				.put("password","********");
		return objectNode.toString();
	}
}
