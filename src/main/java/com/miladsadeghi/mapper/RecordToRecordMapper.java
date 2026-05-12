package com.miladsadeghi.mapper;

import com.miladsadeghi.api.IMonoMapper;

public class RecordToRecordMapper implements IMonoMapper {

  @Override
  public Object map(Object source, Class<?> targetType) {
    System.out.println("RecordToRecordMapper");

    return "RecordToRecordMapper";
  }
}
