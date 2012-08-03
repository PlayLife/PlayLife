package com.playlife.persistenceLayer.DAO.genericDAO;


import java.lang.annotation.Annotation;
import java.util.ArrayList;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.IntroductionInterceptor;

public class FinderIntroductionInterceptor implements IntroductionInterceptor {

    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
    	Annotation[][] annotationsList = methodInvocation.getMethod().getParameterAnnotations();
    	ArrayList<String> parameterNamesList = new ArrayList<String>();
    	for (Annotation[] annotations : annotationsList){
    		if (annotations.length > 0 && annotations[0] instanceof Param){
        		Param annotation = (Param) annotations[0];
        		parameterNamesList.add(annotation.name());	
    		}
    	}
    	Object[] arguments = methodInvocation.getArguments();
    	
        FinderExecutor<?> executor = (FinderExecutor<?>) methodInvocation.getThis();

        String methodName = methodInvocation.getMethod().getName();
        if(methodName.startsWith("hql_")){
        	if (methodName.substring("hql_".length()).startsWith("find_")){
        		return executor.executeHQLFind(methodInvocation.getMethod(), parameterNamesList.toArray(new String[parameterNamesList.size()]), arguments);
        	} else if (methodName.substring("hql_".length()).startsWith("update_")){
        		executor.executeHQLUpdate(methodInvocation.getMethod(), parameterNamesList.toArray(new String[parameterNamesList.size()]), arguments);
	            return null;
        	} else if (methodName.substring("hql_".length()).startsWith("count_")){
        		return executor.executeHQLCount(methodInvocation.getMethod(), parameterNamesList.toArray(new String[parameterNamesList.size()]), arguments);
        	}
        } else if (methodName.startsWith("sql_")){
        	if (methodName.substring("sql_".length()).startsWith("find_")){
        		return executor.executeSQLFind(methodInvocation.getMethod(), parameterNamesList.toArray(new String[parameterNamesList.size()]), arguments);
        	} else if (methodName.substring("sql_".length()).startsWith("update_")){
        		executor.executeHQLUpdate(methodInvocation.getMethod(), parameterNamesList.toArray(new String[parameterNamesList.size()]), arguments);
	            return null;
        	} else if (methodName.substring("sql_".length()).startsWith("count_")){
        		return executor.executeHQLCount(methodInvocation.getMethod(), parameterNamesList.toArray(new String[parameterNamesList.size()]), arguments);
        	}
        }
        
        return methodInvocation.proceed();
    }

    @SuppressWarnings("rawtypes")
	public boolean implementsInterface(Class intf) {
        return intf.isInterface() && FinderExecutor.class.isAssignableFrom(intf);
    }
}
