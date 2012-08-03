package com.playlife.persistenceLayer.DAO.genericDAO;

import org.hibernate.type.Type;

public interface FinderArgumentTypeFactory{
    Type getArgumentType(Object arg);
}