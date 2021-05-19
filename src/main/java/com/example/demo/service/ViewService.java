package com.example.demo.service;


import com.example.demo.entity.View;

import java.util.List;

public interface ViewService {

    List<View> getViews(Long spaceId);
}
