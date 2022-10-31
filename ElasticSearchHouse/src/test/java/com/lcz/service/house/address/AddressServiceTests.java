package com.lcz.service.house.address;

import com.lcz.ApplicationTests;
import com.lcz.service.ServiceResult;
import com.lcz.service.house.IAddressService;
import com.lcz.service.search.BaiduMapLocation;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class AddressServiceTests extends ApplicationTests {
    @Autowired
    private IAddressService addressService;

    @Test
    public void testGetMapLocation() {
        String city = "北京";
        String address = "北京市昌平区巩华家园1号楼2单元";
        ServiceResult<BaiduMapLocation> serviceResult = addressService.getBaiduMapLocation(city, address);
System.out.println(serviceResult+"--"+(serviceResult.getResult().getLongitude())+""+(serviceResult.getResult().getLatitude()));
        Assert.assertTrue(serviceResult.isSuccess());

        Assert.assertTrue(serviceResult.getResult().getLongitude() > 0);
        Assert.assertTrue(serviceResult.getResult().getLatitude() > 0);

    }
}
