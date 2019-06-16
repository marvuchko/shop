package com.marko.shop.api.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

public class DataConverter {

	private DataConverter() {
		super();
	}
	
	public static Object convert(Object source, Class<?> target) {
		if(source == null || target == null)
			return null;
		return new ModelMapper().map(source, target);
	}

	public static List<?> convertToList(List<?> source, Class<?> target) {
		if(source == null || target == null)
			return null;
		return source.stream()
				.map(element -> DataConverter.convert(element, target)).collect(Collectors.toList());
	}

	public static Page<?> convertToPage(Page<?> source, Class<?> target) {
		if(source == null || target == null)
			return null;
		return source.map(element -> DataConverter.convert(element, target));
	}
}
