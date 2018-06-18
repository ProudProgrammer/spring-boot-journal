package hu.gaborbalazs.practice.springboot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hu.gaborbalazs.practice.springboot.repository.JournalRepository;

@Controller
public class JournalRestController {

	private static final String VIEW_INDEX = "index";

	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Value("${myqueue}")
	private String queue;

	@Autowired
	private JournalRepository journalRepository;

	@RequestMapping(value = "/")
	public ModelAndView index(ModelAndView modelAndView) {
		jmsTemplate.convertAndSend(queue, "Jeeee");
		modelAndView.setViewName(VIEW_INDEX);
		modelAndView.addObject("journal", journalRepository.findAll());
		return modelAndView;
	}
}
