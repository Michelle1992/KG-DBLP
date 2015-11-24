package neo4j.domain;

import org.neo4j.ogm.annotation.*;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

@JsonIdentityInfo(generator=JSOGGenerator.class)
@RelationshipEntity(type = "PUBLISH")
public class Publication {
    @GraphId
    Long id;
    Collection<String> publications;
    @StartNode Author author;
    @EndNode   Paper paper;

    public Publication() {
    }

    public Collection<String> getPublications() {
        return publications;
    }

    public Author getAuthor() {
        return author;
    }

    public Paper getPaper() {
        return paper;
    }
}

