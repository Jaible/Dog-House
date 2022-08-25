package com.dog_house.service;

import com.dog_house.entity.Habitacion;
import java.io.IOException;
import java.util.List;
import net.sf.jasperreports.engine.JRException;

public interface ReportService {
    String generateReport(Long id, String fileFormat) throws JRException, IOException;
}
