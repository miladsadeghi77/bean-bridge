package com.miladsadeghi.mapper;

import com.miladsadeghi.api.IMonoMapper;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.RecordComponent;

public class RecordToBeanMapper implements IMonoMapper {

  @Override
  public Object map(Object source, Class<?> targetType)
      throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, IntrospectionException {
    System.out.println("RecordToBeanMapper");

    //constructor of the record - canonical constructor
    Constructor<?> constructor = targetType.getConstructor();
     Object bean = constructor.newInstance();

    BeanInfo beanInfo = Introspector.getBeanInfo(targetType);
    nextRecordComponent:
    for (RecordComponent recordComponent : source.getClass().getRecordComponents()) {
      Object value = recordComponent.getAccessor().invoke(source);
      String name = recordComponent.getName();
      for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
        if (name.equals(propertyDescriptor.getName())) {
          propertyDescriptor.getWriteMethod().invoke(bean, value);
          continue nextRecordComponent;
        }
      }

      throw new IllegalArgumentException("Could not find property: " + name);
    }
    return bean;
  }
}
