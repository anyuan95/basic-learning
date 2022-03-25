package org.example.spring.testing.testng.ones.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * @author anyuan
 * @date 2022-01-05 15:39
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ViewGroup {

    private String groupName;
    private Integer orderInViewScheme;
    private String position;
    private List<ViewField> viewFieldList;

    private boolean showDelete = true;
    private boolean hideAddFieldButton = false;

    /**
     * NORMAL / WORKTIME
     */
    private String viewGroupType;
}
