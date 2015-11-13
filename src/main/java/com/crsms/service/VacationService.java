package com.crsms.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.crsms.dto.VacationJson;

@Service("vacationService")

public class VacationService {
  
  private static final String URL = "http://softserve.ua/vacancies"
      + "/open-vacancies/?tax-direction=0&tax-country=117&tax-city=140";  
  
  public List<VacationJson> getAllVacations() {
    List<VacationJson> vacationsList = new ArrayList<VacationJson>();
    try {
      Document doc = Jsoup.connect(URL).get();  
      Elements links = doc.select("a[class=card-vacancy-link]");
      Long id = 1L;
      for (Element el : links) {
        VacationJson vacation = new VacationJson();
        vacation.setId(id);    
        vacation.setName(el.getElementsByTag("h3").text());
        vacation.setUrl(el.attr("href"));
        vacationsList.add(vacation); 
        id++;
      } 
    } catch (IOException e) {
        e.printStackTrace();
    }
    return vacationsList;
  }
}
  
