package services;

import models.entities.Record;

import java.util.List;

/**
 * Created by Paddy on 25/06/2016.
 */
public interface RecordService {
    public Record createNewRecord(Record record);
    public List<Record> findAllRecords();
}
