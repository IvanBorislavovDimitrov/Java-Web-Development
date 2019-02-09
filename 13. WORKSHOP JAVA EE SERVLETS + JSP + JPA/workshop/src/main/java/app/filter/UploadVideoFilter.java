package app.filter;

import app.model.TubeBindingEntity;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

import static app.constants.Constants.UPLOAD_TUBE;

@WebFilter("/tube/upload")
public class UploadVideoFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String youtubeId = request.getParameter("youtubeId");
        String description = request.getParameter("description");

        if (youtubeId != null) {
            youtubeId = youtubeId.substring(youtubeId.lastIndexOf("=") + 1);
        }

        TubeBindingEntity tubeBindingModel = new TubeBindingEntity();
        tubeBindingModel.setTitle(title);
        tubeBindingModel.setAuthor(author);
        tubeBindingModel.setYoutubeId(youtubeId);
        tubeBindingModel.setDescription(description);
        request.setAttribute(UPLOAD_TUBE, tubeBindingModel);

        chain.doFilter(request, response);
    }
}
