package com.swacorp.crew.microservices.core.qualifications.api;

import com.swacorp.crew.microservices.core.qualifications.domain.QualificationType;
import com.swacorp.crew.microservices.core.qualifications.exception.BadRequestException;
import com.swacorp.crew.microservices.core.qualifications.exception.NotFoundException;
import com.swacorp.crew.microservices.core.qualifications.service.MessageSenderService;
import com.swacorp.crew.microservices.core.qualifications.service.QualificationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * Created by POD Norris on 5/9/16.
 */
@RestController
@RequestMapping("/qualificationtype")
public class QualificationTypeApi {

    @Autowired
    QualificationTypeService service;

    @Autowired
    MessageSenderService senderService;

    /**
     *
     * @return @throws com.swacorp.crew.microservices.core.qualifications.exception.NotFoundException
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<QualificationType> getQualification() throws NotFoundException {
        List<QualificationType> qualificationType = service.findAll();
        if (!qualificationType.isEmpty()) {
            return qualificationType;
        } else {
            throw new NotFoundException();
        }
    }

    /**
     *
     * @param qualificationId
     * @return
     * @throws com.swacorp.crew.microservices.core.qualifications.exception.BadRequestException
     * @throws com.swacorp.crew.microservices.core.qualifications.exception.NotFoundException
     */
    @RequestMapping(value = "/{qualificationId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public QualificationType getQualification(@PathVariable Integer qualificationId) throws BadRequestException, NotFoundException {
        QualificationType qualificationType = null;

        try {
            qualificationType = service.findById(qualificationId);
        } catch (NumberFormatException e) {
            throw new BadRequestException();
        }

        if (qualificationType != null) {
            return qualificationType;
        } else {
            throw new NotFoundException();
        }
    }


    /**
     * Gets a Crew Qualification List based on given filter.
     *
     * @param filter
     * @return the updated crew qualification
     * @throws com.swacorp.crew.microservices.core.qualifications.exception.NotFoundException
     * @throws com.swacorp.crew.microservices.core.qualifications.exception.BadRequestException
     */
    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<QualificationType> getQualificationByFilter(@RequestBody QualTypeFilter filter) throws NotFoundException, BadRequestException {

        List<QualificationType> qualificationsType = null;
        qualificationsType = service.findByFilter(filter);
        if (qualificationsType == null)
            throw new NotFoundException();

        return qualificationsType;
    }
    
    /**
     *
     * @param inputQualifiation
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public QualificationType save(@RequestBody QualificationType inputQualifiation) {

        QualificationType createdQualificationType = null;
        createdQualificationType = service.create(inputQualifiation);
        senderService.sendToStream(inputQualifiation, "create");
        return createdQualificationType;

    }

    /**
     *
     * @param qualificationId
     * @throws com.swacorp.crew.microservices.core.qualifications.exception.NotFoundException
     * @throws com.swacorp.crew.microservices.core.qualifications.exception.BadRequestException
     */
    @RequestMapping(value = "/{qualificationId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer qualificationId) throws NotFoundException, BadRequestException {

        QualificationType qualificationType = null;
        try {
            qualificationType = service.findById(qualificationId);
        } catch (NumberFormatException e) {
            throw new BadRequestException();
        } 
        
        if (qualificationType != null) {
            service.delete(qualificationType);
        } else {
            throw new NotFoundException();
        }
        senderService.sendToStream(qualificationType, "delete");
    }

    /**
     *
     * @param qualificationId
     * @param inQualificationType
     * @return
     * @throws com.swacorp.crew.microservices.core.qualifications.exception.NotFoundException
     * @throws com.swacorp.crew.microservices.core.qualifications.exception.BadRequestException
     */
    @RequestMapping(value = "/{qualificationId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public QualificationType update(@PathVariable Integer qualificationId, @RequestBody QualificationType inQualificationType) throws NotFoundException, BadRequestException {

        QualificationType qualificationType = null;
        try {
            qualificationType = service.findById(qualificationId);
        } catch (NumberFormatException e) {
            throw new NotFoundException();
        } 

        if (qualificationType != null && Objects.equals(qualificationType.getQualificationId(), inQualificationType.getQualificationId())) {
            qualificationType = service.update(inQualificationType);
        } else {
            throw new BadRequestException();
        }
        senderService.sendToStream(inQualificationType, "update");
        return qualificationType;
    }

}
