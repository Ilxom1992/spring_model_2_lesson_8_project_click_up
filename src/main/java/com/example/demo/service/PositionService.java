package com.example.demo.service;

import com.example.demo.entity.Tamplate.Position;
import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.PositionDto;
import com.example.demo.repository.PositionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PositionService {
    final PositionRepository positionRepository;

    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public ApiResponse addPosition(PositionDto positionDto) {
        boolean existsByName = positionRepository.existsByName(positionDto.getName());
        if (existsByName){
            return new ApiResponse("Bunday lavozim bor",false);
        }
        Position position=new Position();
        position.setName(positionDto.getName());
        position.setDescriptin(positionDto.getDescription());
        position.setHuquqList(position.getHuquqList());
        positionRepository.save(position);
        return new ApiResponse("Lavozim saqlandi",true);

    }
    public ApiResponse editPosition(Long id, PositionDto positionDto) {
        Optional<Position> optionalPosition = positionRepository.findById(id);
        if (!optionalPosition.isPresent()){
            return new ApiResponse("Lavozim sizga berilmagan",false);
        }
        Position position = optionalPosition.get();
        position.setName(positionDto.getName());
        position.setDescriptin(position.getDescriptin());
        position.setHuquqList(position.getHuquqList());
        positionRepository.save(position);
        return new ApiResponse("Lavozim saqlandi",true);
    }
}
