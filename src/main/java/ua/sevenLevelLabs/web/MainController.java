package ua.sevenLevelLabs.web;

/**
 * @author Tatiana Marchuk
 * 16.03.16 : 10:13
 */

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import net.sf.jasperreports.engine.JRException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ua.sevenLevelLabs.model.Template;
import ua.sevenLevelLabs.service.DownloadService;
import ua.sevenLevelLabs.service.DownloadServiceImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


@Controller
@ComponentScan("ua.sevenLevelLabs.service")

public class MainController {

    private final String PATH = "/home/tanya/dev/projects/carsReport/src/main/resources/report/";
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    private DownloadService service;

    @Autowired
    public void setService(DownloadServiceImpl service) {
        this.service = service;
    }

    @RequestMapping(value = {"/hello", "/"}, method = RequestMethod.GET)
    public ModelAndView hello() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        Template template1;
        List<Template> templateList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        File[] files = new File(PATH).listFiles();
        if (files != null)
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".jrxml")) {
                    template1 = new Template(file.getName().replaceAll(".jrxml", ""), sdf.format(file.lastModified()), file.length());
                    templateList.add(template1);
                }
            }
        modelAndView.addObject("templateList", templateList);
        logger.info("List of templates is downloaded!");
        return modelAndView;
    }


    @RequestMapping(value = "/{fileName}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> getFile(@PathVariable("fileName") String fileName) throws JRException, ClassNotFoundException, IOException, DocumentException {
        String fileExportPath = PATH + fileName + ".pdf";
        String fileStartPath = PATH + fileName + ".jrxml";
        PdfWriter.getInstance(new Document(), new FileOutputStream(new File(fileExportPath)));
        byte[] contents = service.downloadPDF(fileStartPath, fileExportPath);
        HttpHeaders headers = getHeaders();
        logger.info("Pdf report is constructed and downloaded on separate page!");
        return new ResponseEntity<>(contents, headers, HttpStatus.OK);
    }


    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        return headers;
    }
}
