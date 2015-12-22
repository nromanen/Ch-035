package com.crsms.service.hibernate.query;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.crsms.domain.Resource;

public class ResourceQueryCustomizer {
	
	private String type;

	public Criteria customize(Criteria criteria) {
		if (type != null && !type.isEmpty()) {
			criteria.add(Restrictions.eq("type", Resource.Type.valueOf(type)));
		}
		return criteria;
	}
	
	public String getType() {
		return type;
	}

	public ResourceQueryCustomizer setType(String type) {
		this.type = type;
		return this;
	}
	
}
