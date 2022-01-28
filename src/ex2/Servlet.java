package ex2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.Vector;


@WebServlet(name = "ex2.Servlet",
        urlPatterns = {""})
public class Servlet extends HttpServlet {
    private String que;
    private Survey survey = new Survey();

    /**
     * Read the file on the first time(server)
     * @throws ServletException ServletException
     */
    @Override
    public void init() throws ServletException {
        super.init();
        try {
            readSurvey();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check valid file or survey and display the survey
     * @param request request
     * @param response res
     * @throws ServletException Ser Exc
     * @throws IOException IO Exc
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean voted;
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        RequestDispatcher reqDispatcher = request.getRequestDispatcher("/header.html");
        reqDispatcher.include(request, response);
        voted = isVoted(request);
        if (!survey.validFile()) {
            out.println("<h1>Invalid ex2.Poll, Please Try Again Later</h1>");
        } else if (!voted) {
            String question = survey.getmQuestion();
            Vector<String> optionStr = survey.getOptiosStr();
            out.println("<br>" + question);
            out.println("<form action =\"/poll\" method=\"/get\">");
            int i = 1;
            for (String key : optionStr) {
                out.println(i + ". <input type=\"radio\" name=\"option\" value=" + i + ">"
                        + key + "<br>");
                i++;
            }
            out.println("<br><input type=\"submit\">");
            request.getServletContext().setAttribute("result", survey);

        } else {
            RequestDispatcher reqDispatcher1 = request.getRequestDispatcher("/results.html");
            reqDispatcher1.include(request, response);
        }

        RequestDispatcher reqDispatcher3 = request.getRequestDispatcher("/ex2.resultServlet");
        reqDispatcher3.include(request, response);

        RequestDispatcher reqDispatcher2 = request.getRequestDispatcher("/footer.html");
        reqDispatcher2.include(request, response);
        out.close();

    }

    /**
     * Function checks the cookies and find if the user voted
     * @param request req
     * @return check if the client already voted
     */
    static boolean isVoted(HttpServletRequest request) {
        Cookie[] ck = request.getCookies();
        if (ck != null) {
            for (int j = 0; j < ck.length; j++) {
                if (ck[j].getName().equals("user") && ck[j].getValue().equals("true")) {
                    return true;
                }
            }
        }
        return false;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Read ex2.Survey from the file and import valid question and answers to the ex2.Survey object
     * @throws IOException IO EXC
     */
    public void readSurvey() throws IOException {
        int counter = 0;
        Vector<String> vec = new Vector<>();
        File myObj = new File(getServletContext().getRealPath("poll.txt"));
        BufferedReader br = new BufferedReader(new FileReader(myObj));
        String thisLine;
        while ((thisLine = br.readLine()) != null) {
            if (counter == 0 && !thisLine.trim().isEmpty()) {
                que = thisLine;
            } else if (!thisLine.isEmpty()) {
                vec.addElement(thisLine.trim());
            }
            if (thisLine != null && !thisLine.isEmpty())
                counter++;
        }
        br.close();
        Survey survey = new Survey(que, vec);
        setSurvey(survey);

    }

    /**
     * set ex2.Survey to start using it in the website
     * @param survey Survey Object
     */
    public void setSurvey(Survey survey) {
        this.survey = survey;
    }
}
