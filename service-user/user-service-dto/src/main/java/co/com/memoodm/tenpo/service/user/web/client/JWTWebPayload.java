package co.com.memoodm.tenpo.service.user.web.client;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JWTWebPayload
{
	private Long userId;
	private String userName;
	private String userEmail;
}
