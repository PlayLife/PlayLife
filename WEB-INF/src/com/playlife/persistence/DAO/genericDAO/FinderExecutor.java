package com.playlife.persistence.DAO.genericDAO;

import java.lang.reflect.Method;
import java.util.List;

public interface FinderExecutor<T>{
    List<T> executeHQLFind(Method method, String[] nameArgs, Object[] queryArgs);
    void executeHQLUpdate(Method method, String[] nameArgs, Object[] queryArgs);
    Number executeHQLCount(Method method, String[] nameArgs, Object[] queryArgs);
    List<T> executeSQLFind(Method method, String[] nameArgs, Object[] queryArgs);
    void executeSQLUpdate(Method method, String[] nameArgs, Object[] queryArgs);
    Number executeSQLCount(Method method, String[] nameArgs, Object[] queryArgs);
}
