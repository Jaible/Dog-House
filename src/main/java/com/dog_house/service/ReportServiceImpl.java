package com.dog_house.service;

import com.dog_house.entity.Habitacion;
import com.dog_house.repository.HabitacionRepositorio;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    
    @Autowired
    private HabitacionRepositorio habitacionRepositorio;

    private JasperPrint getJasperPrint(List<Habitacion> habitacion, String resourceLocation) throws FileNotFoundException, JRException {
        File file = ResourceUtils.getFile(resourceLocation);
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(habitacion);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Dog House");

        JasperPrint jasperPrint = JasperFillManager
                .fillReport(jasperReport, parameters, dataSource);

        return jasperPrint;
    }

    private Path getUploadPath(String fileFormat, JasperPrint jasperPrint, String fileName) throws IOException, JRException {
        String uploadDir = StringUtils.cleanPath("./generated-reports");
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        if (fileFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(
                    jasperPrint, uploadPath + fileName
            );
        }

        return uploadPath;
    }

    private String getPdfFileLink(String uploadPath) {
        return uploadPath + "/" + "dog_house.pdf";
    }

    @Override
    public String generateReport(Long id, String fileFormat) throws JRException, IOException {
        List<Habitacion> habitaciones = habitacionRepositorio.findAllById(id);
        
        String resourceLocation = "classpath:dog_house.jrxml";
        JasperPrint jasperPrint = getJasperPrint(habitaciones, resourceLocation);

        String fileName = "/" + "dog_house.pdf";
        Path uploadPath = getUploadPath(fileFormat, jasperPrint, fileName);

        return getPdfFileLink(uploadPath.toString());
    }
    
    
}
