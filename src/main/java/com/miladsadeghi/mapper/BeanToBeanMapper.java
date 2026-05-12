package com.miladsadeghi.mapper;

import com.miladsadeghi.api.IMonoMapper;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

public class BeanToBeanMapper implements IMonoMapper {

  @Override
  public Object map(Object source, Class<?> targetType)
      throws IntrospectionException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    System.out.println("BeanToBeanMapper");

    return "BeanToBeanMapper";
  }
}
