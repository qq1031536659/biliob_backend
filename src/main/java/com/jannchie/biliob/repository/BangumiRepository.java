package com.jannchie.biliob.repository;

import com.jannchie.biliob.model.Bangumi;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jannchie
 */
@Repository
public interface BangumiRepository
        extends MongoRepository<Bangumi, ObjectId>, PagingAndSortingRepository<Bangumi, ObjectId> {
}
