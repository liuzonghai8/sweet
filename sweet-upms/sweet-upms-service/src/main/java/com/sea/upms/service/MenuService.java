package com.sea.upms.service;

import com.sea.upms.mapper.MenuMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;
}
