package com.tuyano.springboot;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HeloController4 {

	@RequestMapping("/")
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index4");
		mav.addObject("msg","current data.");
		DataObject obj = new DataObject(123, "hanako","hanako@flower");
		mav.addObject("object",obj);
		mav.addObject("msg2","message 1<hr/>message 2<br/>message 3");
		return mav;
	}

	@RequestMapping("/{id}")
	public ModelAndView index2(@PathVariable int id,
			ModelAndView mav) {
		mav.setViewName("index4-1");
		mav.addObject("id",id);
		mav.addObject("check",id % 2 == 0);
		mav.addObject("trueVal","Even number!");
		mav.addObject("falseVal","Odd number...");
		return mav;
	}


	@RequestMapping("/sw/{month}")
	public ModelAndView index(@PathVariable int month,
			ModelAndView mav) {
		mav.setViewName("index4-2");
		int m = Math.abs(month) % 12;
		m = m == 0 ? 12 : m;
		mav.addObject("month",m);
		mav.addObject("check",Math.floor(m / 3));
		return mav;
	}

	@RequestMapping("/each")
	public ModelAndView indexeach(ModelAndView mav) {
		mav.setViewName("index4-3");
		ArrayList<String[]> data = new ArrayList<String[]>();
		data.add(new String[]{"taro","taro@yamada","090-999-999"});
		data.add(new String[]{"hanako","hanako@flower","080-888-888"});
		data.add(new String[]{"sachiko","sachiko@happy","080-888-888"});
		mav.addObject("data",data);
		return mav;
	}

	@RequestMapping("/frag")
	public ModelAndView indexfrag(ModelAndView mav) {
		mav.setViewName("index4-4");
		return mav;
	}

	@RequestMapping("/js/{tax}")
	public ModelAndView indexjs(@PathVariable int tax,
			ModelAndView mav) {
		mav.setViewName("indexjs");
		mav.addObject("tax",tax);
		return mav;
	}
}