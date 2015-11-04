package com.crsms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.googlecode.jmapper.JMapper;

@Service("dtoService")
public class DtoServiceImpl implements DtoService {

	@Override
	public <D, S> D getDto(S source, Class<D> destinationType, Class<S> sourceType) {
		JMapper<D, S> mapper = new JMapper<>(destinationType, sourceType);
		D dto = mapper.getDestination(source);
		return dto;
	}

	@Override
	public <D, S> List<D> getDtos(List<S> sources, Class<D> destinationType, Class<S> sourceType) {
		List<D> dtos = new ArrayList<>();
		for (S source : sources) {
			dtos.add(this.getDto(source, destinationType, sourceType));
		}
		return dtos;
	}	

}
