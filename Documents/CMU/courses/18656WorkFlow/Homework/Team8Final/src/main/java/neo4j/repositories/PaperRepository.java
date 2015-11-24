package neo4j.repositories;

import neo4j.domain.Paper;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public interface PaperRepository extends GraphRepository<Paper> {
    Paper findByTitle(@Param("title") String title);

    @Query("MATCH (p:Paper) WHERE p.title =~ ('(?i).*'+{title}+'.*') RETURN p")
    Collection<Paper> findByTitleContaining(@Param("title") String title);

    @Query("MATCH (p:Paper)<-[:PUBLISH]-(a:Author) RETURN p.title as paper, collect(a.name) as cast LIMIT {limit}")
    List<Map<String, Object>> graph(@Param("limit") int limit);

    @Query("MATCH (p:Author {name:{name}})-[:CO]->(c:Author) RETURN distinct c.name as paper, collect(p.name) as cast")
    List<Map<String, Object>> graph(@Param("name") String name);
    
    @Query("MATCH (a:Author {name:{name1}})-[:CO]->(Author),(Author)-[:CO]->(coAuthors) RETURN coAuthors.name as paper, collect(Author.name) as cast")
    List<Map<String, Object>> findCoAuthorCoAuthor(@Param("name1") String name);
}


