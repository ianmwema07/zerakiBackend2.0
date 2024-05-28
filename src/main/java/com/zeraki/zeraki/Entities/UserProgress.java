package com.zeraki.zeraki.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Immutable;


@Immutable
@Entity
@Table(name = "user_progress")
@Data
public class UserProgress {

    @Id
    @Column(name = "id")
    Long id;
    String userName;
    String exerciseName;
    Long marks;
    String remarks;
}
