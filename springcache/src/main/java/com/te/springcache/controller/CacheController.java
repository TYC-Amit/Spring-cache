package com.te.springcache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.te.springcache.entity.City;
import com.te.springcache.service.CacheService;

@RestController
public class CacheController {

	@Autowired
	CacheService cacheService;
	
	@GetMapping("/city")
	public City getByCityName(@RequestParam("name") String name) {
		return cacheService.getZipCode(name);
		
	}
	
	@GetMapping("/cache")
	public Cache getCacheDetails(@RequestParam("name") String name) {
		return cacheService.getCacheByName(name);
	}
	
	@PostMapping("/city-added")
	public City addCity(@RequestBody City city) {
		return cacheService.addCity(city);
		
	}
	
	@DeleteMapping("/cache-delete")
	public String removeCache() {
		return cacheService.removeCache();
		
	}
}
