package com.nanemo.from_csv_to_database.service.impl;

import com.nanemo.from_csv_to_database.repository.TextRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TextServiceImpl {
    private final TextRepository textRepository;
}
