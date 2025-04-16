
package com.miladsadeghi;


import java.beans.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.RecordComponent;
import java.util.Arrays;

public class BeanBridge {

  public static <T, R extends Record> R convertBeanToRecord(T bean, Class<R> recordType)
      throws ReflectiveOperationException, IntrospectionException {

    //constructor of the record - canonical constructor
    RecordComponent[] recordComponents = recordType.getRecordComponents();
    Class<?>[] recordComponentTypes = Arrays.stream(recordComponents)
        .map(RecordComponent::getType)
        .toArray(Class[]::new);
    Object[] recordComponentValues = new Object[recordComponents.length];
    BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());

    nextRecordComponent:
    for (int i = 0; i < recordComponents.length; i++) {
      RecordComponent recordComponent = recordComponents[i];
      String propertyName = recordComponent.getName();

      for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {

        String propertyDescriptorName = propertyDescriptor.getName();

        if (propertyDescriptorName.equals(propertyName)) {

          recordComponentValues[i] = propertyDescriptor.getReadMethod().invoke(bean);
          continue nextRecordComponent;
        }
      }

      throw new IllegalArgumentException("Could not find property: " + propertyName);
    }

    Constructor<R> constructor = recordType.getConstructor(recordComponentTypes);
    R record = constructor.newInstance(recordComponentValues);

    return record;
  }

  public static <T extends Record, R> R convertRecordToBean(T record, Class<R> beanType)
      throws ReflectiveOperationException, IntrospectionException {

    //constructor of the record - canonical constructor
    Constructor<R> constructor = beanType.getConstructor();
    R bean = constructor.newInstance();

    BeanInfo beanInfo = Introspector.getBeanInfo(beanType);
    nextRecordComponent:
    for (RecordComponent recordComponent : record.getClass().getRecordComponents()) {
      Object value = recordComponent.getAccessor().invoke(record);
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
