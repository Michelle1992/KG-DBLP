package neo4j.domain;


import org.neo4j.ogm.annotation.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

@JsonIdentityInfo(generator=JSOGGenerator.class)
@NodeEntity
public class Author {
    @GraphId 
    Long id;

    private String name;

    @Relationship(type = "PUBLISH")
    List<Paper> papers;

    public Author() { }

    public String getName() {
        return name;
    }

    public List<Paper> getPapers() {
        return papers;
    }
}

