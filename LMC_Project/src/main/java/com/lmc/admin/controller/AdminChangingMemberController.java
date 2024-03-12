package com.lmc.admin.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.lmc.common.MyFileRenamePolicy;
import com.lmc.member.model.service.MemberService;
import com.lmc.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class AdminChangingMemberController
 */
@WebServlet("/changing.me")
public class AdminChangingMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminChangingMemberController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		if(ServletFileUpload.isMultipartContent(request)) {
			
			int maxSize = 10 * 1024 * 1024;
			String savePath = request.getSession().getServletContext().getRealPath("/resources/member_profiles/");
			
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			String memberNo = multiRequest.getParameter("memberNo");
			String memberId = multiRequest.getParameter("memberId");
			String memberName = multiRequest.getParameter("memberName");
			String memberNick = multiRequest.getParameter("memberNick");
			String email = multiRequest.getParameter("email");
			String memberImg = multiRequest.getParameter("memberImg");
			
			String changeImg = multiRequest.getFilesystemName("updateImg");
			
			if(multiRequest.getOriginalFileName("updateImg") != null) {
				
				new File(savePath + changeImg).renameTo(new File(savePath + memberNo + "." + changeImg.substring(changeImg.lastIndexOf(".") + 1)));
				new File(savePath + changeImg).delete();
				memberImg = "/resources/member_profiles/" + memberNo + "." + changeImg.substring(changeImg.lastIndexOf(".") + 1);
				
			}

			Member m = new Member();
			m.setMemberId(memberId);
			m.setMemberName(memberName);
			m.setMemberNick(memberNick);
			m.setEmail(email);
			m.setMemberImg(memberImg);
			m.setMemberNo(Integer.parseInt(memberNo));
			
			Member updateMem = new MemberService().updateMember(m);
			
			if(updateMem == null) {
				
				request.setAttribute("errorMsg", "회원정보 수정에 실패했습니다.");
				RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
				view.forward(request, response);
				
			} else {
				
				HttpSession session = request.getSession();
				session.setAttribute("alertMsg", "성공적으로 회원정보를 수정했습니다.");
				session.setAttribute("selectedM", updateMem);
				response.sendRedirect(request.getContextPath() + "/member.no?mno="+ memberNo);
				
			}
			
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
