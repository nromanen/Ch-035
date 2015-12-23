package com.crsms.service.hibernate.query;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.crsms.domain.Resource;
import com.crsms.util.StringUtil;

public class ResourceQueryCustomizer {
	
	private String type;
	private String search;

	public Criteria customize(Criteria criteria) {
		if (!StringUtil.isEmptyOrNull(type)) {
			criteria.add(Restrictions.eq("type", Resource.Type.valueOf(type)));
		}
		if (!StringUtil.isEmptyOrNull(search)) {
			search = StringUtil.trimTrailingSpaces(search);
			criteria.add(Restrictions.or(
					Restrictions.ilike("name", search, MatchMode.ANYWHERE), 
					Restrictions.ilike("path", search, MatchMode.ANYWHERE)));
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

	public String getSearch() {
		return search;
	}

	public ResourceQueryCustomizer setSearch(String search) {
		this.search = search;
		return this;
	}
	
}
