package com.ibm.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.entity.Pan;
import com.ibm.pojo.ResponseHeader;
import com.ibm.service.PanService;
/**
 * Class {PanController} is the controller class.
 * Mainly having the routes related to pan entity.
 * Mainly uses PanService methods.
 * 
 * @author Saswata Dutta
 */

@CrossOrigin
@RestController
public class PanController {
	@Autowired
	private PanService panService;
	private ResponseHeader rh;
	
	@PostMapping(path = "/create-pan", consumes = "application/json")
	public ResponseEntity<Pan> createPan(@RequestBody Pan p) {
//		return panService.createPan(p);
		rh = new ResponseHeader();
		rh.putOnMap("success", "true");
		ResponseEntity<Pan> res = new ResponseEntity<Pan>(
				panService.createPan(p),
				rh.getHeaders(), HttpStatus.OK);
		return res;
	}
	@PostMapping(path = "/update-pan", consumes = "application/json")
	public ResponseEntity<Pan> updatePan(@RequestBody Pan p) {
//		return panService.updatePan(p);
		rh = new ResponseHeader();
		rh.putOnMap("success", "true");
		ResponseEntity<Pan> res = new ResponseEntity<Pan>(
				panService.updatePan(p),
				rh.getHeaders(), HttpStatus.OK);
		return res;
	}
	
	@GetMapping(path = "/get-pans/{panNo}", produces = "application/json")
	public ResponseEntity<Pan> getPanByPanNo(@PathVariable String panNo) {
//		return panService.getPanByPanNo(panNo);
		rh = new ResponseHeader();
		rh.putOnMap("success", "true");
		ResponseEntity<Pan> res = new ResponseEntity<Pan>(
				panService.getPanByPanNo(panNo),
				rh.getHeaders(), HttpStatus.OK);
		return res;
	}
	
	@GetMapping(path = "/get-pans", produces = "application/json")
	public ResponseEntity<List<Pan>> getAllPans() {
//		return panService.getAllPans();
		rh = new ResponseHeader();
		rh.putOnMap("success", "true");
		ResponseEntity<List<Pan>> res = new ResponseEntity<List<Pan>>(
				panService.getAllPans(),
				rh.getHeaders(), HttpStatus.OK);
		return res;
	}
	
	@GetMapping(path = "/get-pan-by-customer", produces = "application/json")
	public ResponseEntity<Pan> getPanByCustomerId(@RequestParam int custId) {
//		return panService.getPanByCustomerId(custId);
		rh = new ResponseHeader();
		rh.putOnMap("success", "true");
		ResponseEntity<Pan> res = new ResponseEntity<Pan>(
				panService.getPanByCustomerId(custId),
				rh.getHeaders(), HttpStatus.OK);
		return res;
	}
	@GetMapping(path = "/get-cibil-score", produces = "application/json")
	public ResponseEntity<Integer> getCibilScore(@RequestParam int custId) {
//		return panService.getCibilScore(custId);
		rh = new ResponseHeader();
		rh.putOnMap("success", "true");
		ResponseEntity<Integer> res = new ResponseEntity<Integer>(
				panService.getCibilScore(custId),
				rh.getHeaders(), HttpStatus.OK);
		return res;
	}
	
	
}
