package hu.gaborbalazs.practice.springboot.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hu.gaborbalazs.practice.springboot.domain.Journal;
import hu.gaborbalazs.practice.springboot.repository.JournalRepository;

@Controller
public class JournalController {

	@Autowired
	private JournalRepository journalRepository;

	@RequestMapping(value = "/journal", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<Journal> getJournal() {
		return journalRepository.findAll();
	}

	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("journal", journalRepository.findAll());
		return "index";
	}
}
