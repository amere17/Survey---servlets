package ex2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "ex2.Poll",
        urlPatterns = {"/poll"})
/**
 *
 */
public class Poll extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("");
    }

    /**
     * Function to get the answer and update the data
     * @param request request
     * @param response response
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean update;
        try {
            update = Servlet.isVoted(request);
            String p = request.getParameter("option");
            Survey uSurvey = (Survey) request.getServletContext().getAttribute("result");
            if (p != null) {
                if (!update)
                    uSurvey.UpdateResult(p);
                Cookie ck1 = new Cookie("user", "true");//creating cookie object
                response.addCookie(ck1);//adding cookie in the response
                request.getServletContext().setAttribute("result", uSurvey);
            }
            response.sendRedirect("/");
        }catch (Exception e){
            response.sendRedirect("/");
        }
    }
}
