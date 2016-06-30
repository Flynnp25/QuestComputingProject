package services.impl;

import models.entities.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.RecordRepository;
import services.RecordService;
import services.exceptions.CustomGenericException;
import utils.constants.ServerResponseConstants;
import utils.validation.RecordValidation;

import java.util.List;

@Service
@Transactional
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordRepository recordRepository;

    @Override
    public Record createNewRecord(Record data) throws CustomGenericException{
        try {
            RecordValidation.validateRecord(data);
        }catch (CustomGenericException cge){
            throw cge;
        }

        Record record = recordRepository.findRecordByPPSNumber(data.getPpsNumber());
        if(record != null)
        {
            throw new CustomGenericException(ServerResponseConstants.FAILED_DUPE_PPS_CODE,ServerResponseConstants.FAILED_DUPE_PPS_TEXT);
        }
        return recordRepository.createRecord(data);
    }

    @Override
    public List<Record> findAllRecords() {
        return recordRepository.findAllRecords();
    }
}
