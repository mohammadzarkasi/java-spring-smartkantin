package id.zar.smartkantin.security;

import java.util.Collection;
import java.util.UUID;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

public class CustomUsernamePasswordAuthToken extends UsernamePasswordAuthenticationToken{

	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
	
	private UUID userId;

	public CustomUsernamePasswordAuthToken(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities, UUID userId) {
		super(principal, credentials, authorities);
		// TODO Auto-generated constructor stub
		this.userId = userId;
	}

	public UUID getUserId() {
		return userId;
	}

//	public void setUserId(UUID userId) {
//		this.userId = userId;
//	}

	

}
