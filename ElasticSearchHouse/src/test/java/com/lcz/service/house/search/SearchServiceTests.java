package com.lcz.service.house.search;

import com.lcz.ApplicationTests;
import com.lcz.service.ServiceMultiResult;
import com.lcz.service.search.ISearchService;
import com.lcz.web.form.RentSearch;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SearchServiceTests extends ApplicationTests {

    @Autowired
    private ISearchService searchService;

    @Test
    public void testIndex() {
        Long targetHouseId = 16L;
        searchService.index(targetHouseId);
//        boolean success = searchService.index(targetHouseId);
//        Assert.assertTrue(success);
        for(int i = 15; i <= 27; i++){
            searchService.index((long)i);
        }
    }

    @Test
    public void testRemove() {
        Long targetHouseId = 15L;

        searchService.remove(targetHouseId);
    }

    @Test
    public void testQuery() {
        RentSearch rentSearch = new RentSearch();
        rentSearch.setCityEnName("bj");
        rentSearch.setStart(0);
        rentSearch.setSize(10);
        rentSearch.setKeywords("国贸");
        ServiceMultiResult<Long> serviceResult = searchService.query(rentSearch);
        System.out.println(serviceResult.getResult());
        Assert.assertTrue(serviceResult.getTotal() > 0);
    }
}
