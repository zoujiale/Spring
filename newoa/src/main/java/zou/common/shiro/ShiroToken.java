package zou.common.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class ShiroToken extends UsernamePasswordToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = -616244771169805394L;

	public ShiroToken(String username ,String pwsd) {
		super(username,pwsd);
		this.pwsd = pwsd;
	}

	private String pwsd;
}
