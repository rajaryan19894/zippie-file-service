package com.googne.zippie.service.file.engine.detector;

import com.googne.zippie.service.file.model.HeaderRule;

import java.util.List;

public interface HeaderDetector<SHEET, HEADER> {

    HEADER detect(SHEET sheet, List<HeaderRule> rules);
}

