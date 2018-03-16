package com.gzycdjk.commons.pojo;

import java.util.List;

import com.gzycdjk.identity.domain.Permission;

import lombok.Getter;
import lombok.Setter;
@Getter@Setter
public class Menu {
	private List<Permission> nodes;
	
	private Permission parent;
}
