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
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestBookVo;

@WebServlet("/gbc")
public class GuestbookController extends HttpServlet {
    public GuestbookController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		// 방명록 삭제 화면 
	    if("deleteForm".equals(action)) {
	    	WebUtil.forward(request, response, "/WEB-INF/deleteForm.jsp");
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
				WebUtil.redirect(request, response, "/guestbook2/gbc");
			}catch(Exception e) {
				request.setAttribute("errorMessage", e.getMessage());
				WebUtil.forward(request, response, "/WEB-INF/errorBack.jsp");
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
				WebUtil.redirect(request, response, "/guestbook2/gbc");
			}catch(Exception e) {
				request.setAttribute("errorMessage", e.getMessage());
				WebUtil.forward(request, response, "/WEB-INF/errorBack.jsp");
			}
			
		}
	    // 방명록 리스트
		else {
			GuestBookDao dao = new GuestBookDao();
			List<GuestBookVo> guestBookList = dao.getGuestBookList();
			request.setAttribute("gList", guestBookList);
		
			WebUtil.forward(request, response, "/WEB-INF/addList.jsp");
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); 
		doGet(request, response);
	}

}
