package com.playlife.presentation.converters;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.playlife.persistence.DAO.genericDAO.GsonExclude;
import com.playlife.utility.exceptions.PresentationException;

@Component
@Qualifier("objectConverter")
public class JSON_ObjectConverter implements IObjectConverter{
	@Override
	public JSON constructObject(Object object) throws PresentationException {
		try {
			Gson gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy(){
				@Override
				public boolean shouldSkipClass(Class<?> clazz) {
					return false;
				}
	
				@Override
				public boolean shouldSkipField(FieldAttributes f) {
					return (f.getAnnotation(OneToMany.class) != null || f.getAnnotation(ManyToMany.class) != null || f.getAnnotation(ManyToOne.class) != null || (f.getAnnotation(OneToOne.class) != null && f.getAnnotation(JoinColumn.class) == null) || f.getAnnotation(GsonExclude.class) != null);
				}
				
			}).create();
			return JSONSerializer.toJSON(gson.toJson(object));
		} catch (Exception ex){
			throw new PresentationException(-9999, ex);
		}
	}
}
