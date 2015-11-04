package com.crsms.service;

import java.util.List;

public interface DtoService {
	
	<D, S> D getDto(S source, Class<D> destinationType, Class<S> sourceType);
	
	<D, S> List<D> getDtos(List<S> sources, Class<D> destinationType, Class<S> sourceType);
	
}
