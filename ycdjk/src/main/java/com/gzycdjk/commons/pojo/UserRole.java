package com.gzycdjk.commons.pojo;

import com.gzycdjk.identity.domain.User;

import lombok.Getter;
import lombok.Setter;

/**
 * 为了方便JSon转换
 * @author YCJKmr.zo
 *
 */
@Getter@Setter
public class UserRole {
	private User user;
	private String roleId;
	
}
