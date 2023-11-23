package com.fpt.duantn.services;

import com.fpt.duantn.shrared.dto.CRUD.SizeDto;

import java.util.List;

public interface SizeCardService {

    List<SizeDto> getSizeCard(int page, int limit);

}
