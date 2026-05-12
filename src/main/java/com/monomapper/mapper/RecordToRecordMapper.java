package com.monomapper.mapper;

import com.monomapper.api.IMonoMapper;

public class RecordToRecordMapper implements IMonoMapper {

  @Override
  public Object map(Object source, Class<?> targetType) {
    System.out.println("RecordToRecordMapper");

    return "RecordToRecordMapper";
  }
}
