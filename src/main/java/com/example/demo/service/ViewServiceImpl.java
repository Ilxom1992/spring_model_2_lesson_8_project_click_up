package com.example.demo.service;

import com.example.demo.entity.View;
import com.example.demo.repository.ViewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewServiceImpl implements ViewService{
    final ViewRepository viewRepository;

    public ViewServiceImpl(ViewRepository viewRepository) {
        this.viewRepository = viewRepository;
    }

    @Override
    public List<View> getViews(Long spaceId) {
        return viewRepository.getAllBySpace(spaceId);
    }
}
