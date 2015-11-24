package neo4j.domain;

import org.neo4j.ogm.annotation.*;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

@JsonIdentityInfo(generator=JSOGGenerator.class)

@NodeEntity
public class Dataset {
    @GraphId 
    Long id;

    String title;

    @Relationship(type="USE", direction = Relationship.INCOMING) List<Dataset> datasets;

    public Dataset() { }

    public String getTitle() {
        return title;
    }

    public Collection<Dataset> getDatasets() {
        return datasets;
    }
}


