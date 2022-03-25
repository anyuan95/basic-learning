package org.example.spring.testing.testng.ones.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.example.spring.testing.testng.ones.enumerations.ViewType;

import java.util.List;

/**
 * @author anyuan
 * @date 2022-01-05 15:07
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ViewScheme {

    private Long id;
    private String name;
    private Long createdAt;
    private String createdBy;
    private Long updatedAt;
    private String updatedBy;
    private List<Integer> fieldDesc;
    private ViewType viewType;
    private List<ViewGroup> viewGroupList;


//    private String viewGroups;
//
//    private String viewEntityRelationship;
//
//    private transient List<Field> fieldDetail;
//    private transient List<ViewEntityRelationship> viewEntityRelationshipList;
//
//    private transient Long entityId;
//    private transient Long currentProjectId;
//    private transient String currentType;
//    private transient Long subtypeId;
//    private transient Long transitionId;
}
