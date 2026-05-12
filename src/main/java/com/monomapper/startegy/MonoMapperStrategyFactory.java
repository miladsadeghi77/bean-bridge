package com.monomapper.startegy;

import com.monomapper.api.IMonoMapper;
import com.monomapper.mapper.BeanToBeanMapper;
import com.monomapper.mapper.BeanToRecordMapper;
import com.monomapper.mapper.RecordToBeanMapper;
import com.monomapper.mapper.RecordToRecordMapper;
import com.monomapper.model.MappingType;
import java.util.EnumMap;
import java.util.Map;

public class MonoMapperStrategyFactory {

  private static final Map<MappingType, IMonoMapper> strategies = new EnumMap<>(MappingType.class);

  static {
    strategies.put(MappingType.BEAN_TO_BEAN, new BeanToBeanMapper());
    strategies.put(MappingType.BEAN_TO_RECORD, new BeanToRecordMapper());
    strategies.put(MappingType.RECORD_TO_BEAN, new RecordToBeanMapper());
    strategies.put(MappingType.RECORD_TO_RECORD, new RecordToRecordMapper());
  }

  public static IMonoMapper getStrategy(MappingType mappingType) {
    return strategies.get(mappingType);
  }

}
