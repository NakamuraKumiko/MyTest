package com.tuyano.springboot;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tuyano.springboot.repositories.MyDataRepository;

//@Controller
public class HeloController6 {

	@Autowired
	MyDataRepository repository;

//	@PersistenceContext
//	EntityManager entityManager; //●

	@Autowired
	MyDataDaoImpl dao; //●

	@PostConstruct
	public void init(){
//		dao = new MyDataDaoImpl(entityManager); //●

	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index6-1");
		mav.addObject("msg","MyDataのサンプルです。");
		//Iterable<MyData> list = dao.getAll(); //●
		//Iterable<MyData> list = repository.findAllOrderByName(); //dao.getAll(); //●
		//Iterable<MyData> list = dao.findByAge(-17,99); //●
		Iterable<MyData> list = repository.findByAge(25,40); //●
		mav.addObject("datalist", list);
		return mav;
	}

	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public ModelAndView find(ModelAndView mav) {
		mav.setViewName("find");
		mav.addObject("title","Find Page");
		mav.addObject("msg","MyDataのサンプルです。");
		mav.addObject("value","");
		Iterable<MyData> list = dao.getAll(); //●
		mav.addObject("datalist", list);
		return mav;
	}

	@RequestMapping(value = "/find", method = RequestMethod.POST)
	public ModelAndView search(HttpServletRequest request,
			ModelAndView mav) {
		mav.setViewName("find");
		String param = request.getParameter("fstr");
		if (param == ""){
			mav = new ModelAndView("redirect:/find");
		} else {
			mav.addObject("title","Find result");
			mav.addObject("msg","「" + param + "」の検索結果");
			mav.addObject("value",param);
			List<MyData> list = dao.find(param);
			mav.addObject("datalist", list);
		}
		return mav;
	}

}