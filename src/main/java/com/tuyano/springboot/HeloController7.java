package com.tuyano.springboot;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tuyano.springboot.repositories.MyDataRepository;
import com.tuyano.springboot.service.MyDataService;
import com.tuyano.springboot.service.MySampleBean;

//@Controller
public class HeloController7 {

	@Autowired
	MyDataRepository repository;

	@Autowired
	private MyDataService service;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index6-1");
		mav.addObject("title","Find Page");
		mav.addObject("msg","MyDataのサンプルです。");
		List<MyData> list = service.getAll(); //●
		mav.addObject("datalist", list);
		return mav;
	}

	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public ModelAndView find(ModelAndView mav) {
		mav.setViewName("find");
		mav.addObject("title","Find Page");
		mav.addObject("msg","MyDataのサンプルです。");
		mav.addObject("value","");
		List<MyData> list = service.getAll(); //●
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
			List<MyData> list = service.find(param); //●
			mav.addObject("datalist", list);
		}
		return mav;
	}

	@Autowired
	MySampleBean bean;

	@RequestMapping("/count")
	public int count() {
		return bean.count();
	}

	@Autowired
	MyDataBean myDataBean;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView indexById(@PathVariable long id,
			ModelAndView mav) {
		mav.setViewName("pickup");
		mav.addObject("title","Pickup Page");
		String table = "<table>"
				+ myDataBean.getTableTagById(id)
				+ "</table>";
		mav.addObject("msg","pickup data id = " + id);
		mav.addObject("data",table);
		return mav;
	}

}