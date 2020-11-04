package ca.sheridancollege.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.beans.Question;

@Repository
public class DatabaseAccess {

	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	public Question getQuestions(String category, int value) {
		MapSqlParameterSource perameters = new MapSqlParameterSource();
		String query = "Select * from question where category=:category and value=:value";
		perameters.addValue("category", category);
		perameters.addValue("value", value);
		Question question = jdbc.query(query, perameters, new BeanPropertyRowMapper<Question>(Question.class)).get(0);
		return question;

	}
}
