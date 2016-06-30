package controllers;

import models.entities.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import services.RecordService;
import services.exceptions.CustomGenericException;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RecordController {

    private RecordService recordService;

    @Autowired
    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @RequestMapping(value="/addNewRecord", method=RequestMethod.GET)
    public String greetingForm(Model model) {
        model.addAttribute("record", new Record());
        return "addNewRecord";
    }


    @RequestMapping(value="/listAllRecords", method=RequestMethod.GET)
    public String listAllRecords(Model model) {
        List<Record> records = recordService.findAllRecords();
        model.addAttribute("records",records);
        return "listAllRecords";
    }

    @RequestMapping(value="/processAddNewRecord", method=RequestMethod.POST)
    public String checkPersonInfo(@ModelAttribute @Valid Record record, BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()) {
            return "addNewRecord";
        }else{
            try {
                recordService.createNewRecord(record);
            }catch (CustomGenericException cge){
                model.addAttribute("record", record);
                model.addAttribute("errMsg", cge.getErrMsg());
                return "addNewRecord";
            }

            model.addAttribute("record", record);
        }
        return "redirect:/listAllRecords";
    }

    @ExceptionHandler(CustomGenericException.class)
    public ModelAndView handleCustomException(CustomGenericException ex) {
        System.out.println("INSIDE HANDLE CUSTOM EXCEPTION");
        ModelAndView model = new ModelAndView("error");
        model.addObject("errCode", ex.getErrCode());
        model.addObject("errMsg", ex.getErrMsg());

        return model;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllException(Exception ex) {
        System.out.println("INSIDE HANDLE ALL EXCEPTION");
        ModelAndView model = new ModelAndView("error");
        model.addObject("errMsg", ex.toString());
        return model;

    }
}