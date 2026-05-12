package com.miladsadeghi.api;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

public interface IMonoMapper {
  Object map(Object source,Class<?> targetType)
      throws NoSuchMethodException, IntrospectionException, InvocationTargetException, IllegalAccessException, InstantiationException;
}
