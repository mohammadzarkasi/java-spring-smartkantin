package id.zar.smartkantin.tools;

import java.util.UUID;

import org.springframework.security.core.Authentication;

import id.zar.smartkantin.security.CustomUsernamePasswordAuthToken;

public class SessionTools {
	public static UUID getUserIdFromAuth(Authentication auth)
	{
		if(auth instanceof CustomUsernamePasswordAuthToken)
		{
			return ((CustomUsernamePasswordAuthToken) auth).getUserId();
		}
		return null;
	}
}
