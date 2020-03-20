package com.service;

import com.entity.UserVisit;
import com.utils.BrokenLineData;
import com.utils.CircularData;
import com.utils.ColumnarData;

import java.util.List;

public interface ChartsService {

    List<CircularData> classifyGross();

    List<ColumnarData> ClassifyGrossAndBorrow();

    List<BrokenLineData> userVisitLastSeven();
}
