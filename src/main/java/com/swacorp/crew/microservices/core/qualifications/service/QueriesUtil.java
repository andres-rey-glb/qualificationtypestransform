package com.swacorp.crew.microservices.core.qualifications.service;

import com.gemstone.gemfire.cache.query.*;
import com.swacorp.crew.microservices.core.qualifications.domain.QualificationType;

import java.util.Collection;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author x220551
 */
public class QueriesUtil {

    public QueriesUtil(QueryService queryService) {
        this.queryService = queryService;
    }

    private final QueryService queryService;
    private Query query;

    @SuppressWarnings("unchecked")
    public Collection<QualificationType> findByThisSearchCriteria(Collection<String> filters) {
        try {
            StringBuilder queryString = new StringBuilder("SELECT * FROM /QualificationType qt WHERE ");
            filters.removeIf(filter -> filter.isEmpty());
            String whereSection = filters.stream().collect(Collectors.joining(" AND "));
            queryString.append(whereSection);
            Logger.getAnonymousLogger().info("Query:"+queryString);
            query = queryService.newQuery(queryString.toString());
            return  (SelectResults<QualificationType>) query.execute();
        } catch (FunctionDomainException | TypeMismatchException | NameResolutionException | QueryInvocationTargetException ex) {
            Logger.getLogger(QualificationTypeServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return Collections.emptyList();
        }
    }
}
