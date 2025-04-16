package com.miladsadeghi;

import com.miladsadeghi.model.bean.PersonBean;

import com.miladsadeghi.model.record.PersonRecord;
import org.junit.jupiter.api.Test;

import java.beans.IntrospectionException;

import static org.junit.jupiter.api.Assertions.*;

class BeanBridgeTest {

  @Test
  void testConvertBeanToRecordWithNullValues()
      throws ReflectiveOperationException, IntrospectionException {

    PersonBean person = new PersonBean(null, null, 27);

    var record = BeanBridge.convertBeanToRecord(person, PersonRecord.class);
    assertNull(record.firstname());
    assertNull(record.lastname());
    assertEquals(27, record.age());

  }

  @Test
  void testConvertBeanToRecordWithActualValues()
      throws ReflectiveOperationException, IntrospectionException {

    PersonBean person = new PersonBean("milad", "sadeghi", 27);

    var record = BeanBridge.convertBeanToRecord(person, PersonRecord.class);
    assertEquals("milad", record.firstname());
    assertEquals("sadeghi", record.lastname());
    assertEquals(27, record.age());

  }

  @Test
  void testConvertRecordToBeanWithNullValues()
      throws ReflectiveOperationException, IntrospectionException {
    var personRecord = new PersonRecord(null, null, 27);
    var bean = BeanBridge.convertRecordToBean(personRecord, PersonBean.class);

    assertNull(bean.getFirstname());
    assertNull(bean.getLastname());
    assertEquals(27, bean.getAge());
  }

  @Test
  void testConvertRecordToBeanWithActualValues()
      throws ReflectiveOperationException, IntrospectionException {

    var personRecord = new PersonRecord("milad", "sadeghi", 27);
    var bean = BeanBridge.convertRecordToBean(personRecord, PersonBean.class);

    assertEquals("milad", bean.getFirstname());
    assertEquals("sadeghi", bean.getLastname());
    assertEquals(27, bean.getAge());

  }

}

