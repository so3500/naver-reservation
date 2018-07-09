import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Formatter;

@WebServlet("/today")
public class TodayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TodayServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<html style='height:100%'><body><a href='/aboutme/index.html'>메인화면</a><br>");
		LocalDateTime ldt = LocalDateTime.now();
		Formatter f = new Formatter();
		f.format("%tY/%tm/%te %tk:%tM", ldt, ldt, ldt, ldt, ldt);
		out.print("<div style='position:relative;top:50%;left:50%'>");
		out.print("현재시간 : " + f.toString());
		out.print("</div></body></html>");
		f.close();
	}
}