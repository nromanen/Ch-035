package com.crsms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.googlecode.jmapper.JMapper;

@Service("dtoService")
public class DtoServiceImpl implements DtoService {

	@Override
	public <D, S> D convert(S source, Class<D> destinationType, Class<S> sourceType) {
		JMapper<D, S> mapper = new JMapper<>(destinationType, sourceType);
		D destination = mapper.getDestination(source);
		return destination;
	}

	@Override
	public <D, S> List<D> convert(List<S> sources, Class<D> destinationType, Class<S> sourceType) {
		List<D> destinations = new ArrayList<>();
		for (S source : sources) {
			destinations.add(this.convert(source, destinationType, sourceType));
		}
		return destinations;
	}	

}
