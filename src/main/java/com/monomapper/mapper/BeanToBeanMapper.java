package com.monomapper.mapper;

import com.monomapper.api.IMonoMapper;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class BeanToBeanMapper implements IMonoMapper {

  @Override
  public Object map(Object source, Class<?> targetType)
      throws IntrospectionException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

    BeanInfo beanInfoSource = Introspector.getBeanInfo(source.getClass(), Object.class);

    Constructor<?> constructor = targetType.getConstructor();
    Object bean = constructor.newInstance();
    BeanInfo beanInfoTarget = Introspector.getBeanInfo(targetType);

    nextBeanItem:
    for (PropertyDescriptor propertyDescriptor : beanInfoSource.getPropertyDescriptors()) {
      String propertyName = propertyDescriptor.getName();
      Object value = propertyDescriptor.getReadMethod().invoke(source);
      for (PropertyDescriptor targetDescriptor : beanInfoTarget.getPropertyDescriptors()) {
        if (propertyName.equals(targetDescriptor.getName())) {
          targetDescriptor.getWriteMethod().invoke(bean, value);
          continue nextBeanItem;
        }
      }
    }
    return bean;
  }
}
