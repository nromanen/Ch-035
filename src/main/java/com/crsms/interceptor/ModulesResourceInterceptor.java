package com.crsms.interceptor;

import java.io.Serializable;
import java.util.Iterator;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import com.crsms.domain.Module;

public class ModulesResourceInterceptor extends EmptyInterceptor  {
	public void onDelete(Object entity,
            Serializable id,
            Object[] state,
            String[] propertyNames,
            Type[] types) {
		if ( entity instanceof Module ) {
			System.out.println("Update Operation onDelete");
		}
	}
	
	// This method is called when Employee object gets updated.
	public boolean onFlushDirty(Object entity,
					Serializable id,
					Object[] currentState,
					Object[] previousState,
					String[] propertyNames,
					Type[] types) {
		if ( entity instanceof Module ) {
			System.out.println("Update Operation onFlushDirty");
			return true; 
		}
		return false;
	}
	
		//called before commit into database
	public void preFlush(Iterator iterator) {
		System.out.println("preFlush");
	}
	   //called after committed into database
	public void postFlush(Iterator iterator) {
		System.out.println("postFlush");
	}
}
