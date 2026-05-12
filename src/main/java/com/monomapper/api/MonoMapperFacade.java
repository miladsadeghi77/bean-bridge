package com.monomapper.api;

import com.monomapper.model.MappingType;
import com.monomapper.startegy.MonoMapperResolver;
import com.monomapper.startegy.MonoMapperStrategyFactory;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;


public class MonoMapperFacade {
  @SuppressWarnings("unchecked")
  public static <T> T map(Object source, Class<T> targetType) {

    MappingType mappingType = MonoMapperResolver.resolve(source.getClass(), targetType);

    IMonoMapper monoMapper = MonoMapperStrategyFactory.getStrategy(mappingType);
    try {
      return (T) monoMapper.map(source, targetType);

    } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
             InvocationTargetException | IntrospectionException e) {
      throw new RuntimeException(e);
    }
  }
}
