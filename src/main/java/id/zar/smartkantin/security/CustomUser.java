package id.zar.smartkantin.security;
import java.util.Collection;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.User;

public class CustomUser extends User{

	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
	
	private UUID userId;

	public UUID getUserId() {
		return userId;
	}

//	public void setUserId(UUID userId) {
//		this.userId = userId;
//	}

	public CustomUser(UUID userId,String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		// TODO Auto-generated constructor stub
		this.userId=userId;
	}
	
	public CustomUser(UUID userId, String username, String password, Collection<? extends GrantedAuthority> authorities) {
		this(userId, username, password, true, true, true, true, authorities);
	}

}
