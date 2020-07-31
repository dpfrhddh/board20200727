package article.service;

import java.sql.Connection;
import java.sql.SQLException;

import article.dao.ArticleDao;
import article.model.Article;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class DeleteArticleService {

	private ArticleDao dao = new ArticleDao();
	
	public void delete(DeleteRequest del) {
		Connection conn = null;
		try {
			conn=ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Article article = dao.selectById(conn, del.getArticleNumber());
			
			if(article == null) {
				throw new ArticleNotFoundException();
			}
			if(!canDelete(del.getUserId(), article)) {
				throw new PermissionDeniedException();
			}
			dao.delete(conn, del.getArticleNumber());
			conn.commit();
		}catch(SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}catch(PermissionDeniedException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw e;
		}finally {
			JdbcUtil.close(conn);
		}
	}

	private boolean canDelete(String delUserId, Article article) {
		return article.getWriter().getId().equals(delUserId);
		
	}
		
}
