package test;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.dao.ArticleDao;
import article.model.Article;
import article.model.Writer;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

/**
 * Servlet implementation class ArticleDaoInsertServlet
 */
@WebServlet("/ArticleDaoInsertServlet")
public class ArticleDaoInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticleDaoInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		Article article = new Article(null,new Writer("baek","리"),"title",new Date(), new Date(), 0);
		
		try {
		conn = ConnectionProvider.getConnection();
		ArticleDao dao = new ArticleDao();
		Article ar = dao.insert(conn, article);
		
		System.out.println(ar.getNumber());
		System.out.println(ar.getTitle());
		System.out.println(ar.getWriter().getId());
		System.out.println(ar.getWriter().getName());
		System.out.println(ar.getRegDate());
		System.out.println(ar.getModifiedDate());
		System.out.println(ar.getReadCount());
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
