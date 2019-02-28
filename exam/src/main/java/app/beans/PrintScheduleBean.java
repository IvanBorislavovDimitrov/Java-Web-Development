package app.beans;

import app.service.api.DocumentService;
import com.sun.org.apache.regexp.internal.RE;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.print.Doc;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import sun.misc.IOUtils;

@Named
@RequestScoped
public class PrintScheduleBean {

    @Inject
    private DocumentService documentService;

    public void print() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();

        String id = facesContext.getExternalContext().getRequestParameterMap().get("id");

        documentService.deleteById(id);

        facesContext.getExternalContext().redirect("/");
    }

    public void downloadPdf() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String content = facesContext.getExternalContext().getRequestParameterMap().get("content");
        String title = facesContext.getExternalContext().getRequestParameterMap().get("title");

        String createdContent = createPdfString(content, title);

        getDocument(title, createdContent);

        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        response.reset();
        response.setHeader("Content-Disposition", "attachment;filename=" + title + ".pdf");

        OutputStream responseOutputStream = response.getOutputStream();
        String home = System.getProperty("user.dir");

        responseOutputStream.write(Files.readAllBytes(new File(home + "/" + title + ".pdf").toPath()));

        facesContext.responseComplete();

    }

    private void getDocument(String title, String content) {
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(title + ".pdf"));
            document.open();
            document.add(new Paragraph(title));
            document.add(new Paragraph(content));
            document.close();

            writer.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String createPdfString(String content, String title) {
        String[] lines = content.split(System.lineSeparator());
        StringBuilder sb = new StringBuilder();
        sb.append("<center>").append(System.lineSeparator()).append("<h1>").append(title).append("</h1>")
                .append(System.lineSeparator());
        for (String line : lines) {
            sb.append(enhanceRow(line)).append(System.lineSeparator());
        }

        sb.append("</center>");
        return sb.toString();
    }

    private String enhanceRow(String row) {
        if (row.startsWith("#")) {
            int lastIndexDash = row.lastIndexOf("#");
            int dashesCount = dashesCount(row);

            return "<h" + dashesCount + ">" + row.substring(lastIndexDash + 1) + "</h" + dashesCount + ">" + "<hr/>";
        } else if (row.startsWith("**") && row.endsWith("**")) {
            return "<strong>" + row.substring(2, row.length() - 2) + "</strong><br/>";
        } else if (row.startsWith("*")) {
            return "<ul>" + "<li>" + row.substring(1) + "</li>" + "</ul>";
        }

        return row + "<br/>";
    }

    private int dashesCount(String str) {
        int cnt = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '#') {
                cnt++;
            }
        }

        return cnt;
    }
}
