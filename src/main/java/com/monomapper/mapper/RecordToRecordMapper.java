package com.monomapper.mapper;

import com.monomapper.api.IMonoMapper;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.RecordComponent;
import java.util.Arrays;

public class RecordToRecordMapper implements IMonoMapper {

  @Override
  public Object map(Object source, Class<?> targetType)
      throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {

    RecordComponent[] recordComponents = targetType.getRecordComponents();
    Class<?>[] recordComponentTypes = Arrays.stream(recordComponents)
        .map(RecordComponent::getType)
        .toArray(Class[]::new);
    Object[] recordComponentValues = new Object[recordComponents.length];

    nextRecordComponent:
    for (int i = 0; i < recordComponents.length; i++) {
      RecordComponent recordComponent = recordComponents[i];
      String propertyName = recordComponent.getName();

      for (RecordComponent component : targetType.getRecordComponents()) {
        if (component.getName().equals(propertyName)) {

          recordComponentValues[i] = component.getAccessor().invoke(source);
          continue nextRecordComponent;
        }
      }

      throw new IllegalArgumentException("Could not find property: " + propertyName);
    }

    Constructor<?> constructor = targetType.getConstructor(recordComponentTypes);
    return constructor.newInstance(recordComponentValues);
  }
}
