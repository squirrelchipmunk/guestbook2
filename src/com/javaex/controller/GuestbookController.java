package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestBookDao;
import com.javaex.vo.GuestBookVo;

@WebServlet("/gbc")
public class GuestbookController extends HttpServlet {
    public GuestbookController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("guestbook controller");
		String action = request.getParameter("action");
		
		System.out.println(action);
		request.setCharacterEncoding("UTF-8");
		
		if("addList".equals(action)) {
			GuestBookDao dao = new GuestBookDao();
			List<GuestBookVo> guestBookList = dao.getGuestBookList();

			request.setAttribute("gList", guestBookList);
			// 포워드 
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/addList.jsp");
			rd.forward(request, response);
		}
	    else if("deleteForm".equals(action)) {
	    	int no = Integer.parseInt(request.getParameter("no"));
	    	request.setAttribute("no", no);
	    	
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/deleteForm.jsp");
			rd.forward(request, response);
		}
		
		else if("add".equals(action)) {
			request.setCharacterEncoding("UTF-8");
			String name = request.getParameter("name");
			String password= request.getParameter("password");
			String content = request.getParameter("content");

			System.out.println(name +" " + password+ " "+ content);
			GuestBookVo vo = new GuestBookVo(0, name, password, content, "");
			new GuestBookDao().addGuestBook(vo);
			response.sendRedirect("/guestbook2/gbc?action=addList");
		}
		
		else if("delete".equals(action)) {
			int no = Integer.parseInt(request.getParameter("no"));
			String password = request.getParameter("password");
			GuestBookVo vo = new GuestBookVo(no, "", password, "", "");
			
			new GuestBookDao().deleteGuestBook(vo);
			
			response.sendRedirect("/guestbook2/gbc?action=addList");
		}
		
		else
			System.out.println("파라미터 없음");
	    
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
