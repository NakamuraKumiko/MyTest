package com.tuyano.springboot;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tuyano.springboot.repositories.MyDataRepository;

//@Controller
public class HeloController5 {

	@Autowired
	MyDataRepository repository;

	/*	@PostConstruct
		public void init(){
			MyData d1 = new MyData();
			d1.setName("tuyano");
			d1.setAge(123);
			d1.setMail("syoda@tuyano.com");
			d1.setMemo("this is my data!");
			repository.saveAndFlush(d1);
			MyData d2 = new MyData();
			d2.setName("hanako");
			d2.setAge(15);
			d2.setMail("hanako@flower");
			d2.setMemo("my girl friend.");
			repository.saveAndFlush(d2);
			MyData d3 = new MyData();
			d3.setName("sachiko");
			d3.setAge(37);
			d3.setMail("sachico@happy");
			d3.setMemo("my work friend...");
			repository.saveAndFlush(d3);
		}*/

	@RequestMapping("/")
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index5");
		mav.addObject("msg","this is sample content.");
		//Iterable<MyData> list = repository.findAll();
		//Iterable<MyData> list = repository.findByAgeGreaterThan(21);
		Iterable<MyData> list = repository.findByIdIsNotNullOrderByIdAsc();
		mav.addObject("data",list);
		return mav;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView index(
			@ModelAttribute("formModel") MyData mydata,
			ModelAndView mav) {
		/*		System.out.println("formModel mydataの中身");
				System.out.println(mydata.getId());
				System.out.println(mydata.getName());
				System.out.println(mydata.getAge());*/
		mav.setViewName("index5-3");
		mav.addObject("msg","this is sample content.");
		mav.addObject("formModel",mydata);
		Iterable<MyData> list = repository.findAll();
		mav.addObject("datalist",list);
		return mav;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@Transactional(readOnly=false)
	public ModelAndView form(
			@ModelAttribute("formModel")
			@Validated MyData mydata,
			BindingResult result,
			ModelAndView mov) {
		ModelAndView res = null;
		if (!result.hasErrors()){
			repository.saveAndFlush(mydata);
			res = new ModelAndView("redirect:/");
		} else {
			mov.setViewName("index5-3");
			mov.addObject("msg","sorry, error is occured...");
			Iterable<MyData> list = repository.findAll();
			mov.addObject("datalist",list);
			res = mov;
		}
		return res;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute MyData mydata,
			@PathVariable int id,ModelAndView mav) {
		mav.setViewName("edit");
		mav.addObject("title","edit mydata.");
		Optional<MyData> data = repository.findById((long)id);
		//MyData data = repository.findById((long)id);
		mav.addObject("formModel",data.get());
		return mav;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@Transactional(readOnly=false)
	public ModelAndView update(@ModelAttribute MyData mydata,
			ModelAndView mav) {
		repository.saveAndFlush(mydata);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable int id,
			ModelAndView mav) {
		mav.setViewName("delete");
		mav.addObject("title","delete mydata.");
		Optional<MyData> data = repository.findById((long)id);
		mav.addObject("formModel",data.get());
		return mav;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@Transactional(readOnly=false)
	public ModelAndView remove(@RequestParam long id,
			ModelAndView mav) {
		repository.deleteById(id);
		return new ModelAndView("redirect:/");
	}
}