package ua.sevenLevelLabs.service;


/**
 * @author Tatiana Marchuk
 * 17.03.16 : 21:43
 */

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.*;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.sevenLevelLabs.repository.CarDao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;


/**
 * Service for processing Jasper  reports
 */
@Service("downloadService")
@Transactional
@ComponentScan("ua.sevenLevelLabs.repository")
public class DownloadServiceImpl implements DownloadService {

    protected static Logger logger = LoggerFactory.logger(DownloadServiceImpl.class);

    @Autowired
    private CarDao carDao;

    public byte[] downloadPDF(String reportSrcFile, String fileExportPath) throws ClassNotFoundException, JRException {

        JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);
        Map<String, Object> parameters = new HashMap<String, Object>();
        Connection connection = carDao.getConnection();
        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, connection);
        JRPdfExporter exporter = new JRPdfExporter();
        ExporterInput exporterInput = new SimpleExporterInput(print);
        exporter.setExporterInput(exporterInput);
        OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(fileExportPath);
        exporter.setExporterOutput(exporterOutput);
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        exporter.setConfiguration(configuration);
        exporter.exportReport();
        byte[] data = readFile(fileExportPath);
        logger.info("File " + reportSrcFile + " downloaded to pdf" + new File(fileExportPath).length() + " Data length = " + data.length);
        return data;
    }


    public byte[] readFile(String filePath) {
        Path path = Paths.get(filePath);
        byte[] data = new byte[0];
        try {
            data = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("File " + filePath + " readed and has " + data.length + " bytes.");
        return data;
    }
}
