package com.crsms.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.crsms.dto.VacancyJsonDto;

@Service("vacationService")
public class VacancyServiceImpl implements VacancyService {

	private static final String URL = "http://softserve.ua/vacancies"
			+ "/open-vacancies/?tax-direction=0&tax-country=117&tax-city=140";

	private static Logger logger = LogManager
			.getLogger(VacancyServiceImpl.class);

	@Override
	public List<VacancyJsonDto> getAllVacancies() throws IOException {
		List<VacancyJsonDto> vacationsList = new ArrayList<VacancyJsonDto>();
		Document doc = null;
		try {
			doc = Jsoup.connect(URL).get();
		} catch (IOException e) {
			logger.error("Error connect URL=" + ": " + e);
			throw e;
		}
		Elements links = doc.select("a[class=card-vacancy-link]");
		if (links.isEmpty())
			logger.error("Links not found");
		Long id = 1L;
		for (Element el : links) {
			VacancyJsonDto vacation = new VacancyJsonDto();
			vacation.setId(id);
			String name = el.getElementsByTag("h3").text();
			if (name.equals(""))
				logger.error("Headers not found");
			vacation.setName(name);
			vacation.setUrl(el.attr("href"));
			vacationsList.add(vacation);
			id++;
		}

		return vacationsList;
	}
}
