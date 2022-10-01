package com.exam.portal.entity.exam;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Table(name = "quizzes")
public class Quiz {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long quizID;

    @Column(unique = true)
    @NotBlank
    private String title;

    @Column(length = 1000)
    @NotBlank
    private String description;

    @NotBlank
    private String maxMarks;

    @NotBlank
    private String numberOfQuestions;

    private boolean active = false;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @OneToMany(
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL
    )
    @JsonIgnore
    private Set<Question> questions = new HashSet<>();
}
