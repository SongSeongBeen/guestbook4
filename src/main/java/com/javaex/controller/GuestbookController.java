package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping(value="/book")
public class GuestbookController {
	//필드
	@Autowired
	private GuestbookDao guestbookDao;
	private GuestbookVo guestbookVo;
	//생성자
	
	//메소드gs
	
	//메소드 일반
	
//리스트	
	@RequestMapping(value = "/gList", method= {RequestMethod.GET, RequestMethod.POST})
	public String gList(Model model) {
			System.out.println("GuestbookController=addList");
		
		List<GuestbookVo> gList = guestbookDao.getList();
		System.out.println(gList.toString());
		
		model.addAttribute("gList", gList);
			
		return "addList";
	}

//리스트입력-확인
	@RequestMapping(value = "/add", method= {RequestMethod.GET, RequestMethod.POST})
	public String add(@ModelAttribute GuestbookVo guestboookVo) {
		System.out.println("GuestbookController=add");
		
		guestbookDao.guestInsert(guestboookVo);
	
	return "redirect:./gList";
}
	
//삭제-폼
	@RequestMapping(value = "/deleteForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String deleteForm(@ModelAttribute GuestbookVo guestboookVo) {
		System.out.println("GuestbookController=deleteForm");
		
	return "deleteForm";
}
//삭제
	@RequestMapping(value = "/delete", method= {RequestMethod.GET, RequestMethod.POST})
	public String delete(@ModelAttribute GuestbookVo guestboookVo) {
		System.out.println("GuestbookController=delete");
		
		guestbookDao.guestbookDelete(guestboookVo);
	
		return "redirect:./gList";
}
	

}
