package org.example.spring.testing.testng.ones.enumerations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import retrofit2.http.GET;

/**
 * @author anyuan
 * @date 2022-03-15 21:02
 */
@Getter
@AllArgsConstructor
@ToString
public enum ViewType {

    CREATE_VIEW,
    TRANSITION_VIEW

}
