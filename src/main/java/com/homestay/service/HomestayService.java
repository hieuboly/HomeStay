package com.homestay.service;

import com.homestay.entity.Homestay;
import com.homestay.repository.HomestayRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomestayService {

    private final HomestayRepository homestayRepository;

    public HomestayService(HomestayRepository homestayRepository) {
        this.homestayRepository = homestayRepository;
    }

    public List<Homestay> getAll() {
        return homestayRepository.findAll();
    }
}
