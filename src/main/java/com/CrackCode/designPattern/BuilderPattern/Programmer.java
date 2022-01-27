package com.CrackCode.designPattern.BuilderPattern;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Programmer {
    private Long id;
    private String name;
    private List<String> favouriteLanguages;
    private String skilledFrameworkName;
    private Integer numberOfExperience;
}
