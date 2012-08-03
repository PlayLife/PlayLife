package com.playlife.presentationLayer.converters;

import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.playlife.utility.exceptions.LogicLayerException;
import com.playlife.utility.exceptions.PersistenceLayerException;
import com.playlife.utility.exceptions.PresentationLayerException;
import com.playlife.utility.exceptions.ValidatorException;

@Component
@Qualifier("errorConverter")
public class JSON_ErrorConverter implements IErrorConverter {
	public JSONObject constructError(Exception exception) throws Exception{
		JSONObject obj_return = new JSONObject();
		String s_displayMessage = "";
		
		ArrayList<PersistenceLayerException> persistenceList = new ArrayList<PersistenceLayerException>();
		ArrayList<LogicLayerException> logicList = new ArrayList<LogicLayerException>();
		ArrayList<PresentationLayerException> presentationList = new ArrayList<PresentationLayerException>();
		ArrayList<ValidatorException> validatorList = new ArrayList<ValidatorException>();
		ArrayList<Exception> exceptionList = new ArrayList<Exception>();
		
		while (exception != null){
			if (exception instanceof PersistenceLayerException){
				persistenceList.add((PersistenceLayerException) exception);
				exception = ((PersistenceLayerException) exception).getException();
			} else if (exception instanceof LogicLayerException) {
				logicList.add((LogicLayerException) exception);
				exception = ((LogicLayerException) exception).getException();
			} else if (exception instanceof ValidatorException) {
				validatorList.add((ValidatorException) exception);
				exception = ((ValidatorException) exception).getException();
			} else if (exception instanceof PresentationLayerException) {
				presentationList.add((PresentationLayerException) exception);
				exception = ((PresentationLayerException) exception).getException();
			} else { 
				exceptionList.add((Exception) exception);
				exception = null;
			}
		}
		
		
		JSONArray arr_validator = new JSONArray();
		for (ValidatorException validatorException : validatorList){
			if (s_displayMessage.isEmpty() && validatorList.get(validatorList.size()-1).equals(validatorException))
				s_displayMessage = validatorException.getErrorMessage();
			JSONObject obj_validator = new JSONObject(); 
			obj_validator.put("errorCode", validatorException.getErrorCode());
			obj_validator.put("msg", validatorException.getErrorMessage());
			arr_validator.add(obj_validator);
		}
		
		JSONArray arr_presentation = new JSONArray();
		for (PresentationLayerException presentationException : presentationList){
			if (s_displayMessage.isEmpty() && presentationList.get(presentationList.size()-1).equals(presentationException))
				s_displayMessage = presentationException.getErrorMessage();
			JSONObject obj_presentation = new JSONObject(); 
			obj_presentation.put("errorCode", presentationException.getErrorCode());
			obj_presentation.put("msg", presentationException.getErrorMessage());
			arr_presentation.add(obj_presentation);
		}
		
		JSONArray arr_logic = new JSONArray();
		for (LogicLayerException logicException : logicList){
			if (s_displayMessage.isEmpty() && logicList.get(logicList.size()-1).equals(logicException))
				s_displayMessage = logicException.getErrorMessage();
			JSONObject obj_logic = new JSONObject(); 
			obj_logic.put("errorCode", logicException.getErrorCode());
			obj_logic.put("msg", logicException.getErrorMessage());
			arr_logic.add(obj_logic);
		}
		
		JSONArray arr_persistence = new JSONArray();
		for (PersistenceLayerException persistenceException : persistenceList){
			if (s_displayMessage.isEmpty() && persistenceList.get(persistenceList.size()-1).equals(persistenceException))
				s_displayMessage = persistenceException.getErrorMessage();
			JSONObject obj_persistence = new JSONObject(); 
			obj_persistence.put("errorCode", persistenceException.getErrorCode());
			obj_persistence.put("msg", persistenceException.getErrorMessage());
			arr_persistence.add(obj_persistence);
		}
		
		JSONArray arr_exception = new JSONArray();
		for (Exception _exception : exceptionList){
			if (s_displayMessage.isEmpty())
				s_displayMessage = _exception.getStackTrace().toString();
			JSONObject obj_validator = new JSONObject(); 
			obj_validator.put("name", _exception.toString());
			obj_validator.put("msg", _exception.getMessage());
			obj_validator.put("trace", _exception.getStackTrace());
			arr_exception.add(obj_validator);
		}
				
		obj_return.put("persistence", arr_persistence);
		obj_return.put("logic", arr_logic);
		obj_return.put("presentation", arr_presentation);
		obj_return.put("validator", arr_validator);
		obj_return.put("exception", arr_exception);
		obj_return.put("displayMessage", s_displayMessage);
		return obj_return;
	}
}
