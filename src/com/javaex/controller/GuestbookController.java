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
		String action = request.getParameter("action");
		String view = "";
		
		// 방명록 리스트
		if("addList".equals(action)) {
			GuestBookDao dao = new GuestBookDao();
			List<GuestBookVo> guestBookList = dao.getGuestBookList();
			request.setAttribute("gList", guestBookList);
			
			view = "addList";
		}
		
		// 방명록 삭제 화면 
	    else if("deleteForm".equals(action)) {
	    	
	    	view = "deleteForm";
		}
		
		// 방명록 추가 
		else if("add".equals(action)) {
			String name = request.getParameter("name");
			String password= request.getParameter("password");
			String content = request.getParameter("content");
			GuestBookVo vo = new GuestBookVo(0, name, password, content, "");
			
			// 빈칸 처리
			try {
				new GuestBookDao().addGuestBook(vo);
				view = "redirect:addList";
			}catch(Exception e) {
				request.setAttribute("errorMessage", e.getMessage());
				view="errorBack";
			}
		}
		
		// 방명록 삭제 
		else if("delete".equals(action)) {
			int no = Integer.parseInt(request.getParameter("no"));
			String password = request.getParameter("password");
			GuestBookVo vo = new GuestBookVo(no, "", password, "", "");
			
			// 빈칸, 비밀번호 불일치 처리
			try {
				new GuestBookDao().deleteGuestBook(vo);
				view = "redirect:addList";
			}catch(Exception e) {
				request.setAttribute("errorMessage", e.getMessage());
				view="errorBack";
			}
			
		}
		
		else
			System.out.println("파라미터 없음");
		
		
		// view >> redirect
		if(view.startsWith("redirect")) {
			response.sendRedirect("/guestbook2/gbc?action="+ view.substring(9));
		}
		// view >> forward
		else {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/"+view+".jsp");
			rd.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); 
		doGet(request, response);
	}

}
