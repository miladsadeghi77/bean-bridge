package com.miladsadeghi.startegy;

import com.miladsadeghi.model.MappingType;

public class MonoMapperResolver {

  public static MappingType resolve(Class<?> sourceType, Class<?> targetType) {

    boolean sourceIsRecord = sourceType.isRecord();
    boolean targetIsRecord = targetType.isRecord();

    if (!sourceIsRecord && !targetIsRecord) {
      return MappingType.BEAN_TO_BEAN;
    } else if (!sourceIsRecord) {
      return MappingType.BEAN_TO_RECORD;
    } else if (!targetIsRecord) {
      return MappingType.RECORD_TO_BEAN;
    } else {
      return MappingType.RECORD_TO_RECORD;
    }
  }

}
