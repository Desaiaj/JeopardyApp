package ca.sheridancollege.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.beans.Player;
import ca.sheridancollege.beans.Question;
import ca.sheridancollege.database.DatabaseAccess;

@Controller
public class HomeController {
	@Autowired
	private DatabaseAccess da;

	@GetMapping("/")
	public String home() {
		return "login.html";
	}

	@GetMapping("/GameBoard")
	public String GameBoard(@RequestParam String name, HttpSession session) {
		Player player = new Player(name, 0);
		session.setAttribute("player", player);
		session.setAttribute("answered", new Question());
		return "GameBoard.html";
	}

	@GetMapping("/Question")
	public String goQuestions() {
		return "Question.html";
	}

	@GetMapping("/pickQuestion/{category}/{value}")
	public String pickQuestion(HttpSession session, @PathVariable String category, @PathVariable int value) {
		Question ques = da.getQuestions(category, value);
		session.setAttribute("question", ques);
		return "Question.html";
	}

	@GetMapping("/answerQuestion")
	public String answerQuestion(@RequestParam String ques, @RequestParam String ans, HttpSession session) {
		Player player = (Player) session.getAttribute("player");
		Question question = (Question) session.getAttribute("question");
		Question Answered = (Question) session.getAttribute("answered");

		if (question.getQuestion().equals(ques) && question.getAnswer().equals(ans)) {
			player.setScore(player.getScore() + question.getValue());
			Answered.setCategory(question.getCategory());
			Answered.setValue(question.getValue());
		} else
			player.setScore(player.getScore() - question.getValue());
		session.setAttribute("player", player);
		session.setAttribute("question", question);
		session.setAttribute("answered", Answered);
		return "GameBoard.html";
	}
}
