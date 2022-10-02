package com.exam.portal.entity.exam;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Table(name = "questions")
public class Question {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long questionID;

    @Column(length = 5000)
    @NotBlank
    private String content;

    private String image;

    @NotBlank
    private String answer;

    @Transient // will not add this field in the dB
    private String givenAnswer = "";

    private String option1;
    private String option2;
    private String option3;
    private String option4;

    @ManyToOne(fetch = FetchType.EAGER)
    private Quiz quiz;
}
