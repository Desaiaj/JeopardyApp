package ca.sheridancollege.beans;

import java.io.Serializable;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Question implements Serializable {

	private static final long serialVersionUID = 1L;
	private String category;
	private int value;
	private String question;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String answer;
}
