package com.monomapper.mapper;

import com.monomapper.api.IMonoMapper;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.RecordComponent;
import java.util.Arrays;

public class BeanToRecordMapper implements IMonoMapper{

  @Override
  public Object map(Object source, Class<?> targetType)
      throws IntrospectionException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {

    System.out.println("BeanToRecordMapper");

    //constructor of the record - canonical constructor
    RecordComponent[] recordComponents = targetType.getRecordComponents();
    Class<?>[] recordComponentTypes = Arrays.stream(recordComponents)
        .map(RecordComponent::getType)
        .toArray(Class[]::new);
    Object[] recordComponentValues = new Object[recordComponents.length];
    BeanInfo beanInfo = Introspector.getBeanInfo(source.getClass());

    nextRecordComponent:
    for (int i = 0; i < recordComponents.length; i++) {
      RecordComponent recordComponent = recordComponents[i];
      String propertyName = recordComponent.getName();

      for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {

        String propertyDescriptorName = propertyDescriptor.getName();

        if (propertyDescriptorName.equals(propertyName)) {

          recordComponentValues[i] = propertyDescriptor.getReadMethod().invoke(source);
          continue nextRecordComponent;
        }
      }

      throw new IllegalArgumentException("Could not find property: " + propertyName);
    }

    Constructor<?> constructor = targetType.getConstructor(recordComponentTypes);

    return constructor.newInstance(recordComponentValues);
  }
}
