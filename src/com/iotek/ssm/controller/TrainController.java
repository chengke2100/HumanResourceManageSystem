package com.iotek.ssm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iotek.ssm.entity.Train;
import com.iotek.ssm.service.TrainService;

@Controller
@RequestMapping("train")
public class TrainController {
	@Autowired
	private TrainService trainService;
	
	@RequestMapping("showAll")
	public String findAllTrains(Model model) {
		List<Train> trains = trainService.findAllTrains();
		model.addAttribute("trains", trains);
		return "manager";
	}
	
	public String addTrain()
}
