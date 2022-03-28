package com.ibm.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.entity.Pan;
import com.ibm.service.PanService;


@RestController
public class PanController {
	@Autowired
	private PanService panService;
	
	@PostMapping(path = "/create-pan", consumes = "application/json")
	public Pan createPan(@RequestBody Pan p) {
		return panService.createPan(p);
//		Pan createdPan = panService.createPan(p);
//		return "Pan created. Details: "+createdPan;
	}
	@PostMapping(path = "/update-pan", consumes = "application/json")
	public Pan updatePan(@RequestBody Pan p) {
		return panService.updatePan(p);
//		Pan updatedPan = panService.createPan(p);
//		return "Pan updated. Details: "+updatedPan;
	}
	
	@GetMapping(path = "/get-pans/{panNo}", produces = "application/json")
	public Pan getPanByPanNo(@PathVariable String panNo) {
		return panService.getPanByPanNo(panNo);
	}
	
	@GetMapping(path = "/get-pans", produces = "application/json")
	public List<Pan> getAllPans() {
		return panService.getAllPans();
	}
	
	@GetMapping(path = "/get-pan-by-customer", produces = "application/json")
	public Pan getPanByCustomerId(@RequestParam int custId) {
		return panService.getPanByCustomerId(custId);
	}
	@GetMapping(path = "/get-cibil-score", produces = "application/json")
	public int getCibilScore(@RequestParam int custId) {
		return panService.getCibilScore(custId);
	}
	
	
}
