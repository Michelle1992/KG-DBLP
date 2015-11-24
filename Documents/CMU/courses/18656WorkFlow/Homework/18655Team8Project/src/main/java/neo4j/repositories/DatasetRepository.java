package neo4j.repositories;

import neo4j.domain.Dataset;
import neo4j.domain.Paper;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public interface DatasetRepository extends GraphRepository<Paper> {
    Paper findByTitle(@Param("title") String title);

    @Query("MATCH (d:Dataset) WHERE d.title =~ ('(?i).*'+{title}+'.*') RETURN d")
    Collection<Dataset> findByTitleContaining(@Param("title") String title);

    @Query("MATCH (d:Dataset)<-[:USE]-(a:User) RETURN d.title as dataset, collect(a.name) as cast LIMIT {limit}")
    List<Map<String, Object>> graph(@Param("limit") int limit);
}


