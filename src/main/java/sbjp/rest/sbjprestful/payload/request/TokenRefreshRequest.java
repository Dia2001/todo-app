package sbjp.rest.sbjprestful.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class TokenRefreshRequest {
	
	private String refreshToken;

}
