package neo4j.domain;


import org.neo4j.ogm.annotation.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

@JsonIdentityInfo(generator=JSOGGenerator.class)
@NodeEntity
public class User {
    @GraphId 
    Long id;

    private String name;

    @Relationship(type = "USE")
    List<Dataset> datasets;

    public User() { }

    public String getName() {
        return name;
    }

    public List<Dataset> getDatasets() {
        return datasets;
    }
}

