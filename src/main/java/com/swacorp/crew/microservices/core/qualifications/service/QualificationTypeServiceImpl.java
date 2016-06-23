package com.swacorp.crew.microservices.core.qualifications.service;

import com.gemstone.gemfire.cache.Cache;
import com.swacorp.crew.microservices.core.qualifications.api.QualType;
import com.swacorp.crew.microservices.core.qualifications.api.QualTypeFilter;
import com.swacorp.crew.microservices.core.qualifications.domain.QualificationType;
import com.swacorp.crew.microservices.core.qualifications.repository.QualificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QualificationTypeServiceImpl implements QualificationTypeService {

    @Autowired
    QualificationRepository repository;

    @Autowired
    private Cache cache;

    private QueriesUtil queriesutil;

    /**
     * Create a changeLog entry into GenFire region
     *
     * @param qualificationType
     * @return ChangeLog Object
     */
    @Override
    public QualificationType create(QualificationType qualificationType) {
        return repository.save(qualificationType);
    }

    /**
     * Return a list of all changeLogs
     *
     * @return List of all ChangeLog entries
     */
    @Override
    public List<QualificationType> findAll() {
        return (List) repository.findAll();
    }

    /**
     * Finds an specific changeLog based on ID
     *
     * @param qualificationId
     * @return ChangeLog Object
     */
    @Override
    public QualificationType findById(Integer qualificationId) {
        QualificationType find = repository.findOne(qualificationId);
        return find;
    }

    /**
     *
     * @param filter
     * @return
     */
    @Override
    public List<QualificationType> findByFilter(QualTypeFilter filter) {
        List<String> queryFilter = new ArrayList<>();
        Optional<Integer> id = Optional.ofNullable(filter.getQualificationId());
        Optional<String> station = Optional.ofNullable(filter.getStation());
        Optional<String> group = Optional.ofNullable(filter.getGroup());
        Optional<String> position = Optional.ofNullable(filter.getPosition());
        Optional<List<QualType>> types = Optional.ofNullable(filter.getQualTypes());

        queryFilter.add(id.map(fieldValue -> String.format("qualificationId = %d", fieldValue)).orElse(""));

        if (station.isPresent()) {
            addToFilterMap(queryFilter, "station = '%s'", station);
        }

        if (group.isPresent()) {
            addToFilterMap(queryFilter, "group = '%s'", group);
        }

        if (position.isPresent()) {
            addToFilterMap(queryFilter, "position = '%s'", position);
        }

        if (types.isPresent()) {
            types.get().stream().forEach((item) -> {
                queryFilter.add(String.format("type = '%s'", item.getQualType()));
                queryFilter.add(String.format("SubType = '%s'", item.getQualSubType()));
            });
        }

        queriesutil = new QueriesUtil(cache.getQueryService());
        return new ArrayList<>(queriesutil.findByThisSearchCriteria(queryFilter));
    }

    private void addToFilterMap(List<String> queryFilter, String filter, Optional<String> field) {
        queryFilter.add(field.map(fieldValue -> String.format(filter, fieldValue)).orElse(""));
    }



    /**
     * Updates a ChangeLog entry based on a ChangeLog input Object
     *
     * @param qualificationType
     * @return updated QualificationType Object
     */
    @Override
    public QualificationType update(QualificationType qualificationType) {
        return repository.save(qualificationType);
    }

    /**
     * Removes an specific changeLog entry based on a changeLog Object
     *
     * @param qualificationType
     */
    @Override
    public void delete(QualificationType qualificationType) {
        repository.delete(qualificationType);
    }
}
