package com.gzycdjk.commons.until;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.gzycdjk.identity.domain.Role;

/**
 * 方法配对
 * @author YCJKmr.zo
 *
 */
public class ObjectComparison {
	/**
	 * 
	 * @param class1  新对象
	 * @param class2  旧对象
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws IntrospectionException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	public static  void  comparison (Object New,Object old) throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IntrospectionException, IllegalArgumentException, InvocationTargetException{
		Class<?> class3 = New.getClass();
		Class<?> class4 = old.getClass();
		
		Field[] a = class3.getDeclaredFields();
		for (Field field : a) {
			// 获取成员变量的姓名
			String name = field.getName();
			PropertyDescriptor pd = new PropertyDescriptor(name, class3);
			
			PropertyDescriptor pd2 = new PropertyDescriptor(name, class4);
			Method writeMethod = pd.getWriteMethod();
			
			Method method = pd.getReadMethod();
			Method method2 = pd2.getReadMethod();
			
			Object invoke = method.invoke(New);
			Object invoke2 = method2.invoke(old);
			if (invoke2 ==null) {
				continue;
			}
			if (!invoke2.equals(invoke)) {
				writeMethod.invoke(New, invoke2);
			}
		}
	}
	public static void main(String[] args) {
		Role role = new Role();
		role.setName("998");
		
		Role role2 = new Role();
		try {
			ObjectComparison.comparison(role2, role);
			System.out.println("进来了");
		}
		catch (Exception e) {
		}
		
		System.out.println(role2.getName());
	}
}
