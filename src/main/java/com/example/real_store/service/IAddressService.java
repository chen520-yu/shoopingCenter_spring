package com.example.real_store.service;

public interface IAddressService {


    /**
     * 创建新的收货地址
     * @param uid
     * @param username
     * @param address
     */
    void addNewAddress(Integer uid,String username,String address);


}
