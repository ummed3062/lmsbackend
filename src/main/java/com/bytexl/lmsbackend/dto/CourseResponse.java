package com.bytexl.lmsbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponse {

    private Long id;
    private String title;
    private String description;
    private Double price;
    private Integer duration;
}
