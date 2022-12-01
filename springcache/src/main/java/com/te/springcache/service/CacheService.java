package com.te.springcache.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.te.springcache.entity.City;

@Service
public class CacheService {
	
	@Autowired
	CacheManager cacheManager;

	Map<String, City> cityZipCodeMap;
	
	public CacheService() {
		cityZipCodeMap= new HashMap<>();
		
		cityZipCodeMap.put("Bangalore", City.builder().cityName("Bangalore").zipCode("339977").build());
		cityZipCodeMap.put("Assam", City.builder().cityName("Assam").zipCode("783286").build());
		cityZipCodeMap.put("Hyd", City.builder().cityName("Hyd").zipCode("879688").build());
		cityZipCodeMap.put("srinagar", City.builder().cityName("srinagar").zipCode("767474").build());
		cityZipCodeMap.put("BTM", City.builder().cityName("BTM").zipCode("767686").build());
		cityZipCodeMap.put("Jayanagar", City.builder().cityName("Jayanagar").zipCode("843566").build());
	}
	
	@Cacheable(value = "city-zip-cache")
	public City getZipCode(String cityName) {
		
		System.out.println("getZipCode Method Called");
		return cityZipCodeMap.get(cityName);
		
	}
	
	public Cache getCacheByName(String cacheName) {
		return cacheManager.getCache(cacheName);
	}
	
	@CachePut(value = "city-zip-cache",key="#city.cityName")
	public City addCity(City city) {
		cityZipCodeMap.put(city.getCityName(), city);
		return city;
	}
	
	@CacheEvict(value = "city-zip-cache",allEntries = true)
	public String removeCache() {
		return "Cache Removed Succesfully";
		
	}
}
