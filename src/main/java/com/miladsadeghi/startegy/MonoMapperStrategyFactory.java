package com.miladsadeghi.startegy;

import com.miladsadeghi.api.IMonoMapper;
import com.miladsadeghi.mapper.BeanToBeanMapper;
import com.miladsadeghi.mapper.BeanToRecordMapper;
import com.miladsadeghi.mapper.RecordToBeanMapper;
import com.miladsadeghi.mapper.RecordToRecordMapper;
import com.miladsadeghi.model.MappingType;
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
