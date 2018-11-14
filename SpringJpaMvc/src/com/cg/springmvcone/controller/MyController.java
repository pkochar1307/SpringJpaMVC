package com.cg.springmvcone.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cg.springmvcone.dto.Employee;

import com.cg.springmvcone.service.IEmployeeService;
import com.cg.springmvcone.service.EmployeeServiceImpl;

@Controller
public class MyController {

	@Autowired
	IEmployeeService employeeService;
	@RequestMapping(value="all",method=RequestMethod.GET)
	public String getAll(){
		return "home";
	}
	
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String addEmployee(@ModelAttribute("my") Employee emp,Map<String,Object> model){
		List<String> myDeg = new ArrayList<>();
		myDeg.add("software engg");
		myDeg.add("sr consultant");
		myDeg.add("manager");
		model.put("deg", myDeg);
		return "addemployee";
		
	}
	
	@RequestMapping(value="insertdata",method=RequestMethod.POST)
	public ModelAndView insertEmployee(@Valid@ModelAttribute("my") Employee emp, BindingResult result, Map<String,Object> model){
		int id=0;
		if(result.hasErrors()){
			List<String> myDeg = new ArrayList<>();
			myDeg.add("software engg");
			myDeg.add("sr consultant");
			myDeg.add("manager");
			model.put("deg", myDeg);
			return new ModelAndView("addEmployee");
		}else{
			id=employeeService.addEmployeeData(emp);
		}
		return new ModelAndView("sucess","edata",id);
	}
	
	@RequestMapping(value="show",method=RequestMethod.GET)
		public ModelAndView showAllEmployee(){
			List<Employee> myAllData = employeeService.showAllEmployee();
			return new ModelAndView("showall","temp",myAllData);
		}

	@RequestMapping(value="delete",method=RequestMethod.GET)
	public String deleteEmployee(){
		return "deleteemployee";
	}
	

	@RequestMapping(value="dodelete",method=RequestMethod.GET)
	public String employeeDelete(@RequestParam("eid") int id){
		employeeService.deleteEmployee(id);
		return "sucess";
	}
}
