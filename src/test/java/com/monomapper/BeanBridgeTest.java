package com.monomapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.monomapper.api.MonoMapperFacade;
import com.monomapper.bean.PersonBean;
import com.monomapper.record.PersonRecord;
import org.junit.jupiter.api.Test;

class BeanBridgeTest {

  @Test
  void testMapBeanToRecordWithNullValues(){

    PersonBean person = new PersonBean(null, null, 27);

    var record = MonoMapperFacade.map(person, PersonRecord.class);
    assertNull(record.firstname());
    assertNull(record.lastname());
    assertEquals(27, record.age());

  }

  @Test
  void testMapBeanToRecordWithActualValues(){

    PersonBean person = new PersonBean("milad", "sadeghi", 27);

    var record = MonoMapperFacade.map(person, PersonRecord.class);
    assertEquals("milad", record.firstname());
    assertEquals("sadeghi", record.lastname());
    assertEquals(27, record.age());

  }

  @Test
  void testMapRecordToBeanWithNullValues() {
    var personRecord = new PersonRecord(null, null, 27);
    var bean = MonoMapperFacade.map(personRecord, PersonBean.class);

    assertNull(bean.getFirstname());
    assertNull(bean.getLastname());
    assertEquals(27, bean.getAge());
  }

  @Test
  void testMapRecordToBeanWithActualValues() {

    var personRecord = new PersonRecord("milad", "sadeghi", 27);
    var bean = MonoMapperFacade.map(personRecord, PersonBean.class);

    assertEquals("milad", bean.getFirstname());
    assertEquals("sadeghi", bean.getLastname());
    assertEquals(27, bean.getAge());

  }

}

