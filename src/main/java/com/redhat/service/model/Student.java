package com.redhat.service.model;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;

import lombok.Data;
import lombok.ToString;

@RedisHash("Student")
@Data
@ToString
public class Student implements Serializable {

	private static final long serialVersionUID = -2915422077032803231L;
	public enum Gender {
		MALE, FEMALE
	}

	private String id;
	private String name;
	private Gender gender;
	private int grade;
}
