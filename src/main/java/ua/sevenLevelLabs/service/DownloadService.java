package ua.sevenLevelLabs.service;

import net.sf.jasperreports.engine.JRException;

/**
 * @author Tatiana Marchuk
 *         29.03.16 : 10:08
 */
public interface DownloadService {
    byte[] downloadPDF(String reportSrcFile, String fileExportPath) throws ClassNotFoundException, JRException;
}
