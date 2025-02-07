package org.example.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author anyuan
 * @date 2022-09-13 19:49
 */
@Data
public class User {

    private Long id;
    @JsonProperty("$name")
    private String name;
    @JsonProperty("$date")
    private Date date;

}
