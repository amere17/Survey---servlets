package ex2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Vector;

@WebServlet(name = "ex2.resultServlet", urlPatterns = "/ex2.resultServlet")
public class resultServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    /**
     * Function to show the results
     * @param request request
     * @param response response
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Survey uSurvey = (Survey) request.getServletContext().getAttribute("result");
        out.println("<h2>Results</h2>");
        out.println("<h3>"+uSurvey.getmQuestion()+"</h3>");
        HashMap<String,Integer> map;
        map = uSurvey.getmOptions();
        Vector<String> answers = uSurvey.getOptiosStr();
        for(int index = 0; index<map.size();index++){
            out.println("<h5>"+ answers.get(index) +" - "+map.get(answers.get(index))+"</h5>");
        }

    }
}
