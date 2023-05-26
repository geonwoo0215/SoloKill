package com.geonwoo.solokill.domain.matchrecord.service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class MatchRecordCacheTest {

	@Autowired
	private MatchRecordService matchRecordService;

	@Autowired
	private CacheManager cacheManager;

	@Test
	@DisplayName("[성공] 경기 캐싱에 성공한다.")
	void getPlayerChampionByName() {

		String name = "리거누";
		Cache matchRecordCacheStore = cacheManager.getCache("matchRecordCacheStore");

		List<String> championList1 = matchRecordService.getPlayerChampionByName(name);
		List<String> championList2 = matchRecordService.getPlayerChampionByName(name);

		Assertions.assertEquals(championList1, championList2);
		Cache.ValueWrapper valueWrapper = matchRecordCacheStore.get(name);
		Assertions.assertNotNull(valueWrapper);
		List<String> cachedData = (List<String>)valueWrapper.get();
		Assertions.assertEquals(championList1, cachedData);
	}
}
